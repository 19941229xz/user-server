package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Userloginiprecord" ,description = "用户登录ip记录")
@Data  // 自动生成get set 和构造器
public class Userloginiprecord implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 用户id
    @ApiModelProperty(value = "用户id" ,name = "userId")
	private Integer userId;
	// 登录ip
    @ApiModelProperty(value = "登录ip" ,name = "loginIp")
	private String loginIp;
	// 登录总次数
    @ApiModelProperty(value = "登录总次数" ,name = "totalLogins")
	private Integer totalLogins;

}