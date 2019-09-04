package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Userloginiprecord;

import java.util.List;

public interface UserloginiprecordDao {


    List<Userloginiprecord> getAllUserloginiprecord(Userloginiprecord userloginiprecord);

    @Delete("delete from userloginiprecord where id = #{id}")
    int removeUserloginiprecordById(int id);

    int addUserloginiprecord(Userloginiprecord userloginiprecord);

    int updateUserloginiprecord(Userloginiprecord userloginiprecord);

    @Select("select * from userloginiprecord where id =#{id}")
    Userloginiprecord getUserloginiprecordById(int id);

    @Select("select * from userloginiprecord where userloginiprecordName =#{userloginiprecordName}")
    Userloginiprecord getUserloginiprecordByUserloginiprecordName(String userloginiprecordName);


	List<Userloginiprecord> superSearch(String superSearchKeyWord);

}