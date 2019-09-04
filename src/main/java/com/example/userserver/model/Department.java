package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Department" ,description = "部门")
@Data  // 自动生成get set 和构造器
public class Department implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 部门名称
    @ApiModelProperty(value = "部门名称" ,name = "name")
	private String name;
	// 部门负责人id（对应职工表）
    @ApiModelProperty(value = "部门负责人id（对应职工表）" ,name = "principalId")
	private Integer principalId;
	// 员工人数
    @ApiModelProperty(value = "员工人数" ,name = "numOfStaff")
	private Integer numOfStaff;

}