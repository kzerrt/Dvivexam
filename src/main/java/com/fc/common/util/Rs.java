package com.fc.common.util;

/**
 * @author Florence
 * @since 2023/04/29
 */

@Deprecated
public class Rs  {
    /*public static R error(String msg) {
        return new R().setSuccess(false).setMsg(msg);
    }
    public static R error(int code, String msg) {
        return new R().setSuccess(false).setMsg(msg).setCode(code);
    }
    public static R ok(PageQuery query) {
        R r = new R();
        r.put("count", query.getCount());
        return r.setSuccess(true).setData(query.getData());
    }

    public static R ok(String msg) {
        R r = new R();
        return r.setSuccess(true).setMsg(msg);
    }
    public static R ok(Object data) {
        return new R().setSuccess(true).setData(data);
    }
    public static R error(Throwable t) {
        if(t instanceof CommonException) {
            CommonException e = (CommonException) t;
            R r = new R();
            r.setCode(e.getCode());
            r.setMsg(e.getMessage());
            return r;
        } else {
            error(t.getMessage());
        }
        return null;
    }

    public static R raise(String s) {
        R.raise(s);
        return null;
    }*/
}
