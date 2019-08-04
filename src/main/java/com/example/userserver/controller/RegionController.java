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

    @GetMapping("/removeRegionById/{id}")
    public Object removeRegionByRegionName(@PathVariable("id") int id){

        return regionService.removeRegionById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addRegion")
    public Object addRegion(@RequestBody @Valid Region regionParam){
        Region region=(Region)regionService.addRegion(regionParam);

        return region!=null?MyRsp.success(region).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateRegion")
    public Object updateRegion(@RequestBody@Valid Region region){
        return regionService.updateRegion(region)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getRegionById/{id}")
    public Object getRegionById(@PathVariable("id") int id){

        Region region=regionService.getRegionById(id);
        return region!=null?MyRsp.success(region):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
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