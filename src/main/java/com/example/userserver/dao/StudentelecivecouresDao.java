package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Studentelecivecoures;

import java.util.List;

public interface StudentelecivecouresDao {


    List<Studentelecivecoures> getAllStudentelecivecoures(Studentelecivecoures studentelecivecoures);

    @Delete("delete from studentelecivecoures where id = #{id}")
    int removeStudentelecivecouresById(int id);

    int addStudentelecivecoures(Studentelecivecoures studentelecivecoures);

    int updateStudentelecivecoures(Studentelecivecoures studentelecivecoures);

    @Select("select * from studentelecivecoures where id =#{id}")
    Studentelecivecoures getStudentelecivecouresById(int id);

    @Select("select * from studentelecivecoures where studentelecivecouresName =#{studentelecivecouresName}")
    Studentelecivecoures getStudentelecivecouresByStudentelecivecouresName(String studentelecivecouresName);




}