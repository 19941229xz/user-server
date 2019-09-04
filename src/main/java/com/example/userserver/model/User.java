package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "User" ,description = "用户信息")
@Data  // 自动生成get set 和构造器
public class User implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 头像
    @ApiModelProperty(value = "头像" ,name = "headImg")
	private String headImg;
	// 用户名
    @ApiModelProperty(value = "用户名" ,name = "userName")
	private String userName;
	// 密码
    @ApiModelProperty(value = "密码" ,name = "password")
	private String password;
	// 昵称
    @ApiModelProperty(value = "昵称" ,name = "nickName")
	private String nickName;
	// 真实姓名
    @ApiModelProperty(value = "真实姓名" ,name = "realName")
	private String realName;
	// 性别 1男  2女
    @ApiModelProperty(value = "性别 1男  2女" ,name = "gender")
	private Integer gender;
	// 生日
    @ApiModelProperty(value = "生日" ,name = "birthday")
	private Date birthday;
	// 年龄
    @ApiModelProperty(value = "年龄" ,name = "age")
	private Integer age;
	// qq号
    @ApiModelProperty(value = "qq号" ,name = "qq")
	private String qq;
	// 微信号
    @ApiModelProperty(value = "微信号" ,name = "weChat")
	private String weChat;
	// 手机号
    @ApiModelProperty(value = "手机号" ,name = "phoneNum")
	private String phoneNum;
	// 邮箱
    @ApiModelProperty(value = "邮箱" ,name = "email")
	private String email;
	// 注册ip
    @ApiModelProperty(value = "注册ip" ,name = "regIp")
	private String regIp;
	// 注册时间
    @ApiModelProperty(value = "注册时间" ,name = "regTime")
	private Date regTime;
	// （上一次）登出ip
    @ApiModelProperty(value = "（上一次）登出ip" ,name = "lastLogoutIp")
	private String lastLogoutIp;
	// （本次）登录ip
    @ApiModelProperty(value = "（本次）登录ip" ,name = "loginIp")
	private String loginIp;
	// 最后在线/登出时刻
    @ApiModelProperty(value = "最后在线/登出时刻" ,name = "lastOnlineTime")
	private Date lastOnlineTime;
	// （本次）登录时刻
    @ApiModelProperty(value = "（本次）登录时刻" ,name = "loginTime")
	private Date loginTime;
	// 本次登录时长
    @ApiModelProperty(value = "本次登录时长" ,name = "timeOfThisLogin")
	private Long timeOfThisLogin;
	// 在线总时长
    @ApiModelProperty(value = "在线总时长" ,name = "totalOnlineTime")
	private Long totalOnlineTime;
	// 用户身份id（1用户 2管理员）
    @ApiModelProperty(value = "用户身份id（1用户 2管理员）" ,name = "roleId")
	private Integer roleId;
	// 会员id
    @ApiModelProperty(value = "会员id" ,name = "memberId")
	private Integer memberId;
	// 收货地址id（关联地区表）
    @ApiModelProperty(value = "收货地址id（关联地区表）" ,name = "shippingAddress")
	private Integer shippingAddress;
	// 状态（1已激活、2未激活、3冻结、4已删除）
    @ApiModelProperty(value = "状态（1已激活、2未激活、3冻结、4已删除）" ,name = "status")
	private Integer status;
	// 常用登录ip
    @ApiModelProperty(value = "常用登录ip" ,name = "commonLoginIp")
	private String commonLoginIp;
	// 支付密码
    @ApiModelProperty(value = "支付密码" ,name = "payPassword")
	private String payPassword;
	// 用户签名
    @ApiModelProperty(value = "用户签名" ,name = "signature")
	private String signature;
	// 用户地址
    @ApiModelProperty(value = "用户地址" ,name = "userAddress")
	private String userAddress;
	// 学生id
    @ApiModelProperty(value = "学生id" ,name = "studentId")
	private Integer studentId;
	// 公司id
    @ApiModelProperty(value = "公司id" ,name = "companyId")
	private Integer companyId;
	// 学校id
    @ApiModelProperty(value = "学校id" ,name = "schoolId")
	private Integer schoolId;
	// 学院id
    @ApiModelProperty(value = "学院id" ,name = "facultyId")
	private Integer facultyId;
	// 班级id
    @ApiModelProperty(value = "班级id" ,name = "banjiId")
	private Integer banjiId;
	// 教师id
    @ApiModelProperty(value = "教师id" ,name = "teacherId")
	private Integer teacherId;
	// 部门id
    @ApiModelProperty(value = "部门id" ,name = "departmentId")
	private Integer departmentId;
	// 职工id
    @ApiModelProperty(value = "职工id" ,name = "staffId")
	private Integer staffId;
	// 籍贯（关联地区表）
    @ApiModelProperty(value = "籍贯（关联地区表）" ,name = "birthPlace")
	private Integer birthPlace;
	// 政治面貌（1党员 2团员 3其他）
    @ApiModelProperty(value = "政治面貌（1党员 2团员 3其他）" ,name = "politicalStatus")
	private Integer politicalStatus;
	// 宗教信仰（1基督教 2伊斯兰教 3佛教 4其他）
    @ApiModelProperty(value = "宗教信仰（1基督教 2伊斯兰教 3佛教 4其他）" ,name = "religion")
	private Integer religion;
	// 邀请码
    @ApiModelProperty(value = "邀请码" ,name = "inviteCode")
	private String inviteCode;
	// 邀请人数
    @ApiModelProperty(value = "邀请人数" ,name = "inviteNum")
	private Integer inviteNum;

}