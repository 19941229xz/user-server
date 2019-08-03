package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Studentelecivecoures;
import com.example.userserver.service.StudentelecivecouresService;

import javax.validation.Valid;

@Api(value = "studentelecivecoures模块接口",description = "这是一个学生选课模块的接口文档")
@RestController
@Slf4j
public class StudentelecivecouresController {

	@Autowired
    StudentelecivecouresService studentelecivecouresService;

	@ApiOperation("查询所有学生选课 支持多条件分页排序查询")
    @PostMapping("/getAllStudentelecivecoures")
    public Object getAllStudentelecivecoures(@RequestBody PageParam<Studentelecivecoures> pageParam){
        return MyRsp.success(studentelecivecouresService.getAllStudentelecivecoures(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeStudentelecivecouresById/{id}")
    public Object removeStudentelecivecouresByStudentelecivecouresName(@PathVariable("id") int id){

        return studentelecivecouresService.removeStudentelecivecouresById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addStudentelecivecoures")
    public Object addStudentelecivecoures(@RequestBody @Valid Studentelecivecoures studentelecivecouresParam){
        Studentelecivecoures studentelecivecoures=(Studentelecivecoures)studentelecivecouresService.addStudentelecivecoures(studentelecivecouresParam);

        return studentelecivecoures!=null?MyRsp.success(studentelecivecoures).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateStudentelecivecoures")
    public Object updateStudentelecivecoures(@RequestBody@Valid Studentelecivecoures studentelecivecoures){
        return studentelecivecouresService.updateStudentelecivecoures(studentelecivecoures)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getStudentelecivecouresById/{id}")
    public Object getStudentelecivecouresById(@PathVariable("id") int id){

        Studentelecivecoures studentelecivecoures=studentelecivecouresService.getStudentelecivecouresById(id);
        return studentelecivecoures!=null?MyRsp.success(studentelecivecoures):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}