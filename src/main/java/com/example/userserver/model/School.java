package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "School" ,description = "学校")
@Data  // 自动生成get set 和构造器
public class School implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 学校名称
    @ApiModelProperty(value = "学校名称" ,name = "name")
	private String name;
	// 学校类型（1小学，2初中，3高中，4职高，5本科，6大专 7其他）
    @ApiModelProperty(value = "学校类型（1小学，2初中，3高中，4职高，5本科，6大专 7其他）" ,name = "type")
	private Integer type;
	// 学校地址，用于关联地区表
    @ApiModelProperty(value = "学校地址，用于关联地区表" ,name = "address")
	private Integer address;
	// 学费情况
    @ApiModelProperty(value = "学费情况" ,name = "tuition")
	private Double tuition;
	// 师资条件Id（用于关联教师职位）
    @ApiModelProperty(value = "师资条件Id（用于关联教师职位）" ,name = "teachePositionId")
	private Integer teachePositionId;
	// 服务热线
    @ApiModelProperty(value = "服务热线" ,name = "serviceHotline")
	private Integer serviceHotline;
	// 地址邮箱
    @ApiModelProperty(value = "地址邮箱" ,name = "email")
	private String email;

}