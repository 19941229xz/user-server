package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Menu;

import java.util.List;

public interface MenuDao {


    List<Menu> getAllMenu(Menu menu);

    @Delete("delete from menu where id = #{id}")
    int removeMenuById(int id);

    int addMenu(Menu menu);

    int updateMenu(Menu menu);

    @Select("select * from menu where id =#{id}")
    Menu getMenuById(int id);

    @Select("select * from menu where menuName =#{menuName}")
    Menu getMenuByMenuName(String menuName);


	List<Menu> superSearch(String superSearchKeyWord);

}