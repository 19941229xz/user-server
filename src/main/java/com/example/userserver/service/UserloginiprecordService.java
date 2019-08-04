package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Userloginiprecord;


public interface UserloginiprecordService {



	public Object getAllUserloginiprecord(PageParam<Userloginiprecord> pageParam);

    public boolean removeUserloginiprecordById(int id);

    public Object addUserloginiprecord(Userloginiprecord userloginiprecord);

    public boolean updateUserloginiprecord(Userloginiprecord userloginiprecord);

    public Userloginiprecord getUserloginiprecordById(int id);


}