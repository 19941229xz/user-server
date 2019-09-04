package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Comment;
import com.example.userserver.service.CommentService;

import javax.validation.Valid;

@Api(value = "comment模块接口",description = "这是一个用户评论模块的接口文档")
@RestController
@Slf4j
public class CommentController {

	@Autowired
    CommentService commentService;

	@ApiOperation("查询所有用户评论 支持多条件分页排序查询")
    @PostMapping("/getAllComment")
    public Object getAllComment(@RequestBody PageParam<Comment> pageParam){
        return MyRsp.success(commentService.getAllComment(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有用户评论 支持分页和排序")
    @PostMapping("/superSearchComment")
    public Object superSearch(@RequestBody PageParam<Comment> pageParam){
        return MyRsp.success(commentService.getAllComment(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除用户评论，同时会清空redis缓存")
    @GetMapping("/removeCommentById/{id}")
    public Object removeCommentByCommentName(@PathVariable("id") int id){

        return commentService.removeCommentById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addComment")
    public Object addComment(@RequestBody @Valid Comment commentParam){
        Comment comment=(Comment)commentService.addComment(commentParam);

        return comment!=null?MyRsp.success(comment).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateComment")
    public Object updateComment(@RequestBody@Valid Comment comment){
        return commentService.updateComment(comment)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getCommentById/{id}")
    public Object getCommentById(@PathVariable("id") int id){

        Comment comment=commentService.getCommentById(id);
        return comment!=null?MyRsp.success(comment):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteCommentByIds")
    public Object batchDeleteCommentByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (commentService.removeCommentById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}