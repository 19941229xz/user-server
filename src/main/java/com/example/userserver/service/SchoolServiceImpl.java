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
import com.example.userserver.dao.SchoolDao;
import com.example.userserver.model.School;

import java.util.List;

@Slf4j
@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {


	@Autowired
    SchoolDao schoolDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllSchool(PageParam<School> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<School> schoolList=schoolDao.getAllSchool(pageParam.getModel());
        PageInfo<School> schoolPageInfo = new PageInfo<School>(schoolList);

        return schoolPageInfo;
    
    }

	@CacheEvict(value = "schools",key = "#p0")
    @Override
    public boolean removeSchoolById(int id){
    	return schoolDao.removeSchoolById(id)==1;
    }

	@CachePut(value = "schools",key = "#p0.id")
    @Override
    public Object addSchool(School school){
        schoolDao.addSchool(school);

        return schoolDao.getSchoolById(school.getId());
    }

	@CacheEvict(value = "schools",key = "#p0.id")
	@Override
    public boolean updateSchool(School school){
    	if(StringUtils.isEmpty(school.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改school时，id不能为空");
        }

        return schoolDao.updateSchool(school)==1;
    }

	@Cacheable(key = "#p0",value="schools")
    @Override
    @Transactional(readOnly = true)
    public School getSchoolById(int id){
    	return schoolDao.getSchoolById(id);
    	
    }




}
