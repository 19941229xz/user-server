package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.School;

import java.util.List;

public interface SchoolDao {


    List<School> getAllSchool(School school);

    @Delete("delete from school where id = #{id}")
    int removeSchoolById(int id);

    int addSchool(School school);

    int updateSchool(School school);

    @Select("select * from school where id =#{id}")
    School getSchoolById(int id);

    @Select("select * from school where schoolName =#{schoolName}")
    School getSchoolBySchoolName(String schoolName);




}