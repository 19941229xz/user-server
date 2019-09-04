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
import com.example.userserver.dao.BanjiDao;
import com.example.userserver.model.Banji;

import java.util.List;

@Slf4j
@Service
@Transactional
public class BanjiServiceImpl implements BanjiService {


	@Autowired
    BanjiDao banjiDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllBanji(PageParam<Banji> pageParam){
    
    	if(StringUtils.isEmpty(pageParam.getSuperSearchKeyWord())){
            PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Banji> banjiList=banjiDao.getAllBanji(pageParam.getModel());
            PageInfo<Banji> banjiPageInfo = new PageInfo<Banji>(banjiList);
    
            return banjiPageInfo;
        }else{
        	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Banji> banjiList=banjiDao.superSearch(pageParam.getSuperSearchKeyWord());
            PageInfo<Banji> banjiPageInfo = new PageInfo<Banji>(banjiList);
    
            return banjiPageInfo;
        }
    
    	
    
    }

	@CacheEvict(value = "banjis",key = "#p0")
    @Override
    public boolean removeBanjiById(int id){
    	return banjiDao.removeBanjiById(id)==1;
    }

	@CachePut(value = "banjis",key = "#p0.id")
    @Override
    public Object addBanji(Banji banji){
        banjiDao.addBanji(banji);

        return banjiDao.getBanjiById(banji.getId());
    }

	@CacheEvict(value = "banjis",key = "#p0.id")
	@Override
    public boolean updateBanji(Banji banji){
    	if(StringUtils.isEmpty(banji.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改banji时，id不能为空");
        }

        return banjiDao.updateBanji(banji)==1;
    }

	@Cacheable(key = "#p0",value="banjis")
    @Override
    @Transactional(readOnly = true)
    public Banji getBanjiById(int id){
    	return banjiDao.getBanjiById(id);
    	
    }
    
    

}
