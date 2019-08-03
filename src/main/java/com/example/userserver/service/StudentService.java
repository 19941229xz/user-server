package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Student;


public interface StudentService {



	public Object getAllStudent(PageParam<Student> pageParam);

    public boolean removeStudentById(int id);

    public Object addStudent(Student student);

    public boolean updateStudent(Student student);

    public Student getStudentById(int id);




}