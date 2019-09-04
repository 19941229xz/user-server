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
import com.example.userserver.dao.CourseDao;
import com.example.userserver.model.Course;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CourseServiceImpl implements CourseService {


	@Autowired
    CourseDao courseDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllCourse(PageParam<Course> pageParam){
    
    	if(StringUtils.isEmpty(pageParam.getSuperSearchKeyWord())){
            PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Course> courseList=courseDao.getAllCourse(pageParam.getModel());
            PageInfo<Course> coursePageInfo = new PageInfo<Course>(courseList);
    
            return coursePageInfo;
        }else{
        	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Course> courseList=courseDao.superSearch(pageParam.getSuperSearchKeyWord());
            PageInfo<Course> coursePageInfo = new PageInfo<Course>(courseList);
    
            return coursePageInfo;
        }
    
    	
    
    }

	@CacheEvict(value = "courses",key = "#p0")
    @Override
    public boolean removeCourseById(int id){
    	return courseDao.removeCourseById(id)==1;
    }

	@CachePut(value = "courses",key = "#p0.id")
    @Override
    public Object addCourse(Course course){
        courseDao.addCourse(course);

        return courseDao.getCourseById(course.getId());
    }

	@CacheEvict(value = "courses",key = "#p0.id")
	@Override
    public boolean updateCourse(Course course){
    	if(StringUtils.isEmpty(course.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改course时，id不能为空");
        }

        return courseDao.updateCourse(course)==1;
    }

	@Cacheable(key = "#p0",value="courses")
    @Override
    @Transactional(readOnly = true)
    public Course getCourseById(int id){
    	return courseDao.getCourseById(id);
    	
    }
    
    

}
