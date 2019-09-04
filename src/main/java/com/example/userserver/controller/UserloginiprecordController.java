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
public class UserloginiprecordController {

	@Autowired
    UserloginiprecordService userloginiprecordService;

	@ApiOperation("查询所有用户登录ip记录 支持多条件分页排序查询")
    @PostMapping("/getAllUserloginiprecord")
    public Object getAllUserloginiprecord(@RequestBody PageParam<Userloginiprecord> pageParam){
        return MyRsp.success(userloginiprecordService.getAllUserloginiprecord(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有用户登录ip记录 支持分页和排序")
    @PostMapping("/superSearchUserloginiprecord")
    public Object superSearch(@RequestBody PageParam<Userloginiprecord> pageParam){
        return MyRsp.success(userloginiprecordService.getAllUserloginiprecord(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除用户登录ip记录，同时会清空redis缓存")
    @GetMapping("/removeUserloginiprecordById/{id}")
    public Object removeUserloginiprecordByUserloginiprecordName(@PathVariable("id") int id){

        return userloginiprecordService.removeUserloginiprecordById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addUserloginiprecord")
    public Object addUserloginiprecord(@RequestBody @Valid Userloginiprecord userloginiprecordParam){
        Userloginiprecord userloginiprecord=(Userloginiprecord)userloginiprecordService.addUserloginiprecord(userloginiprecordParam);

        return userloginiprecord!=null?MyRsp.success(userloginiprecord).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateUserloginiprecord")
    public Object updateUserloginiprecord(@RequestBody@Valid Userloginiprecord userloginiprecord){
        return userloginiprecordService.updateUserloginiprecord(userloginiprecord)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getUserloginiprecordById/{id}")
    public Object getUserloginiprecordById(@PathVariable("id") int id){

        Userloginiprecord userloginiprecord=userloginiprecordService.getUserloginiprecordById(id);
        return userloginiprecord!=null?MyRsp.success(userloginiprecord):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteUserloginiprecordByIds")
    public Object batchDeleteUserloginiprecordByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (userloginiprecordService.removeUserloginiprecordById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}