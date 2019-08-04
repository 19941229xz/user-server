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
import com.example.userserver.dao.TeachDao;
import com.example.userserver.model.Teach;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TeachServiceImpl implements TeachService {


	@Autowired
    TeachDao teachDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllTeach(PageParam<Teach> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Teach> teachList=teachDao.getAllTeach(pageParam.getModel());
        PageInfo<Teach> teachPageInfo = new PageInfo<Teach>(teachList);

        return teachPageInfo;
    
    }

	@CacheEvict(value = "teachs",key = "#p0")
    @Override
    public boolean removeTeachById(int id){
    	return teachDao.removeTeachById(id)==1;
    }

	@CachePut(value = "teachs",key = "#p0.id")
    @Override
    public Object addTeach(Teach teach){
        teachDao.addTeach(teach);

        return teachDao.getTeachById(teach.getId());
    }

	@CacheEvict(value = "teachs",key = "#p0.id")
	@Override
    public boolean updateTeach(Teach teach){
    	if(StringUtils.isEmpty(teach.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改teach时，id不能为空");
        }

        return teachDao.updateTeach(teach)==1;
    }

	@Cacheable(key = "#p0",value="teachs")
    @Override
    @Transactional(readOnly = true)
    public Teach getTeachById(int id){
    	return teachDao.getTeachById(id);
    	
    }
    
    

}
