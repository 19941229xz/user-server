package com.example.userserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Comment" ,description = "用户评论")
@Data  // 自动生成get set 和构造器
public class Comment implements Serializable {
	// 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 评论图片
    @ApiModelProperty(value = "评论图片" ,name = "img")
	private String img;
	// 评论类别（1好评 2中评 3差评）
    @ApiModelProperty(value = "评论类别（1好评 2中评 3差评）" ,name = "type")
	private Integer type;
	// 评论星级（1-5分别对应1-5星）
    @ApiModelProperty(value = "评论星级（1-5分别对应1-5星）" ,name = "level")
	private Integer level;
	// 评论内容
    @ApiModelProperty(value = "评论内容" ,name = "content")
	private String content;
	// 用户id
    @ApiModelProperty(value = "用户id" ,name = "userId")
	private Integer userId;
	// 不赞同人数
    @ApiModelProperty(value = "不赞同人数" ,name = "disagreeNum")
	private Integer disagreeNum;
	// 赞同人数
    @ApiModelProperty(value = "赞同人数" ,name = "agreeNum")
	private Integer agreeNum;
	// 父评论id（对应主键id，-1表示没有父评论）
    @ApiModelProperty(value = "父评论id（对应主键id，-1表示没有父评论）" ,name = "parentCommentId")
	private Integer parentCommentId;
	// 评论时间
    @ApiModelProperty(value = "评论时间" ,name = "createDate")
	private Date createDate;
	// 评论用户账号
    @ApiModelProperty(value = "评论用户账号" ,name = "userName")
	private String userName;
	// 评论人昵称
    @ApiModelProperty(value = "评论人昵称" ,name = "userNickName")
	private String userNickName;
	// 评论人真实姓名
    @ApiModelProperty(value = "评论人真实姓名" ,name = "userRealName")
	private String userRealName;
	// 评论人头像
    @ApiModelProperty(value = "评论人头像" ,name = "userHeadPic")
	private String userHeadPic;
	// 相关课程id
    @ApiModelProperty(value = "相关课程id" ,name = "relativeCourseId")
	private Integer relativeCourseId;
	// 相关问题id
    @ApiModelProperty(value = "相关问题id" ,name = "relativeQuestionId")
	private Integer relativeQuestionId;
	// 相关试卷id
    @ApiModelProperty(value = "相关试卷id" ,name = "relativeExampaperId")
	private Integer relativeExampaperId;

}