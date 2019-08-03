package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Emailaddressmanagement;
import com.example.userserver.service.EmailaddressmanagementService;

import javax.validation.Valid;

@Api(value = "emailaddressmanagement模块接口",description = "这是一个模块的接口文档")
@RestController
@Slf4j
public class EmailaddressmanagementController {

	@Autowired
    EmailaddressmanagementService emailaddressmanagementService;

	@ApiOperation("查询所有 支持多条件分页排序查询")
    @PostMapping("/getAllEmailaddressmanagement")
    public Object getAllEmailaddressmanagement(@RequestBody PageParam<Emailaddressmanagement> pageParam){
        return MyRsp.success(emailaddressmanagementService.getAllEmailaddressmanagement(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeEmailaddressmanagementById/{id}")
    public Object removeEmailaddressmanagementByEmailaddressmanagementName(@PathVariable("id") int id){

        return emailaddressmanagementService.removeEmailaddressmanagementById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addEmailaddressmanagement")
    public Object addEmailaddressmanagement(@RequestBody @Valid Emailaddressmanagement emailaddressmanagementParam){
        Emailaddressmanagement emailaddressmanagement=(Emailaddressmanagement)emailaddressmanagementService.addEmailaddressmanagement(emailaddressmanagementParam);

        return emailaddressmanagement!=null?MyRsp.success(emailaddressmanagement).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateEmailaddressmanagement")
    public Object updateEmailaddressmanagement(@RequestBody@Valid Emailaddressmanagement emailaddressmanagement){
        return emailaddressmanagementService.updateEmailaddressmanagement(emailaddressmanagement)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getEmailaddressmanagementById/{id}")
    public Object getEmailaddressmanagementById(@PathVariable("id") int id){

        Emailaddressmanagement emailaddressmanagement=emailaddressmanagementService.getEmailaddressmanagementById(id);
        return emailaddressmanagement!=null?MyRsp.success(emailaddressmanagement):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}