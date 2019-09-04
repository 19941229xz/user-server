package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Department;
import com.example.userserver.service.DepartmentService;

import javax.validation.Valid;

@Api(value = "department模块接口",description = "这是一个部门模块的接口文档")
@RestController
@Slf4j
public class DepartmentController {

	@Autowired
    DepartmentService departmentService;

	@ApiOperation("查询所有部门 支持多条件分页排序查询")
    @PostMapping("/getAllDepartment")
    public Object getAllDepartment(@RequestBody PageParam<Department> pageParam){
        return MyRsp.success(departmentService.getAllDepartment(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有部门 支持分页和排序")
    @PostMapping("/superSearchDepartment")
    public Object superSearch(@RequestBody PageParam<Department> pageParam){
        return MyRsp.success(departmentService.getAllDepartment(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除部门，同时会清空redis缓存")
    @GetMapping("/removeDepartmentById/{id}")
    public Object removeDepartmentByDepartmentName(@PathVariable("id") int id){

        return departmentService.removeDepartmentById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addDepartment")
    public Object addDepartment(@RequestBody @Valid Department departmentParam){
        Department department=(Department)departmentService.addDepartment(departmentParam);

        return department!=null?MyRsp.success(department).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateDepartment")
    public Object updateDepartment(@RequestBody@Valid Department department){
        return departmentService.updateDepartment(department)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getDepartmentById/{id}")
    public Object getDepartmentById(@PathVariable("id") int id){

        Department department=departmentService.getDepartmentById(id);
        return department!=null?MyRsp.success(department):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteDepartmentByIds")
    public Object batchDeleteDepartmentByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (departmentService.removeDepartmentById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}