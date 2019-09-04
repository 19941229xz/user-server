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
import com.example.userserver.dao.MenuDao;
import com.example.userserver.model.Menu;

import java.util.List;

@Slf4j
@Service
@Transactional
public class MenuServiceImpl implements MenuService {


	@Autowired
    MenuDao menuDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllMenu(PageParam<Menu> pageParam){
    
    	if(StringUtils.isEmpty(pageParam.getSuperSearchKeyWord())){
            PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Menu> menuList=menuDao.getAllMenu(pageParam.getModel());
            PageInfo<Menu> menuPageInfo = new PageInfo<Menu>(menuList);
    
            return menuPageInfo;
        }else{
        	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Menu> menuList=menuDao.superSearch(pageParam.getSuperSearchKeyWord());
            PageInfo<Menu> menuPageInfo = new PageInfo<Menu>(menuList);
    
            return menuPageInfo;
        }
    
    	
    
    }

	@CacheEvict(value = "menus",key = "#p0")
    @Override
    public boolean removeMenuById(int id){
    	return menuDao.removeMenuById(id)==1;
    }

	@CachePut(value = "menus",key = "#p0.id")
    @Override
    public Object addMenu(Menu menu){
        menuDao.addMenu(menu);

        return menuDao.getMenuById(menu.getId());
    }

	@CacheEvict(value = "menus",key = "#p0.id")
	@Override
    public boolean updateMenu(Menu menu){
    	if(StringUtils.isEmpty(menu.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改menu时，id不能为空");
        }

        return menuDao.updateMenu(menu)==1;
    }

	@Cacheable(key = "#p0",value="menus")
    @Override
    @Transactional(readOnly = true)
    public Menu getMenuById(int id){
    	return menuDao.getMenuById(id);
    	
    }
    
    

}
