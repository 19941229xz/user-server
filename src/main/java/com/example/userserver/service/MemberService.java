package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Member;


public interface MemberService {



	public Object getAllMember(PageParam<Member> pageParam);

    public boolean removeMemberById(int id);

    public Object addMember(Member member);

    public boolean updateMember(Member member);

    public Member getMemberById(int id);


}