package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Position" ,description = "用户职位")
@Data  // 自动生成get set 和构造器
public class Position implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 职位名称（添加记录的时候设置每条记录id分别代表的职位）
    @ApiModelProperty(value = "职位名称（添加记录的时候设置每条记录id分别代表的职位）" ,name = "name")
	private String name;

}