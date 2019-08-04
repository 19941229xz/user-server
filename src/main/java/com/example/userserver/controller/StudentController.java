package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Student;
import com.example.userserver.service.StudentService;

import javax.validation.Valid;

@Api(value = "student模块接口",description = "这是一个学生模块的接口文档")
@RestController
@Slf4j
public class StudentController {

	@Autowired
    StudentService studentService;

	@ApiOperation("查询所有学生 支持多条件分页排序查询")
    @PostMapping("/getAllStudent")
    public Object getAllStudent(@RequestBody PageParam<Student> pageParam){
        return MyRsp.success(studentService.getAllStudent(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeStudentById/{id}")
    public Object removeStudentByStudentName(@PathVariable("id") int id){

        return studentService.removeStudentById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addStudent")
    public Object addStudent(@RequestBody @Valid Student studentParam){
        Student student=(Student)studentService.addStudent(studentParam);

        return student!=null?MyRsp.success(student).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateStudent")
    public Object updateStudent(@RequestBody@Valid Student student){
        return studentService.updateStudent(student)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getStudentById/{id}")
    public Object getStudentById(@PathVariable("id") int id){

        Student student=studentService.getStudentById(id);
        return student!=null?MyRsp.success(student):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @PostMapping("/batchDeleteStudentByIds")
    public Object batchDeleteStudentByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (studentService.removeStudentById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}