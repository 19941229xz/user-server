package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Banji;

import java.util.List;

public interface BanjiDao {


    List<Banji> getAllBanji(Banji banji);

    @Delete("delete from banji where id = #{id}")
    int removeBanjiById(int id);

    int addBanji(Banji banji);

    int updateBanji(Banji banji);

    @Select("select * from banji where id =#{id}")
    Banji getBanjiById(int id);

    @Select("select * from banji where banjiName =#{banjiName}")
    Banji getBanjiByBanjiName(String banjiName);


	List<Banji> superSearch(String superSearchKeyWord);

}