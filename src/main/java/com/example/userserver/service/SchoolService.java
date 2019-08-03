package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.School;


public interface SchoolService {



	public Object getAllSchool(PageParam<School> pageParam);

    public boolean removeSchoolById(int id);

    public Object addSchool(School school);

    public boolean updateSchool(School school);

    public School getSchoolById(int id);




}