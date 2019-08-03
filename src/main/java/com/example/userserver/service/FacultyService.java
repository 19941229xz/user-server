package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Faculty;


public interface FacultyService {



	public Object getAllFaculty(PageParam<Faculty> pageParam);

    public boolean removeFacultyById(int id);

    public Object addFaculty(Faculty faculty);

    public boolean updateFaculty(Faculty faculty);

    public Faculty getFacultyById(int id);




}