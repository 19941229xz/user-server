package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Teach;


public interface TeachService {



	public Object getAllTeach(PageParam<Teach> pageParam);

    public boolean removeTeachById(int id);

    public Object addTeach(Teach teach);

    public boolean updateTeach(Teach teach);

    public Teach getTeachById(int id);




}