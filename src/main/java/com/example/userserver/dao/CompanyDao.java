package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Company;

import java.util.List;

public interface CompanyDao {


    List<Company> getAllCompany(Company company);

    @Delete("delete from company where id = #{id}")
    int removeCompanyById(int id);

    int addCompany(Company company);

    int updateCompany(Company company);

    @Select("select * from company where id =#{id}")
    Company getCompanyById(int id);

    @Select("select * from company where companyName =#{companyName}")
    Company getCompanyByCompanyName(String companyName);




}