package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Staff" ,description = "职工")
@Data  // 自动生成get set 和构造器
public class Staff implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 职工名称
    @ApiModelProperty(value = "职工名称" ,name = "name")
	private String name;
	// 性别（1男、2女）
    @ApiModelProperty(value = "性别（1男、2女）" ,name = "gender")
	private Integer gender;
	// 年龄
    @ApiModelProperty(value = "年龄" ,name = "age")
	private Integer age;
	// 所属部门id(对应部门表Id)
    @ApiModelProperty(value = "所属部门id(对应部门表Id)" ,name = "departmentId")
	private Integer departmentId;
	// 职工职位(对应用户职位表)
    @ApiModelProperty(value = "职工职位(对应用户职位表)" ,name = "position")
	private Integer position;
	// 电话
    @ApiModelProperty(value = "电话" ,name = "phone")
	private String phone;
	// 邮箱
    @ApiModelProperty(value = "邮箱" ,name = "email")
	private String email;

}