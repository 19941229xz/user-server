package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Role" ,description = "角色")
@Data  // 自动生成get set 和构造器
public class Role implements Serializable {
	// 主键
    @ApiModelProperty(value = "主键" ,name = "id")
	private Integer id;
	// 角色英文名
    @ApiModelProperty(value = "角色英文名" ,name = "roleName")
	private String roleName;
	// 角色中文名称
    @ApiModelProperty(value = "角色中文名称" ,name = "roleNameCN")
	private String roleNameCN;
	// 角色级别
    @ApiModelProperty(value = "角色级别" ,name = "roleLevel")
	private Integer roleLevel;

}