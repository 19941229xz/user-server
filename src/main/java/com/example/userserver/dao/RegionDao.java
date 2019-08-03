package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Region;

import java.util.List;

public interface RegionDao {


    List<Region> getAllRegion(Region region);

    @Delete("delete from region where id = #{id}")
    int removeRegionById(int id);

    int addRegion(Region region);

    int updateRegion(Region region);

    @Select("select * from region where id =#{id}")
    Region getRegionById(int id);

    @Select("select * from region where regionName =#{regionName}")
    Region getRegionByRegionName(String regionName);




}