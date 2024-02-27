package com.fc.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Florence
 * @since 2023/05/06
 */

@EqualsAndHashCode(callSuper = true)
@Data

public class DataJsonVo<T> extends JsonVo{

    private T data;
    public DataJsonVo() {

    }
    public DataJsonVo(String msg, T data) {
        super(true, msg);
        this.data = data;
    }
    public DataJsonVo(T data) {
        this(null, data);
    }
}
