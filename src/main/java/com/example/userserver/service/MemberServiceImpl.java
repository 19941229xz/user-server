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
import com.example.userserver.dao.MemberDao;
import com.example.userserver.model.Member;

import java.util.List;

@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {


	@Autowired
    MemberDao memberDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllMember(PageParam<Member> pageParam){
    
    	if(StringUtils.isEmpty(pageParam.getSuperSearchKeyWord())){
            PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Member> memberList=memberDao.getAllMember(pageParam.getModel());
            PageInfo<Member> memberPageInfo = new PageInfo<Member>(memberList);
    
            return memberPageInfo;
        }else{
        	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
            for(int i=0;i<pageParam.getOrderParams().length;i++){
                PageHelper.orderBy(pageParam.getOrderParams()[i]);
            }
    
    
            List<Member> memberList=memberDao.superSearch(pageParam.getSuperSearchKeyWord());
            PageInfo<Member> memberPageInfo = new PageInfo<Member>(memberList);
    
            return memberPageInfo;
        }
    
    	
    
    }

	@CacheEvict(value = "members",key = "#p0")
    @Override
    public boolean removeMemberById(int id){
    	return memberDao.removeMemberById(id)==1;
    }

	@CachePut(value = "members",key = "#p0.id")
    @Override
    public Object addMember(Member member){
        memberDao.addMember(member);

        return memberDao.getMemberById(member.getId());
    }

	@CacheEvict(value = "members",key = "#p0.id")
	@Override
    public boolean updateMember(Member member){
    	if(StringUtils.isEmpty(member.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改member时，id不能为空");
        }

        return memberDao.updateMember(member)==1;
    }

	@Cacheable(key = "#p0",value="members")
    @Override
    @Transactional(readOnly = true)
    public Member getMemberById(int id){
    	return memberDao.getMemberById(id);
    	
    }
    
    

}
