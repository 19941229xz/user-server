package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Emailaddressmanagement" ,description = "")
@Data  // 自动生成get set 和构造器
public class Emailaddressmanagement implements Serializable {
	// 
    @ApiModelProperty(value = "" ,name = "id")
	private Integer id;
	// 发送邮件的本地账号
    @ApiModelProperty(value = "发送邮件的本地账号" ,name = "emailAddress")
	private String emailAddress;
	// 授权码
    @ApiModelProperty(value = "授权码" ,name = "authorizationCode")
	private String authorizationCode;

}