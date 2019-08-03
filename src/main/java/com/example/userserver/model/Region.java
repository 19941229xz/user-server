package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Region" ,description = "地址信息")
@Data  // 自动生成get set 和构造器
public class Region implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 地区名称
    @ApiModelProperty(value = "地区名称" ,name = "name")
	private String name;
	// 行政编号
    @ApiModelProperty(value = "行政编号" ,name = "code")
	private String code;
	// 地区父id（上一级的主键，-1：没有上一级）
    @ApiModelProperty(value = "地区父id（上一级的主键，-1：没有上一级）" ,name = "parentId")
	private Integer parentId;
	// 地区级别（1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县 4-街道、村 5-栋、楼、户、号）
    @ApiModelProperty(value = "地区级别（1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县 4-街道、村 5-栋、楼、户、号）" ,name = "level")
	private Integer level;

}