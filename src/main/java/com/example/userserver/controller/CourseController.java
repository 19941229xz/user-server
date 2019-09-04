package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Course;
import com.example.userserver.service.CourseService;

import javax.validation.Valid;

@Api(value = "course模块接口",description = "这是一个课程模块的接口文档")
@RestController
@Slf4j
public class CourseController {

	@Autowired
    CourseService courseService;

	@ApiOperation("查询所有课程 支持多条件分页排序查询")
    @PostMapping("/getAllCourse")
    public Object getAllCourse(@RequestBody PageParam<Course> pageParam){
        return MyRsp.success(courseService.getAllCourse(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有课程 支持分页和排序")
    @PostMapping("/superSearchCourse")
    public Object superSearch(@RequestBody PageParam<Course> pageParam){
        return MyRsp.success(courseService.getAllCourse(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除课程，同时会清空redis缓存")
    @GetMapping("/removeCourseById/{id}")
    public Object removeCourseByCourseName(@PathVariable("id") int id){

        return courseService.removeCourseById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addCourse")
    public Object addCourse(@RequestBody @Valid Course courseParam){
        Course course=(Course)courseService.addCourse(courseParam);

        return course!=null?MyRsp.success(course).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateCourse")
    public Object updateCourse(@RequestBody@Valid Course course){
        return courseService.updateCourse(course)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getCourseById/{id}")
    public Object getCourseById(@PathVariable("id") int id){

        Course course=courseService.getCourseById(id);
        return course!=null?MyRsp.success(course):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteCourseByIds")
    public Object batchDeleteCourseByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (courseService.removeCourseById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}