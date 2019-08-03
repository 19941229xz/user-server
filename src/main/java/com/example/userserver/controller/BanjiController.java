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

    @GetMapping("/removeBanjiById/{id}")
    public Object removeBanjiByBanjiName(@PathVariable("id") int id){

        return banjiService.removeBanjiById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addBanji")
    public Object addBanji(@RequestBody @Valid Banji banjiParam){
        Banji banji=(Banji)banjiService.addBanji(banjiParam);

        return banji!=null?MyRsp.success(banji).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateBanji")
    public Object updateBanji(@RequestBody@Valid Banji banji){
        return banjiService.updateBanji(banji)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getBanjiById/{id}")
    public Object getBanjiById(@PathVariable("id") int id){

        Banji banji=banjiService.getBanjiById(id);
        return banji!=null?MyRsp.success(banji):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}