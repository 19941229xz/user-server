package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Studentelecivecoures;


public interface StudentelecivecouresService {



	public Object getAllStudentelecivecoures(PageParam<Studentelecivecoures> pageParam);

    public boolean removeStudentelecivecouresById(int id);

    public Object addStudentelecivecoures(Studentelecivecoures studentelecivecoures);

    public boolean updateStudentelecivecoures(Studentelecivecoures studentelecivecoures);

    public Studentelecivecoures getStudentelecivecouresById(int id);


}