package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Banji" ,description = "班级")
@Data  // 自动生成get set 和构造器
public class Banji implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 班级人数
    @ApiModelProperty(value = "班级人数" ,name = "size")
	private Integer size;
	// 班主任id（关联教师表）
    @ApiModelProperty(value = "班主任id（关联教师表）" ,name = "headTeacherId")
	private Integer headTeacherId;
	// 男生人数
    @ApiModelProperty(value = "男生人数" ,name = "numOfBoys")
	private Integer numOfBoys;
	// 女生人数
    @ApiModelProperty(value = "女生人数" ,name = "numOfGirls")
	private Integer numOfGirls;
	// 所属院系id（关联院系表）
    @ApiModelProperty(value = "所属院系id（关联院系表）" ,name = "facultyId")
	private Integer facultyId;
	// 班级名称
    @ApiModelProperty(value = "班级名称" ,name = "banjiName")
	private String banjiName;
	// 班主任姓名
    @ApiModelProperty(value = "班主任姓名" ,name = "headTeacherName")
	private String headTeacherName;
	// 所属岗位id
    @ApiModelProperty(value = "所属岗位id" ,name = "jobTypeId")
	private Integer jobTypeId;
	// 开班时间
    @ApiModelProperty(value = "开班时间" ,name = "startDate")
	private Date startDate;
	// 预计结业时间
    @ApiModelProperty(value = "预计结业时间" ,name = "preEndDate")
	private Date preEndDate;
	// 实际结业时间
    @ApiModelProperty(value = "实际结业时间" ,name = "realEndDate")
	private Date realEndDate;
	// 代课老师id
    @ApiModelProperty(value = "代课老师id" ,name = "teacherId")
	private Integer teacherId;
	// 代课老师姓名
    @ApiModelProperty(value = "代课老师姓名" ,name = "teacherName")
	private String teacherName;

}