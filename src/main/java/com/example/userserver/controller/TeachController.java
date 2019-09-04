package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Teach;
import com.example.userserver.service.TeachService;

import javax.validation.Valid;

@Api(value = "teach模块接口",description = "这是一个教师授课模块的接口文档")
@RestController
@Slf4j
public class TeachController {

	@Autowired
    TeachService teachService;

	@ApiOperation("查询所有教师授课 支持多条件分页排序查询")
    @PostMapping("/getAllTeach")
    public Object getAllTeach(@RequestBody PageParam<Teach> pageParam){
        return MyRsp.success(teachService.getAllTeach(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有教师授课 支持分页和排序")
    @PostMapping("/superSearchTeach")
    public Object superSearch(@RequestBody PageParam<Teach> pageParam){
        return MyRsp.success(teachService.getAllTeach(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除教师授课，同时会清空redis缓存")
    @GetMapping("/removeTeachById/{id}")
    public Object removeTeachByTeachName(@PathVariable("id") int id){

        return teachService.removeTeachById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addTeach")
    public Object addTeach(@RequestBody @Valid Teach teachParam){
        Teach teach=(Teach)teachService.addTeach(teachParam);

        return teach!=null?MyRsp.success(teach).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateTeach")
    public Object updateTeach(@RequestBody@Valid Teach teach){
        return teachService.updateTeach(teach)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getTeachById/{id}")
    public Object getTeachById(@PathVariable("id") int id){

        Teach teach=teachService.getTeachById(id);
        return teach!=null?MyRsp.success(teach):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteTeachByIds")
    public Object batchDeleteTeachByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (teachService.removeTeachById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}