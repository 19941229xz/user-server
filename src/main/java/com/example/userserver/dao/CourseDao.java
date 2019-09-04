package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Course;

import java.util.List;

public interface CourseDao {


    List<Course> getAllCourse(Course course);

    @Delete("delete from course where id = #{id}")
    int removeCourseById(int id);

    int addCourse(Course course);

    int updateCourse(Course course);

    @Select("select * from course where id =#{id}")
    Course getCourseById(int id);

    @Select("select * from course where courseName =#{courseName}")
    Course getCourseByCourseName(String courseName);


	List<Course> superSearch(String superSearchKeyWord);

}