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
import com.example.userserver.dao.StaffDao;
import com.example.userserver.model.Staff;

import java.util.List;

@Slf4j
@Service
@Transactional
public class StaffServiceImpl implements StaffService {


	@Autowired
    StaffDao staffDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllStaff(PageParam<Staff> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Staff> staffList=staffDao.getAllStaff(pageParam.getModel());
        PageInfo<Staff> staffPageInfo = new PageInfo<Staff>(staffList);

        return staffPageInfo;
    
    }

	@CacheEvict(value = "staffs",key = "#p0")
    @Override
    public boolean removeStaffById(int id){
    	return staffDao.removeStaffById(id)==1;
    }

	@CachePut(value = "staffs",key = "#p0.id")
    @Override
    public Object addStaff(Staff staff){
        staffDao.addStaff(staff);

        return staffDao.getStaffById(staff.getId());
    }

	@CacheEvict(value = "staffs",key = "#p0.id")
	@Override
    public boolean updateStaff(Staff staff){
    	if(StringUtils.isEmpty(staff.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改staff时，id不能为空");
        }

        return staffDao.updateStaff(staff)==1;
    }

	@Cacheable(key = "#p0",value="staffs")
    @Override
    @Transactional(readOnly = true)
    public Staff getStaffById(int id){
    	return staffDao.getStaffById(id);
    	
    }
    
    

}
