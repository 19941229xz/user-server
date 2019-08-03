package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Teacher;


public interface TeacherService {



	public Object getAllTeacher(PageParam<Teacher> pageParam);

    public boolean removeTeacherById(int id);

    public Object addTeacher(Teacher teacher);

    public boolean updateTeacher(Teacher teacher);

    public Teacher getTeacherById(int id);




}