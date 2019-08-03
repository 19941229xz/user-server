package com.example.userserver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.example.userserver.common.HttpCode;
import com.example.userserver.common.MyException;
import com.example.userserver.common.PageParam;
import com.example.userserver.dao.DepartmentDao;
import com.example.userserver.model.Department;

import java.util.List;

@Slf4j
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {


	@Autowired
    DepartmentDao departmentDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllDepartment(PageParam<Department> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Department> departmentList=departmentDao.getAllDepartment(pageParam.getModel());
        PageInfo<Department> departmentPageInfo = new PageInfo<Department>(departmentList);

        return departmentPageInfo;
    
    }

	@CacheEvict(value = "departments",key = "#p0")
    @Override
    public boolean removeDepartmentById(int id){
    	return departmentDao.removeDepartmentById(id)==1;
    }

	@CachePut(value = "departments",key = "#p0.id")
    @Override
    public Object addDepartment(Department department){
        departmentDao.addDepartment(department);

        return departmentDao.getDepartmentById(department.getId());
    }

	@CacheEvict(value = "departments",key = "#p0.id")
	@Override
    public boolean updateDepartment(Department department){
    	if(StringUtils.isEmpty(department.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改department时，id不能为空");
        }

        return departmentDao.updateDepartment(department)==1;
    }

	@Cacheable(key = "#p0",value="departments")
    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentById(int id){
    	return departmentDao.getDepartmentById(id);
    	
    }




}
