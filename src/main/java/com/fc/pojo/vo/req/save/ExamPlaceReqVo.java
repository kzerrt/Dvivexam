package com.fc.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ExamPlaceReqVo {
    @ApiModelProperty("id【大于0代表更新，否则代表添加】")
    private Integer id;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称【不能为空】", required = true)
    private String name;

    @NotNull
    @ApiModelProperty(value = "省份id", required = true)
    private Integer provinceId;

    @NotNull
    @ApiModelProperty(value = "城市id", required = true)
    private Integer cityId;

    @ApiModelProperty("是否禁用【0代表不禁用（启用），1代表禁用;默认为0】")
    private Byte disable;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("经度")
    private BigDecimal longitude;
}