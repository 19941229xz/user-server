package com.example.userserver.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "PageParam", description = "分页条件参数")
@Data
public class PageParam<Model> {

    @ApiModelProperty(value = "条件参数",name = "model")
    private Model model;

    @ApiModelProperty(value = "排序参数",name = "orderParams")
    private String[] orderParams;

    @ApiModelProperty(value = "页码",name = "pageNum")
    private int pageNum;

    @ApiModelProperty(value = "每页记录条数",name = "pageSize")
    private int pageSize;

    @ApiModelProperty(value = "高级检索关键词（自动模糊匹配所有字符串类型字段）",name = "superSearchKeyWord")
    private String superSearchKeyWord;
}
