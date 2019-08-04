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
import com.example.userserver.dao.PositionDao;
import com.example.userserver.model.Position;

import java.util.List;

@Slf4j
@Service
@Transactional
public class PositionServiceImpl implements PositionService {


	@Autowired
    PositionDao positionDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllPosition(PageParam<Position> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Position> positionList=positionDao.getAllPosition(pageParam.getModel());
        PageInfo<Position> positionPageInfo = new PageInfo<Position>(positionList);

        return positionPageInfo;
    
    }

	@CacheEvict(value = "positions",key = "#p0")
    @Override
    public boolean removePositionById(int id){
    	return positionDao.removePositionById(id)==1;
    }

	@CachePut(value = "positions",key = "#p0.id")
    @Override
    public Object addPosition(Position position){
        positionDao.addPosition(position);

        return positionDao.getPositionById(position.getId());
    }

	@CacheEvict(value = "positions",key = "#p0.id")
	@Override
    public boolean updatePosition(Position position){
    	if(StringUtils.isEmpty(position.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改position时，id不能为空");
        }

        return positionDao.updatePosition(position)==1;
    }

	@Cacheable(key = "#p0",value="positions")
    @Override
    @Transactional(readOnly = true)
    public Position getPositionById(int id){
    	return positionDao.getPositionById(id);
    	
    }
    
    

}
