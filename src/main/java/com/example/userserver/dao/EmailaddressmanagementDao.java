package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Emailaddressmanagement;

import java.util.List;

public interface EmailaddressmanagementDao {


    List<Emailaddressmanagement> getAllEmailaddressmanagement(Emailaddressmanagement emailaddressmanagement);

    @Delete("delete from emailaddressmanagement where id = #{id}")
    int removeEmailaddressmanagementById(int id);

    int addEmailaddressmanagement(Emailaddressmanagement emailaddressmanagement);

    int updateEmailaddressmanagement(Emailaddressmanagement emailaddressmanagement);

    @Select("select * from emailaddressmanagement where id =#{id}")
    Emailaddressmanagement getEmailaddressmanagementById(int id);

    @Select("select * from emailaddressmanagement where emailaddressmanagementName =#{emailaddressmanagementName}")
    Emailaddressmanagement getEmailaddressmanagementByEmailaddressmanagementName(String emailaddressmanagementName);




}