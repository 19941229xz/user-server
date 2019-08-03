package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.School;
import com.example.userserver.service.SchoolService;

import javax.validation.Valid;

@Api(value = "school模块接口",description = "这是一个学校模块的接口文档")
@RestController
@Slf4j
public class SchoolController {

	@Autowired
    SchoolService schoolService;

	@ApiOperation("查询所有学校 支持多条件分页排序查询")
    @PostMapping("/getAllSchool")
    public Object getAllSchool(@RequestBody PageParam<School> pageParam){
        return MyRsp.success(schoolService.getAllSchool(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeSchoolById/{id}")
    public Object removeSchoolBySchoolName(@PathVariable("id") int id){

        return schoolService.removeSchoolById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addSchool")
    public Object addSchool(@RequestBody @Valid School schoolParam){
        School school=(School)schoolService.addSchool(schoolParam);

        return school!=null?MyRsp.success(school).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateSchool")
    public Object updateSchool(@RequestBody@Valid School school){
        return schoolService.updateSchool(school)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getSchoolById/{id}")
    public Object getSchoolById(@PathVariable("id") int id){

        School school=schoolService.getSchoolById(id);
        return school!=null?MyRsp.success(school):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}