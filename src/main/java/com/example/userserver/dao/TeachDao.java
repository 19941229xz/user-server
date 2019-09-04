package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Teach;

import java.util.List;

public interface TeachDao {


    List<Teach> getAllTeach(Teach teach);

    @Delete("delete from teach where id = #{id}")
    int removeTeachById(int id);

    int addTeach(Teach teach);

    int updateTeach(Teach teach);

    @Select("select * from teach where id =#{id}")
    Teach getTeachById(int id);

    @Select("select * from teach where teachName =#{teachName}")
    Teach getTeachByTeachName(String teachName);


	List<Teach> superSearch(String superSearchKeyWord);

}