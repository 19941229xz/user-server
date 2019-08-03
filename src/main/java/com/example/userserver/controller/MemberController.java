package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Member;
import com.example.userserver.service.MemberService;

import javax.validation.Valid;

@Api(value = "member模块接口",description = "这是一个会员信息模块的接口文档")
@RestController
@Slf4j
public class MemberController {

	@Autowired
    MemberService memberService;

	@ApiOperation("查询所有会员信息 支持多条件分页排序查询")
    @PostMapping("/getAllMember")
    public Object getAllMember(@RequestBody PageParam<Member> pageParam){
        return MyRsp.success(memberService.getAllMember(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeMemberById/{id}")
    public Object removeMemberByMemberName(@PathVariable("id") int id){

        return memberService.removeMemberById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addMember")
    public Object addMember(@RequestBody @Valid Member memberParam){
        Member member=(Member)memberService.addMember(memberParam);

        return member!=null?MyRsp.success(member).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateMember")
    public Object updateMember(@RequestBody@Valid Member member){
        return memberService.updateMember(member)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getMemberById/{id}")
    public Object getMemberById(@PathVariable("id") int id){

        Member member=memberService.getMemberById(id);
        return member!=null?MyRsp.success(member):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}