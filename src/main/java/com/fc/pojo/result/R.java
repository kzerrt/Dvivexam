package com.fc.pojo.result;

import com.fc.common.enchance.Jsonable;
import com.fc.common.exception.CommonException;

import java.util.HashMap;

/**
 * @author Florence
 * @since 2023/04/29
 */

@Deprecated
public class R extends HashMap<String, Object> implements Jsonable {
    public R setSuccess(boolean flag) {
        return flag ? setCode(0) :setCode(400);
    }
    public R setCode(int code) {
        put("code", code);
        return this;
    }
    public R setMsg(String msg) {
        put("msg", msg);
        return this;
    }
    public R setData(Object data) {
        put("data", data);
        return this;
    }
    public static R error(String msg) {
        return new R().setSuccess(false).setMsg(msg);
    }
    public static R raise(String msg) {
        throw new CommonException(msg);
    }
}
