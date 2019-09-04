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
import com.example.userserver.dao.RoleDao;
import com.example.userserver.model.Role;

import java.util.List;

@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {


	@Autowired
    RoleDao roleDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllRole(PageParam<Role> pageParam){
    
    	if(StringUtils.isEmpty(pageParam.getSuperSearchKeyWord())){
            PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Role> roleList=roleDao.getAllRole(pageParam.getModel());
            PageInfo<Role> rolePageInfo = new PageInfo<Role>(roleList);
    
            return rolePageInfo;
        }else{
        	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Role> roleList=roleDao.superSearch(pageParam.getSuperSearchKeyWord());
            PageInfo<Role> rolePageInfo = new PageInfo<Role>(roleList);
    
            return rolePageInfo;
        }
    
    	
    
    }

	@CacheEvict(value = "roles",key = "#p0")
    @Override
    public boolean removeRoleById(int id){
    	return roleDao.removeRoleById(id)==1;
    }

	@CachePut(value = "roles",key = "#p0.id")
    @Override
    public Object addRole(Role role){
        roleDao.addRole(role);

        return roleDao.getRoleById(role.getId());
    }

	@CacheEvict(value = "roles",key = "#p0.id")
	@Override
    public boolean updateRole(Role role){
    	if(StringUtils.isEmpty(role.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改role时，id不能为空");
        }

        return roleDao.updateRole(role)==1;
    }

	@Cacheable(key = "#p0",value="roles")
    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(int id){
    	return roleDao.getRoleById(id);
    	
    }
    
    

}
