package com.fc.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Florence
 * @since 2023/05/08
 */

@Data
@ApiModel("考场列表")
public class ExamPlaceSimpleVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;
}
