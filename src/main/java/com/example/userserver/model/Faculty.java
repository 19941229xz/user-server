package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Faculty" ,description = "院系")
@Data  // 自动生成get set 和构造器
public class Faculty implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 院系名称
    @ApiModelProperty(value = "院系名称" ,name = "name")
	private String name;
	// 所属学校id（关联学校表）
    @ApiModelProperty(value = "所属学校id（关联学校表）" ,name = "schId")
	private Integer schId;

}