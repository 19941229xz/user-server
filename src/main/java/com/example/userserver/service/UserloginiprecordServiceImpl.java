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
import com.example.userserver.dao.UserloginiprecordDao;
import com.example.userserver.model.Userloginiprecord;

import java.util.List;

@Slf4j
@Service
@Transactional
public class UserloginiprecordServiceImpl implements UserloginiprecordService {


	@Autowired
    UserloginiprecordDao userloginiprecordDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllUserloginiprecord(PageParam<Userloginiprecord> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Userloginiprecord> userloginiprecordList=userloginiprecordDao.getAllUserloginiprecord(pageParam.getModel());
        PageInfo<Userloginiprecord> userloginiprecordPageInfo = new PageInfo<Userloginiprecord>(userloginiprecordList);

        return userloginiprecordPageInfo;
    
    }

	@CacheEvict(value = "userloginiprecords",key = "#p0")
    @Override
    public boolean removeUserloginiprecordById(int id){
    	return userloginiprecordDao.removeUserloginiprecordById(id)==1;
    }

	@CachePut(value = "userloginiprecords",key = "#p0.id")
    @Override
    public Object addUserloginiprecord(Userloginiprecord userloginiprecord){
        userloginiprecordDao.addUserloginiprecord(userloginiprecord);

        return userloginiprecordDao.getUserloginiprecordById(userloginiprecord.getId());
    }

	@CacheEvict(value = "userloginiprecords",key = "#p0.id")
	@Override
    public boolean updateUserloginiprecord(Userloginiprecord userloginiprecord){
    	if(StringUtils.isEmpty(userloginiprecord.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改userloginiprecord时，id不能为空");
        }

        return userloginiprecordDao.updateUserloginiprecord(userloginiprecord)==1;
    }

	@Cacheable(key = "#p0",value="userloginiprecords")
    @Override
    @Transactional(readOnly = true)
    public Userloginiprecord getUserloginiprecordById(int id){
    	return userloginiprecordDao.getUserloginiprecordById(id);
    	
    }




}
