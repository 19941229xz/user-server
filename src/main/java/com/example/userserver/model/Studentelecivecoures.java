package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Studentelecivecoures" ,description = "学生选课")
@Data  // 自动生成get set 和构造器
public class Studentelecivecoures implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 学生id（关联学生表）
    @ApiModelProperty(value = "学生id（关联学生表）" ,name = "studentId")
	private Integer studentId;
	// 课程Id（关联课程表）
    @ApiModelProperty(value = "课程Id（关联课程表）" ,name = "couresId")
	private Integer couresId;

}