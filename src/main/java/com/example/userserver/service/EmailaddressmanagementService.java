package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Emailaddressmanagement;


public interface EmailaddressmanagementService {



	public Object getAllEmailaddressmanagement(PageParam<Emailaddressmanagement> pageParam);

    public boolean removeEmailaddressmanagementById(int id);

    public Object addEmailaddressmanagement(Emailaddressmanagement emailaddressmanagement);

    public boolean updateEmailaddressmanagement(Emailaddressmanagement emailaddressmanagement);

    public Emailaddressmanagement getEmailaddressmanagementById(int id);


}