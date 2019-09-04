package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Position;
import com.example.userserver.service.PositionService;

import javax.validation.Valid;

@Api(value = "position模块接口",description = "这是一个用户职位模块的接口文档")
@RestController
@Slf4j
public class PositionController {

	@Autowired
    PositionService positionService;

	@ApiOperation("查询所有用户职位 支持多条件分页排序查询")
    @PostMapping("/getAllPosition")
    public Object getAllPosition(@RequestBody PageParam<Position> pageParam){
        return MyRsp.success(positionService.getAllPosition(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有用户职位 支持分页和排序")
    @PostMapping("/superSearchPosition")
    public Object superSearch(@RequestBody PageParam<Position> pageParam){
        return MyRsp.success(positionService.getAllPosition(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除用户职位，同时会清空redis缓存")
    @GetMapping("/removePositionById/{id}")
    public Object removePositionByPositionName(@PathVariable("id") int id){

        return positionService.removePositionById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addPosition")
    public Object addPosition(@RequestBody @Valid Position positionParam){
        Position position=(Position)positionService.addPosition(positionParam);

        return position!=null?MyRsp.success(position).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updatePosition")
    public Object updatePosition(@RequestBody@Valid Position position){
        return positionService.updatePosition(position)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getPositionById/{id}")
    public Object getPositionById(@PathVariable("id") int id){

        Position position=positionService.getPositionById(id);
        return position!=null?MyRsp.success(position):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeletePositionByIds")
    public Object batchDeletePositionByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (positionService.removePositionById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}