package com.fc.pojo.vo.req.page;

import java.util.List;

/**
 * todo: 在请求后返回数据，需要将这些基本数据进行返回，否则页面中的某些数据无法显示
 * @author Florence
 * @since 2023/04/28
 */


public abstract class PageReqVo {
    private static final int DEFAULT_SIZE = 10;
    private long page;
    private long size;


    public long getPage() {
        return Math.max(page, 1);
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getSize() {
        return (size < 1) ? DEFAULT_SIZE : size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
