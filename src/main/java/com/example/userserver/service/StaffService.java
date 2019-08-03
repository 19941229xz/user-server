package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Staff;


public interface StaffService {



	public Object getAllStaff(PageParam<Staff> pageParam);

    public boolean removeStaffById(int id);

    public Object addStaff(Staff staff);

    public boolean updateStaff(Staff staff);

    public Staff getStaffById(int id);




}