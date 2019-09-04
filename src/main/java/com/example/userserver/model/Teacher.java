package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Teacher" ,description = "教师")
@Data  // 自动生成get set 和构造器
public class Teacher implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 教师名字
    @ApiModelProperty(value = "教师名字" ,name = "name")
	private String name;
	// 性别（1、男，2、女）
    @ApiModelProperty(value = "性别（1、男，2、女）" ,name = "gender")
	private Integer gender;
	// 年龄
    @ApiModelProperty(value = "年龄" ,name = "age")
	private Integer age;
	// 教龄
    @ApiModelProperty(value = "教龄" ,name = "teachingAge")
	private Integer teachingAge;
	// 教师职位id（关联教师职位表）
    @ApiModelProperty(value = "教师职位id（关联教师职位表）" ,name = "positionId")
	private Integer positionId;
	// 授课id（关联授课表）
    @ApiModelProperty(value = "授课id（关联授课表）" ,name = "teachId")
	private Integer teachId;
	// 所属院系id（关联院系表）
    @ApiModelProperty(value = "所属院系id（关联院系表）" ,name = "facultyId")
	private Integer facultyId;
	// 所属公司id（关联公司表）
    @ApiModelProperty(value = "所属公司id（关联公司表）" ,name = "companyId")
	private Integer companyId;
	// 教师地址id（关联地区表）
    @ApiModelProperty(value = "教师地址id（关联地区表）" ,name = "regionId")
	private Integer regionId;
	// 岗位id
    @ApiModelProperty(value = "岗位id" ,name = "jobTypeId")
	private Integer jobTypeId;

}