package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Menu;
import com.example.userserver.service.MenuService;

import javax.validation.Valid;

@Api(value = "menu模块接口",description = "这是一个菜单模块的接口文档")
@RestController
@Slf4j
public class MenuController {

	@Autowired
    MenuService menuService;

	@ApiOperation("查询所有菜单 支持多条件分页排序查询")
    @PostMapping("/getAllMenu")
    public Object getAllMenu(@RequestBody PageParam<Menu> pageParam){
        return MyRsp.success(menuService.getAllMenu(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有菜单 支持分页和排序")
    @PostMapping("/superSearchMenu")
    public Object superSearch(@RequestBody PageParam<Menu> pageParam){
        return MyRsp.success(menuService.getAllMenu(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除菜单，同时会清空redis缓存")
    @GetMapping("/removeMenuById/{id}")
    public Object removeMenuByMenuName(@PathVariable("id") int id){

        return menuService.removeMenuById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addMenu")
    public Object addMenu(@RequestBody @Valid Menu menuParam){
        Menu menu=(Menu)menuService.addMenu(menuParam);

        return menu!=null?MyRsp.success(menu).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateMenu")
    public Object updateMenu(@RequestBody@Valid Menu menu){
        return menuService.updateMenu(menu)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getMenuById/{id}")
    public Object getMenuById(@PathVariable("id") int id){

        Menu menu=menuService.getMenuById(id);
        return menu!=null?MyRsp.success(menu):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteMenuByIds")
    public Object batchDeleteMenuByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (menuService.removeMenuById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}