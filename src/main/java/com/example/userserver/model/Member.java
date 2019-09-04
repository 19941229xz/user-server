package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Member" ,description = "会员信息")
@Data  // 自动生成get set 和构造器
public class Member implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 会员等级
    @ApiModelProperty(value = "会员等级" ,name = "memberLevel")
	private Integer memberLevel;
	// 会员积分
    @ApiModelProperty(value = "会员积分" ,name = "points")
	private Integer points;
	// 注册会员时间
    @ApiModelProperty(value = "注册会员时间" ,name = "regTime")
	private Date regTime;
	// 会员到期时间
    @ApiModelProperty(value = "会员到期时间" ,name = "deadLine")
	private Date deadLine;

}