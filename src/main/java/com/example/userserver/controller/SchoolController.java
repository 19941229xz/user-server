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
    
    @ApiOperation("按照关键字高级检索所有学校 支持分页和排序")
    @PostMapping("/superSearchSchool")
    public Object superSearch(@RequestBody PageParam<School> pageParam){
        return MyRsp.success(schoolService.getAllSchool(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除学校，同时会清空redis缓存")
    @GetMapping("/removeSchoolById/{id}")
    public Object removeSchoolBySchoolName(@PathVariable("id") int id){

        return schoolService.removeSchoolById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addSchool")
    public Object addSchool(@RequestBody @Valid School schoolParam){
        School school=(School)schoolService.addSchool(schoolParam);

        return school!=null?MyRsp.success(school).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateSchool")
    public Object updateSchool(@RequestBody@Valid School school){
        return schoolService.updateSchool(school)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getSchoolById/{id}")
    public Object getSchoolById(@PathVariable("id") int id){

        School school=schoolService.getSchoolById(id);
        return school!=null?MyRsp.success(school):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteSchoolByIds")
    public Object batchDeleteSchoolByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (schoolService.removeSchoolById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}