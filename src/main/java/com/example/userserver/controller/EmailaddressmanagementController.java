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
    
    @ApiOperation("按照关键字高级检索所有 支持分页和排序")
    @PostMapping("/superSearchEmailaddressmanagement")
    public Object superSearch(@RequestBody PageParam<Emailaddressmanagement> pageParam){
        return MyRsp.success(emailaddressmanagementService.getAllEmailaddressmanagement(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除，同时会清空redis缓存")
    @GetMapping("/removeEmailaddressmanagementById/{id}")
    public Object removeEmailaddressmanagementByEmailaddressmanagementName(@PathVariable("id") int id){

        return emailaddressmanagementService.removeEmailaddressmanagementById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addEmailaddressmanagement")
    public Object addEmailaddressmanagement(@RequestBody @Valid Emailaddressmanagement emailaddressmanagementParam){
        Emailaddressmanagement emailaddressmanagement=(Emailaddressmanagement)emailaddressmanagementService.addEmailaddressmanagement(emailaddressmanagementParam);

        return emailaddressmanagement!=null?MyRsp.success(emailaddressmanagement).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateEmailaddressmanagement")
    public Object updateEmailaddressmanagement(@RequestBody@Valid Emailaddressmanagement emailaddressmanagement){
        return emailaddressmanagementService.updateEmailaddressmanagement(emailaddressmanagement)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getEmailaddressmanagementById/{id}")
    public Object getEmailaddressmanagementById(@PathVariable("id") int id){

        Emailaddressmanagement emailaddressmanagement=emailaddressmanagementService.getEmailaddressmanagementById(id);
        return emailaddressmanagement!=null?MyRsp.success(emailaddressmanagement):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteEmailaddressmanagementByIds")
    public Object batchDeleteEmailaddressmanagementByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (emailaddressmanagementService.removeEmailaddressmanagementById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}