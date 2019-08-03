package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Company" ,description = "公司")
@Data  // 自动生成get set 和构造器
public class Company implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 公司名称
    @ApiModelProperty(value = "公司名称" ,name = "name")
	private String name;
	// 公司地址（关联地区表）
    @ApiModelProperty(value = "公司地址（关联地区表）" ,name = "address")
	private Integer address;
	// 公司注册号
    @ApiModelProperty(value = "公司注册号" ,name = "regId")
	private String regId;
	// 公司注册日期
    @ApiModelProperty(value = "公司注册日期" ,name = "regDate")
	private Date regDate;
	// 公司类型(上市，未上市.....)
    @ApiModelProperty(value = "公司类型(上市，未上市.....)" ,name = "type")
	private Integer type;
	// 董事长id（对应职工表)
    @ApiModelProperty(value = "董事长id（对应职工表)" ,name = "chairman")
	private Integer chairman;
	// ceo id（对应职工表）
    @ApiModelProperty(value = "ceo id（对应职工表）" ,name = "ceo")
	private Integer ceo;
	// cbo id（对应职工表）
    @ApiModelProperty(value = "cbo id（对应职工表）" ,name = "cbo")
	private Integer cbo;
	// ceo id（对应职工表）
    @ApiModelProperty(value = "ceo id（对应职工表）" ,name = "cfo")
	private Integer cfo;

}