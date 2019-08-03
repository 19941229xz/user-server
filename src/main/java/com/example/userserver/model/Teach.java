package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Teach" ,description = "教师授课")
@Data  // 自动生成get set 和构造器
public class Teach implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 教师id（关联教师表）
    @ApiModelProperty(value = "教师id（关联教师表）" ,name = "teacherId")
	private Integer teacherId;
	// 课程id（关联课程表）
    @ApiModelProperty(value = "课程id（关联课程表）" ,name = "courseId")
	private Integer courseId;

}