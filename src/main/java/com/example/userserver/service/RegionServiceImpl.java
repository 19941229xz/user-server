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
import com.example.userserver.dao.RegionDao;
import com.example.userserver.model.Region;

import java.util.List;

@Slf4j
@Service
@Transactional
public class RegionServiceImpl implements RegionService {


	@Autowired
    RegionDao regionDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllRegion(PageParam<Region> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Region> regionList=regionDao.getAllRegion(pageParam.getModel());
        PageInfo<Region> regionPageInfo = new PageInfo<Region>(regionList);

        return regionPageInfo;
    
    }

	@CacheEvict(value = "regions",key = "#p0")
    @Override
    public boolean removeRegionById(int id){
    	return regionDao.removeRegionById(id)==1;
    }

	@CachePut(value = "regions",key = "#p0.id")
    @Override
    public Object addRegion(Region region){
        regionDao.addRegion(region);

        return regionDao.getRegionById(region.getId());
    }

	@CacheEvict(value = "regions",key = "#p0.id")
	@Override
    public boolean updateRegion(Region region){
    	if(StringUtils.isEmpty(region.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改region时，id不能为空");
        }

        return regionDao.updateRegion(region)==1;
    }

	@Cacheable(key = "#p0",value="regions")
    @Override
    @Transactional(readOnly = true)
    public Region getRegionById(int id){
    	return regionDao.getRegionById(id);
    	
    }
    
    

}
