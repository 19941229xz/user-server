package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.User;
import com.example.userserver.service.UserService;

import javax.validation.Valid;

@Api(value = "user模块接口",description = "这是一个用户信息模块的接口文档")
@RestController
@Slf4j
public class UserController {

	@Autowired
    UserService userService;

	@ApiOperation("查询所有用户信息 支持多条件分页排序查询")
    @PostMapping("/getAllUser")
    public Object getAllUser(@RequestBody PageParam<User> pageParam){
        return MyRsp.success(userService.getAllUser(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有用户信息 支持分页和排序")
    @PostMapping("/superSearchUser")
    public Object superSearch(@RequestBody PageParam<User> pageParam){
        return MyRsp.success(userService.getAllUser(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除用户信息，同时会清空redis缓存")
    @GetMapping("/removeUserById/{id}")
    public Object removeUserByUserName(@PathVariable("id") int id){

        return userService.removeUserById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addUser")
    public Object addUser(@RequestBody @Valid User userParam){
        User user=(User)userService.addUser(userParam);

        return user!=null?MyRsp.success(user).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateUser")
    public Object updateUser(@RequestBody@Valid User user){
        return userService.updateUser(user)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getUserById/{id}")
    public Object getUserById(@PathVariable("id") int id){

        User user=userService.getUserById(id);
        return user!=null?MyRsp.success(user):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteUserByIds")
    public Object batchDeleteUserByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (userService.removeUserById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}