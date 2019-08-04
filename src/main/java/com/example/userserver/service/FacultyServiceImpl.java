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
import com.example.userserver.dao.FacultyDao;
import com.example.userserver.model.Faculty;

import java.util.List;

@Slf4j
@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {


	@Autowired
    FacultyDao facultyDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllFaculty(PageParam<Faculty> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Faculty> facultyList=facultyDao.getAllFaculty(pageParam.getModel());
        PageInfo<Faculty> facultyPageInfo = new PageInfo<Faculty>(facultyList);

        return facultyPageInfo;
    
    }

	@CacheEvict(value = "facultys",key = "#p0")
    @Override
    public boolean removeFacultyById(int id){
    	return facultyDao.removeFacultyById(id)==1;
    }

	@CachePut(value = "facultys",key = "#p0.id")
    @Override
    public Object addFaculty(Faculty faculty){
        facultyDao.addFaculty(faculty);

        return facultyDao.getFacultyById(faculty.getId());
    }

	@CacheEvict(value = "facultys",key = "#p0.id")
	@Override
    public boolean updateFaculty(Faculty faculty){
    	if(StringUtils.isEmpty(faculty.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改faculty时，id不能为空");
        }

        return facultyDao.updateFaculty(faculty)==1;
    }

	@Cacheable(key = "#p0",value="facultys")
    @Override
    @Transactional(readOnly = true)
    public Faculty getFacultyById(int id){
    	return facultyDao.getFacultyById(id);
    	
    }
    
    

}
