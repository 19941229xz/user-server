package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Member;

import java.util.List;

public interface MemberDao {


    List<Member> getAllMember(Member member);

    @Delete("delete from member where id = #{id}")
    int removeMemberById(int id);

    int addMember(Member member);

    int updateMember(Member member);

    @Select("select * from member where id =#{id}")
    Member getMemberById(int id);

    @Select("select * from member where memberName =#{memberName}")
    Member getMemberByMemberName(String memberName);


	List<Member> superSearch(String superSearchKeyWord);

}