package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Company;


public interface CompanyService {



	public Object getAllCompany(PageParam<Company> pageParam);

    public boolean removeCompanyById(int id);

    public Object addCompany(Company company);

    public boolean updateCompany(Company company);

    public Company getCompanyById(int id);


}