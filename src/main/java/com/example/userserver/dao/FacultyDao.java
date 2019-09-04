package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Faculty;

import java.util.List;

public interface FacultyDao {


    List<Faculty> getAllFaculty(Faculty faculty);

    @Delete("delete from faculty where id = #{id}")
    int removeFacultyById(int id);

    int addFaculty(Faculty faculty);

    int updateFaculty(Faculty faculty);

    @Select("select * from faculty where id =#{id}")
    Faculty getFacultyById(int id);

    @Select("select * from faculty where facultyName =#{facultyName}")
    Faculty getFacultyByFacultyName(String facultyName);


	List<Faculty> superSearch(String superSearchKeyWord);

}