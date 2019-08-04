package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Department;


public interface DepartmentService {



	public Object getAllDepartment(PageParam<Department> pageParam);

    public boolean removeDepartmentById(int id);

    public Object addDepartment(Department department);

    public boolean updateDepartment(Department department);

    public Department getDepartmentById(int id);


}