package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Banji;


public interface BanjiService {



	public Object getAllBanji(PageParam<Banji> pageParam);

    public boolean removeBanjiById(int id);

    public Object addBanji(Banji banji);

    public boolean updateBanji(Banji banji);

    public Banji getBanjiById(int id);


}