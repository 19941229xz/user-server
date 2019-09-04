package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Region;
import com.example.userserver.service.RegionService;

import javax.validation.Valid;

@Api(value = "region模块接口",description = "这是一个地址信息模块的接口文档")
@RestController
@Slf4j
public class RegionController {

	@Autowired
    RegionService regionService;

	@ApiOperation("查询所有地址信息 支持多条件分页排序查询")
    @PostMapping("/getAllRegion")
    public Object getAllRegion(@RequestBody PageParam<Region> pageParam){
        return MyRsp.success(regionService.getAllRegion(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有地址信息 支持分页和排序")
    @PostMapping("/superSearchRegion")
    public Object superSearch(@RequestBody PageParam<Region> pageParam){
        return MyRsp.success(regionService.getAllRegion(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除地址信息，同时会清空redis缓存")
    @GetMapping("/removeRegionById/{id}")
    public Object removeRegionByRegionName(@PathVariable("id") int id){

        return regionService.removeRegionById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addRegion")
    public Object addRegion(@RequestBody @Valid Region regionParam){
        Region region=(Region)regionService.addRegion(regionParam);

        return region!=null?MyRsp.success(region).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateRegion")
    public Object updateRegion(@RequestBody@Valid Region region){
        return regionService.updateRegion(region)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getRegionById/{id}")
    public Object getRegionById(@PathVariable("id") int id){

        Region region=regionService.getRegionById(id);
        return region!=null?MyRsp.success(region):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteRegionByIds")
    public Object batchDeleteRegionByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (regionService.removeRegionById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}