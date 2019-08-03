package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Department;

import java.util.List;

public interface DepartmentDao {


    List<Department> getAllDepartment(Department department);

    @Delete("delete from department where id = #{id}")
    int removeDepartmentById(int id);

    int addDepartment(Department department);

    int updateDepartment(Department department);

    @Select("select * from department where id =#{id}")
    Department getDepartmentById(int id);

    @Select("select * from department where departmentName =#{departmentName}")
    Department getDepartmentByDepartmentName(String departmentName);




}