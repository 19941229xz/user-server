package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Menu;


public interface MenuService {



	public Object getAllMenu(PageParam<Menu> pageParam);

    public boolean removeMenuById(int id);

    public Object addMenu(Menu menu);

    public boolean updateMenu(Menu menu);

    public Menu getMenuById(int id);


}