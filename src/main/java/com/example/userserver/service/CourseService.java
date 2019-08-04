package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Course;


public interface CourseService {



	public Object getAllCourse(PageParam<Course> pageParam);

    public boolean removeCourseById(int id);

    public Object addCourse(Course course);

    public boolean updateCourse(Course course);

    public Course getCourseById(int id);


}