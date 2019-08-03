package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Student" ,description = "学生")
@Data  // 自动生成get set 和构造器
public class Student implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 所属学校id（关联学校表）
    @ApiModelProperty(value = "所属学校id（关联学校表）" ,name = "schoolId")
	private Integer schoolId;
	// 所属院系id（关联院系表）
    @ApiModelProperty(value = "所属院系id（关联院系表）" ,name = "faculty")
	private Integer faculty;
	// 学生姓名
    @ApiModelProperty(value = "学生姓名" ,name = "studentName")
	private String studentName;
	// 所属班级id（关联班级表）
    @ApiModelProperty(value = "所属班级id（关联班级表）" ,name = "banjiId")
	private Integer banjiId;
	// 性别（1、男，2女）
    @ApiModelProperty(value = "性别（1、男，2女）" ,name = "gender")
	private Integer gender;
	// 年龄
    @ApiModelProperty(value = "年龄" ,name = "age")
	private Integer age;
	// 学分
    @ApiModelProperty(value = "学分" ,name = "credit")
	private Double credit;
	// 所属地id（关联地区表）
    @ApiModelProperty(value = "所属地id（关联地区表）" ,name = "regionId")
	private Integer regionId;
	// 选课id（关联选课表）
    @ApiModelProperty(value = "选课id（关联选课表）" ,name = "electiveCouresId")
	private Integer electiveCouresId;

}