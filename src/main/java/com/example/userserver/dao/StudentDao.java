package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Student;

import java.util.List;

public interface StudentDao {


    List<Student> getAllStudent(Student student);

    @Delete("delete from student where id = #{id}")
    int removeStudentById(int id);

    int addStudent(Student student);

    int updateStudent(Student student);

    @Select("select * from student where id =#{id}")
    Student getStudentById(int id);

    @Select("select * from student where studentName =#{studentName}")
    Student getStudentByStudentName(String studentName);




}