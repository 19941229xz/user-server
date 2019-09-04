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
    
    @ApiOperation("按照关键字高级检索所有教师 支持分页和排序")
    @PostMapping("/superSearchTeacher")
    public Object superSearch(@RequestBody PageParam<Teacher> pageParam){
        return MyRsp.success(teacherService.getAllTeacher(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除教师，同时会清空redis缓存")
    @GetMapping("/removeTeacherById/{id}")
    public Object removeTeacherByTeacherName(@PathVariable("id") int id){

        return teacherService.removeTeacherById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addTeacher")
    public Object addTeacher(@RequestBody @Valid Teacher teacherParam){
        Teacher teacher=(Teacher)teacherService.addTeacher(teacherParam);

        return teacher!=null?MyRsp.success(teacher).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateTeacher")
    public Object updateTeacher(@RequestBody@Valid Teacher teacher){
        return teacherService.updateTeacher(teacher)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getTeacherById/{id}")
    public Object getTeacherById(@PathVariable("id") int id){

        Teacher teacher=teacherService.getTeacherById(id);
        return teacher!=null?MyRsp.success(teacher):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteTeacherByIds")
    public Object batchDeleteTeacherByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (teacherService.removeTeacherById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}