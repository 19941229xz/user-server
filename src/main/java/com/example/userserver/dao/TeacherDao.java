package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Teacher;

import java.util.List;

public interface TeacherDao {


    List<Teacher> getAllTeacher(Teacher teacher);

    @Delete("delete from teacher where id = #{id}")
    int removeTeacherById(int id);

    int addTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    @Select("select * from teacher where id =#{id}")
    Teacher getTeacherById(int id);

    @Select("select * from teacher where teacherName =#{teacherName}")
    Teacher getTeacherByTeacherName(String teacherName);




}