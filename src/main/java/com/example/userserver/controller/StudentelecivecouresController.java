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
    
    @ApiOperation("按照关键字高级检索所有学生选课 支持分页和排序")
    @PostMapping("/superSearchStudentelecivecoures")
    public Object superSearch(@RequestBody PageParam<Studentelecivecoures> pageParam){
        return MyRsp.success(studentelecivecouresService.getAllStudentelecivecoures(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除学生选课，同时会清空redis缓存")
    @GetMapping("/removeStudentelecivecouresById/{id}")
    public Object removeStudentelecivecouresByStudentelecivecouresName(@PathVariable("id") int id){

        return studentelecivecouresService.removeStudentelecivecouresById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addStudentelecivecoures")
    public Object addStudentelecivecoures(@RequestBody @Valid Studentelecivecoures studentelecivecouresParam){
        Studentelecivecoures studentelecivecoures=(Studentelecivecoures)studentelecivecouresService.addStudentelecivecoures(studentelecivecouresParam);

        return studentelecivecoures!=null?MyRsp.success(studentelecivecoures).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateStudentelecivecoures")
    public Object updateStudentelecivecoures(@RequestBody@Valid Studentelecivecoures studentelecivecoures){
        return studentelecivecouresService.updateStudentelecivecoures(studentelecivecoures)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getStudentelecivecouresById/{id}")
    public Object getStudentelecivecouresById(@PathVariable("id") int id){

        Studentelecivecoures studentelecivecoures=studentelecivecouresService.getStudentelecivecouresById(id);
        return studentelecivecoures!=null?MyRsp.success(studentelecivecoures):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteStudentelecivecouresByIds")
    public Object batchDeleteStudentelecivecouresByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (studentelecivecouresService.removeStudentelecivecouresById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}