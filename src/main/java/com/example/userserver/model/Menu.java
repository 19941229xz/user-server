package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Menu" ,description = "菜单")
@Data  // 自动生成get set 和构造器
public class Menu implements Serializable {
	// 主键
    @ApiModelProperty(value = "主键" ,name = "id")
	private Integer id;
	// 菜单路由
    @ApiModelProperty(value = "菜单路由" ,name = "menuRoute")
	private String menuRoute;
	// 菜单中文名称
    @ApiModelProperty(value = "菜单中文名称" ,name = "menuNameCN")
	private String menuNameCN;

}