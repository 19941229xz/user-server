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
import com.example.userserver.dao.CompanyDao;
import com.example.userserver.model.Company;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {


	@Autowired
    CompanyDao companyDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllCompany(PageParam<Company> pageParam){
    
    	if(StringUtils.isEmpty(pageParam.getSuperSearchKeyWord())){
            PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Company> companyList=companyDao.getAllCompany(pageParam.getModel());
            PageInfo<Company> companyPageInfo = new PageInfo<Company>(companyList);
    
            return companyPageInfo;
        }else{
        	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Company> companyList=companyDao.superSearch(pageParam.getSuperSearchKeyWord());
            PageInfo<Company> companyPageInfo = new PageInfo<Company>(companyList);
    
            return companyPageInfo;
        }
    
    	
    
    }

	@CacheEvict(value = "companys",key = "#p0")
    @Override
    public boolean removeCompanyById(int id){
    	return companyDao.removeCompanyById(id)==1;
    }

	@CachePut(value = "companys",key = "#p0.id")
    @Override
    public Object addCompany(Company company){
        companyDao.addCompany(company);

        return companyDao.getCompanyById(company.getId());
    }

	@CacheEvict(value = "companys",key = "#p0.id")
	@Override
    public boolean updateCompany(Company company){
    	if(StringUtils.isEmpty(company.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改company时，id不能为空");
        }

        return companyDao.updateCompany(company)==1;
    }

	@Cacheable(key = "#p0",value="companys")
    @Override
    @Transactional(readOnly = true)
    public Company getCompanyById(int id){
    	return companyDao.getCompanyById(id);
    	
    }
    
    

}
