package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Faculty;
import com.example.userserver.service.FacultyService;

import javax.validation.Valid;

@Api(value = "faculty模块接口",description = "这是一个院系模块的接口文档")
@RestController
@Slf4j
public class FacultyController {

	@Autowired
    FacultyService facultyService;

	@ApiOperation("查询所有院系 支持多条件分页排序查询")
    @PostMapping("/getAllFaculty")
    public Object getAllFaculty(@RequestBody PageParam<Faculty> pageParam){
        return MyRsp.success(facultyService.getAllFaculty(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeFacultyById/{id}")
    public Object removeFacultyByFacultyName(@PathVariable("id") int id){

        return facultyService.removeFacultyById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addFaculty")
    public Object addFaculty(@RequestBody @Valid Faculty facultyParam){
        Faculty faculty=(Faculty)facultyService.addFaculty(facultyParam);

        return faculty!=null?MyRsp.success(faculty).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateFaculty")
    public Object updateFaculty(@RequestBody@Valid Faculty faculty){
        return facultyService.updateFaculty(faculty)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getFacultyById/{id}")
    public Object getFacultyById(@PathVariable("id") int id){

        Faculty faculty=facultyService.getFacultyById(id);
        return faculty!=null?MyRsp.success(faculty):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}