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
import com.example.userserver.dao.EmailaddressmanagementDao;
import com.example.userserver.model.Emailaddressmanagement;

import java.util.List;

@Slf4j
@Service
@Transactional
public class EmailaddressmanagementServiceImpl implements EmailaddressmanagementService {


	@Autowired
    EmailaddressmanagementDao emailaddressmanagementDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllEmailaddressmanagement(PageParam<Emailaddressmanagement> pageParam){
    
    	if(StringUtils.isEmpty(pageParam.getSuperSearchKeyWord())){
            PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Emailaddressmanagement> emailaddressmanagementList=emailaddressmanagementDao.getAllEmailaddressmanagement(pageParam.getModel());
            PageInfo<Emailaddressmanagement> emailaddressmanagementPageInfo = new PageInfo<Emailaddressmanagement>(emailaddressmanagementList);
    
            return emailaddressmanagementPageInfo;
        }else{
        	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Emailaddressmanagement> emailaddressmanagementList=emailaddressmanagementDao.superSearch(pageParam.getSuperSearchKeyWord());
            PageInfo<Emailaddressmanagement> emailaddressmanagementPageInfo = new PageInfo<Emailaddressmanagement>(emailaddressmanagementList);
    
            return emailaddressmanagementPageInfo;
        }
    
    	
    
    }

	@CacheEvict(value = "emailaddressmanagements",key = "#p0")
    @Override
    public boolean removeEmailaddressmanagementById(int id){
    	return emailaddressmanagementDao.removeEmailaddressmanagementById(id)==1;
    }

	@CachePut(value = "emailaddressmanagements",key = "#p0.id")
    @Override
    public Object addEmailaddressmanagement(Emailaddressmanagement emailaddressmanagement){
        emailaddressmanagementDao.addEmailaddressmanagement(emailaddressmanagement);

        return emailaddressmanagementDao.getEmailaddressmanagementById(emailaddressmanagement.getId());
    }

	@CacheEvict(value = "emailaddressmanagements",key = "#p0.id")
	@Override
    public boolean updateEmailaddressmanagement(Emailaddressmanagement emailaddressmanagement){
    	if(StringUtils.isEmpty(emailaddressmanagement.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改emailaddressmanagement时，id不能为空");
        }

        return emailaddressmanagementDao.updateEmailaddressmanagement(emailaddressmanagement)==1;
    }

	@Cacheable(key = "#p0",value="emailaddressmanagements")
    @Override
    @Transactional(readOnly = true)
    public Emailaddressmanagement getEmailaddressmanagementById(int id){
    	return emailaddressmanagementDao.getEmailaddressmanagementById(id);
    	
    }
    
    

}
