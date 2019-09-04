package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Staff;
import com.example.userserver.service.StaffService;

import javax.validation.Valid;

@Api(value = "staff模块接口",description = "这是一个职工模块的接口文档")
@RestController
@Slf4j
public class StaffController {

	@Autowired
    StaffService staffService;

	@ApiOperation("查询所有职工 支持多条件分页排序查询")
    @PostMapping("/getAllStaff")
    public Object getAllStaff(@RequestBody PageParam<Staff> pageParam){
        return MyRsp.success(staffService.getAllStaff(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有职工 支持分页和排序")
    @PostMapping("/superSearchStaff")
    public Object superSearch(@RequestBody PageParam<Staff> pageParam){
        return MyRsp.success(staffService.getAllStaff(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除职工，同时会清空redis缓存")
    @GetMapping("/removeStaffById/{id}")
    public Object removeStaffByStaffName(@PathVariable("id") int id){

        return staffService.removeStaffById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addStaff")
    public Object addStaff(@RequestBody @Valid Staff staffParam){
        Staff staff=(Staff)staffService.addStaff(staffParam);

        return staff!=null?MyRsp.success(staff).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateStaff")
    public Object updateStaff(@RequestBody@Valid Staff staff){
        return staffService.updateStaff(staff)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getStaffById/{id}")
    public Object getStaffById(@PathVariable("id") int id){

        Staff staff=staffService.getStaffById(id);
        return staff!=null?MyRsp.success(staff):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteStaffByIds")
    public Object batchDeleteStaffByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (staffService.removeStaffById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}