package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Region;


public interface RegionService {



	public Object getAllRegion(PageParam<Region> pageParam);

    public boolean removeRegionById(int id);

    public Object addRegion(Region region);

    public boolean updateRegion(Region region);

    public Region getRegionById(int id);




}