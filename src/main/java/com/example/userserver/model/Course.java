package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Course" ,description = "课程")
@Data  // 自动生成get set 和构造器
public class Course implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 课程名称
    @ApiModelProperty(value = "课程名称" ,name = "name")
	private String name;
	// 学分
    @ApiModelProperty(value = "学分" ,name = "credit")
	private Double credit;
	// 学时
    @ApiModelProperty(value = "学时" ,name = "hour")
	private Integer hour;
	// 开设院系（关联学校院系表）
    @ApiModelProperty(value = "开设院系（关联学校院系表）" ,name = "facultyId")
	private Integer facultyId;

}