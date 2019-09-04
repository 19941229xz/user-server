package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Banji;
import com.example.userserver.service.BanjiService;

import javax.validation.Valid;

@Api(value = "banji模块接口",description = "这是一个班级模块的接口文档")
@RestController
@Slf4j
public class BanjiController {

	@Autowired
    BanjiService banjiService;

	@ApiOperation("查询所有班级 支持多条件分页排序查询")
    @PostMapping("/getAllBanji")
    public Object getAllBanji(@RequestBody PageParam<Banji> pageParam){
        return MyRsp.success(banjiService.getAllBanji(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有班级 支持分页和排序")
    @PostMapping("/superSearchBanji")
    public Object superSearch(@RequestBody PageParam<Banji> pageParam){
        return MyRsp.success(banjiService.getAllBanji(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除班级，同时会清空redis缓存")
    @GetMapping("/removeBanjiById/{id}")
    public Object removeBanjiByBanjiName(@PathVariable("id") int id){

        return banjiService.removeBanjiById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addBanji")
    public Object addBanji(@RequestBody @Valid Banji banjiParam){
        Banji banji=(Banji)banjiService.addBanji(banjiParam);

        return banji!=null?MyRsp.success(banji).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateBanji")
    public Object updateBanji(@RequestBody@Valid Banji banji){
        return banjiService.updateBanji(banji)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getBanjiById/{id}")
    public Object getBanjiById(@PathVariable("id") int id){

        Banji banji=banjiService.getBanjiById(id);
        return banji!=null?MyRsp.success(banji):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteBanjiByIds")
    public Object batchDeleteBanjiByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (banjiService.removeBanjiById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}