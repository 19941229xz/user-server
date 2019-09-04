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
import com.example.userserver.dao.StudentDao;
import com.example.userserver.model.Student;

import java.util.List;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl implements StudentService {


	@Autowired
    StudentDao studentDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllStudent(PageParam<Student> pageParam){
    
    	if(StringUtils.isEmpty(pageParam.getSuperSearchKeyWord())){
            PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Student> studentList=studentDao.getAllStudent(pageParam.getModel());
            PageInfo<Student> studentPageInfo = new PageInfo<Student>(studentList);
    
            return studentPageInfo;
        }else{
        	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Student> studentList=studentDao.superSearch(pageParam.getSuperSearchKeyWord());
            PageInfo<Student> studentPageInfo = new PageInfo<Student>(studentList);
    
            return studentPageInfo;
        }
    
    	
    
    }

	@CacheEvict(value = "students",key = "#p0")
    @Override
    public boolean removeStudentById(int id){
    	return studentDao.removeStudentById(id)==1;
    }

	@CachePut(value = "students",key = "#p0.id")
    @Override
    public Object addStudent(Student student){
        studentDao.addStudent(student);

        return studentDao.getStudentById(student.getId());
    }

	@CacheEvict(value = "students",key = "#p0.id")
	@Override
    public boolean updateStudent(Student student){
    	if(StringUtils.isEmpty(student.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改student时，id不能为空");
        }

        return studentDao.updateStudent(student)==1;
    }

	@Cacheable(key = "#p0",value="students")
    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(int id){
    	return studentDao.getStudentById(id);
    	
    }
    
    

}
