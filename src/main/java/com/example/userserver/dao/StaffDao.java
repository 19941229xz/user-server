package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Staff;

import java.util.List;

public interface StaffDao {


    List<Staff> getAllStaff(Staff staff);

    @Delete("delete from staff where id = #{id}")
    int removeStaffById(int id);

    int addStaff(Staff staff);

    int updateStaff(Staff staff);

    @Select("select * from staff where id =#{id}")
    Staff getStaffById(int id);

    @Select("select * from staff where staffName =#{staffName}")
    Staff getStaffByStaffName(String staffName);




}