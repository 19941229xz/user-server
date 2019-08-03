package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Userloginiprecord;
import com.example.userserver.service.UserloginiprecordService;

import javax.validation.Valid;

@Api(value = "userloginiprecord模块接口",description = "这是一个用户登录ip记录模块的接口文档")
@RestController
@Slf4j
@CrossOrigin
public class UserloginiprecordController {

	@Autowired
    UserloginiprecordService userloginiprecordService;

	@ApiOperation("查询所有用户登录ip记录 支持多条件分页排序查询")
    @PostMapping("/getAllUserloginiprecord")
    public Object getAllUserloginiprecord(@RequestBody PageParam<Userloginiprecord> pageParam){
        return MyRsp.success(userloginiprecordService.getAllUserloginiprecord(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeUserloginiprecordById/{id}")
    public Object removeUserloginiprecordByUserloginiprecordName(@PathVariable("id") int id){

        return userloginiprecordService.removeUserloginiprecordById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addUserloginiprecord")
    public Object addUserloginiprecord(@RequestBody @Valid Userloginiprecord userloginiprecordParam){
        Userloginiprecord userloginiprecord=(Userloginiprecord)userloginiprecordService.addUserloginiprecord(userloginiprecordParam);

        return userloginiprecord!=null?MyRsp.success(userloginiprecord).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateUserloginiprecord")
    public Object updateUserloginiprecord(@RequestBody@Valid Userloginiprecord userloginiprecord){
        return userloginiprecordService.updateUserloginiprecord(userloginiprecord)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getUserloginiprecordById/{id}")
    public Object getUserloginiprecordById(@PathVariable("id") int id){

        Userloginiprecord userloginiprecord=userloginiprecordService.getUserloginiprecordById(id);
        return userloginiprecord!=null?MyRsp.success(userloginiprecord):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}