package com.example.userserver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.example.userserver.common.HttpCode;
import com.example.userserver.common.MyException;
import com.example.userserver.common.PageParam;
import com.example.userserver.dao.StudentelecivecouresDao;
import com.example.userserver.model.Studentelecivecoures;

import java.util.List;

@Slf4j
@Service
@Transactional
public class StudentelecivecouresServiceImpl implements StudentelecivecouresService {


	@Autowired
    StudentelecivecouresDao studentelecivecouresDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllStudentelecivecoures(PageParam<Studentelecivecoures> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Studentelecivecoures> studentelecivecouresList=studentelecivecouresDao.getAllStudentelecivecoures(pageParam.getModel());
        PageInfo<Studentelecivecoures> studentelecivecouresPageInfo = new PageInfo<Studentelecivecoures>(studentelecivecouresList);

        return studentelecivecouresPageInfo;
    
    }

	@CacheEvict(value = "studentelecivecouress",key = "#p0")
    @Override
    public boolean removeStudentelecivecouresById(int id){
    	return studentelecivecouresDao.removeStudentelecivecouresById(id)==1;
    }

	@CachePut(value = "studentelecivecouress",key = "#p0.id")
    @Override
    public Object addStudentelecivecoures(Studentelecivecoures studentelecivecoures){
        studentelecivecouresDao.addStudentelecivecoures(studentelecivecoures);

        return studentelecivecouresDao.getStudentelecivecouresById(studentelecivecoures.getId());
    }

	@CacheEvict(value = "studentelecivecouress",key = "#p0.id")
	@Override
    public boolean updateStudentelecivecoures(Studentelecivecoures studentelecivecoures){
    	if(StringUtils.isEmpty(studentelecivecoures.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改studentelecivecoures时，id不能为空");
        }

        return studentelecivecouresDao.updateStudentelecivecoures(studentelecivecoures)==1;
    }

	@Cacheable(key = "#p0",value="studentelecivecouress")
    @Override
    @Transactional(readOnly = true)
    public Studentelecivecoures getStudentelecivecouresById(int id){
    	return studentelecivecouresDao.getStudentelecivecouresById(id);
    	
    }




}
