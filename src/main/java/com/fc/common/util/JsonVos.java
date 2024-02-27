package com.fc.common.util;

import com.fc.common.exception.CommonException;
import com.fc.pojo.result.CodeMsg;
import com.fc.pojo.vo.DataJsonVo;
import com.fc.pojo.vo.JsonVo;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.PageVo;

/**
 * @author Florence
 * @since 2023/05/06
 */


public class JsonVos {
    public static JsonVo error(String msg) {
        return new JsonVo(false, msg);
    }

    public static JsonVo error(CodeMsg msg) {
        return new JsonVo(msg);
    }

    public static JsonVo error(int code, String msg) {
        return new JsonVo(code, msg);
    }

    public static JsonVo error() {
        return new JsonVo(false);
    }
    public static JsonVo ok(CodeMsg msg) {
        return new JsonVo(msg);
    }

    public static JsonVo ok(String msg) {
        return new JsonVo(true, msg);
    }
    public static JsonVo ok() {
        return new JsonVo();
    }
    public static <T> DataJsonVo<T> ok(T data) {
        return new DataJsonVo<>(data);
    }
    public static <T> PageJsonVo<T> ok(PageVo<T> pageVo) {
        PageJsonVo<T> vo = new PageJsonVo<>();
        vo.setCount(pageVo.getCount());
        vo.setData(pageVo.getData());
        return vo;
    }
    public static <T> T raise(String msg) throws CommonException {
        throw new CommonException(msg);
    }

    public static <T> T raise(CodeMsg codeMsg) throws CommonException {
        throw new CommonException(codeMsg);
    }
}
