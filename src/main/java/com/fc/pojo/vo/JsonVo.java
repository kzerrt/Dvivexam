package com.fc.pojo.vo;

import com.fc.pojo.result.CodeMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Florence
 * @since 2023/05/06
 */

@Data
@Api("返回结果")
public class JsonVo {
    private static final int CODE_OK = CodeMsg.OPERATE_OK.code();
    private static final int CODE_ERROR = CodeMsg.BAD_REQUEST.code();
    @ApiModelProperty("代码【0代表成功，其他代表失败】")
    private Integer code;

    @ApiModelProperty("消息描述")
    private String msg;
    public JsonVo() {
        this(CODE_OK, null);
    }
    public JsonVo(boolean ok, String msg) {
        this(ok ? CODE_OK : CODE_ERROR, msg);
    }
    public JsonVo(boolean ok) {
        this(ok, null);
    }
    public JsonVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public JsonVo(CodeMsg msg) {
        this(msg.code(), msg.msg());
    }
}
