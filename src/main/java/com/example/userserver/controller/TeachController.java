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

    @GetMapping("/removeTeachById/{id}")
    public Object removeTeachByTeachName(@PathVariable("id") int id){

        return teachService.removeTeachById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addTeach")
    public Object addTeach(@RequestBody @Valid Teach teachParam){
        Teach teach=(Teach)teachService.addTeach(teachParam);

        return teach!=null?MyRsp.success(teach).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateTeach")
    public Object updateTeach(@RequestBody@Valid Teach teach){
        return teachService.updateTeach(teach)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getTeachById/{id}")
    public Object getTeachById(@PathVariable("id") int id){

        Teach teach=teachService.getTeachById(id);
        return teach!=null?MyRsp.success(teach):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
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