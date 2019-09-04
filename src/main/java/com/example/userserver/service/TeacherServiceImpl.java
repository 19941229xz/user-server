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
import com.example.userserver.dao.TeacherDao;
import com.example.userserver.model.Teacher;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {


	@Autowired
    TeacherDao teacherDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllTeacher(PageParam<Teacher> pageParam){
    
    	if(StringUtils.isEmpty(pageParam.getSuperSearchKeyWord())){
            PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Teacher> teacherList=teacherDao.getAllTeacher(pageParam.getModel());
            PageInfo<Teacher> teacherPageInfo = new PageInfo<Teacher>(teacherList);
    
            return teacherPageInfo;
        }else{
        	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Teacher> teacherList=teacherDao.superSearch(pageParam.getSuperSearchKeyWord());
            PageInfo<Teacher> teacherPageInfo = new PageInfo<Teacher>(teacherList);
    
            return teacherPageInfo;
        }
    
    	
    
    }

	@CacheEvict(value = "teachers",key = "#p0")
    @Override
    public boolean removeTeacherById(int id){
    	return teacherDao.removeTeacherById(id)==1;
    }

	@CachePut(value = "teachers",key = "#p0.id")
    @Override
    public Object addTeacher(Teacher teacher){
        teacherDao.addTeacher(teacher);

        return teacherDao.getTeacherById(teacher.getId());
    }

	@CacheEvict(value = "teachers",key = "#p0.id")
	@Override
    public boolean updateTeacher(Teacher teacher){
    	if(StringUtils.isEmpty(teacher.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改teacher时，id不能为空");
        }

        return teacherDao.updateTeacher(teacher)==1;
    }

	@Cacheable(key = "#p0",value="teachers")
    @Override
    @Transactional(readOnly = true)
    public Teacher getTeacherById(int id){
    	return teacherDao.getTeacherById(id);
    	
    }
    
    

}
