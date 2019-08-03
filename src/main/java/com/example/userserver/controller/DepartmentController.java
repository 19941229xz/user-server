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
@CrossOrigin
public class DepartmentController {

	@Autowired
    DepartmentService departmentService;

	@ApiOperation("查询所有部门 支持多条件分页排序查询")
    @PostMapping("/getAllDepartment")
    public Object getAllDepartment(@RequestBody PageParam<Department> pageParam){
        return MyRsp.success(departmentService.getAllDepartment(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeDepartmentById/{id}")
    public Object removeDepartmentByDepartmentName(@PathVariable("id") int id){

        return departmentService.removeDepartmentById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addDepartment")
    public Object addDepartment(@RequestBody @Valid Department departmentParam){
        Department department=(Department)departmentService.addDepartment(departmentParam);

        return department!=null?MyRsp.success(department).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateDepartment")
    public Object updateDepartment(@RequestBody@Valid Department department){
        return departmentService.updateDepartment(department)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getDepartmentById/{id}")
    public Object getDepartmentById(@PathVariable("id") int id){

        Department department=departmentService.getDepartmentById(id);
        return department!=null?MyRsp.success(department):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}