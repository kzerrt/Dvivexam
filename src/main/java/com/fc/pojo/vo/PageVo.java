package com.fc.pojo.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Florence
 * @since 2023/05/06
 */
@Data
@Api("分页查询结果")
public class PageVo<T> {
    @ApiModelProperty("总数")
    private long count;
    @ApiModelProperty("总页数")
    private long pages;
    @ApiModelProperty("数据")
    private List<T> data;
}
