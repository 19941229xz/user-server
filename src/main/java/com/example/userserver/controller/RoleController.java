package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Role;
import com.example.userserver.service.RoleService;

import javax.validation.Valid;

@Api(value = "role模块接口",description = "这是一个角色模块的接口文档")
@RestController
@Slf4j
public class RoleController {

	@Autowired
    RoleService roleService;

	@ApiOperation("查询所有角色 支持多条件分页排序查询")
    @PostMapping("/getAllRole")
    public Object getAllRole(@RequestBody PageParam<Role> pageParam){
        return MyRsp.success(roleService.getAllRole(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有角色 支持分页和排序")
    @PostMapping("/superSearchRole")
    public Object superSearch(@RequestBody PageParam<Role> pageParam){
        return MyRsp.success(roleService.getAllRole(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除角色，同时会清空redis缓存")
    @GetMapping("/removeRoleById/{id}")
    public Object removeRoleByRoleName(@PathVariable("id") int id){

        return roleService.removeRoleById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addRole")
    public Object addRole(@RequestBody @Valid Role roleParam){
        Role role=(Role)roleService.addRole(roleParam);

        return role!=null?MyRsp.success(role).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateRole")
    public Object updateRole(@RequestBody@Valid Role role){
        return roleService.updateRole(role)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getRoleById/{id}")
    public Object getRoleById(@PathVariable("id") int id){

        Role role=roleService.getRoleById(id);
        return role!=null?MyRsp.success(role):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteRoleByIds")
    public Object batchDeleteRoleByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (roleService.removeRoleById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}