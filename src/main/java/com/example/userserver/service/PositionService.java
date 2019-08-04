package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Position;


public interface PositionService {



	public Object getAllPosition(PageParam<Position> pageParam);

    public boolean removePositionById(int id);

    public Object addPosition(Position position);

    public boolean updatePosition(Position position);

    public Position getPositionById(int id);


}