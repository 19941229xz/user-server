package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Position;

import java.util.List;

public interface PositionDao {


    List<Position> getAllPosition(Position position);

    @Delete("delete from position where id = #{id}")
    int removePositionById(int id);

    int addPosition(Position position);

    int updatePosition(Position position);

    @Select("select * from position where id =#{id}")
    Position getPositionById(int id);

    @Select("select * from position where positionName =#{positionName}")
    Position getPositionByPositionName(String positionName);


	List<Position> superSearch(String superSearchKeyWord);

}