package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Teacher;
import com.example.userserver.service.TeacherService;

import javax.validation.Valid;

@Api(value = "teacher模块接口",description = "这是一个教师模块的接口文档")
@RestController
@Slf4j
public class TeacherController {

	@Autowired
    TeacherService teacherService;

	@ApiOperation("查询所有教师 支持多条件分页排序查询")
    @PostMapping("/getAllTeacher")
    public Object getAllTeacher(@RequestBody PageParam<Teacher> pageParam){
        return MyRsp.success(teacherService.getAllTeacher(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeTeacherById/{id}")
    public Object removeTeacherByTeacherName(@PathVariable("id") int id){

        return teacherService.removeTeacherById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addTeacher")
    public Object addTeacher(@RequestBody @Valid Teacher teacherParam){
        Teacher teacher=(Teacher)teacherService.addTeacher(teacherParam);

        return teacher!=null?MyRsp.success(teacher).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateTeacher")
    public Object updateTeacher(@RequestBody@Valid Teacher teacher){
        return teacherService.updateTeacher(teacher)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getTeacherById/{id}")
    public Object getTeacherById(@PathVariable("id") int id){

        Teacher teacher=teacherService.getTeacherById(id);
        return teacher!=null?MyRsp.success(teacher):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}