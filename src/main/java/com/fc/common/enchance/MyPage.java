package com.fc.common.enchance;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.common.util.Streams;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.req.page.PageReqVo;

import java.util.List;
import java.util.function.Function;

/**
 * @author Florence
 * @since 2023/04/29
 */


public class MyPage<T> extends Page<T> {
    private final PageReqVo reqVo;
    public MyPage(PageReqVo reqVo) {
        super(reqVo.getPage(), reqVo.getSize());
        this.reqVo = reqVo;
    }

    private <N> PageVo<N> commonBuildVo(List<N> data) {
        reqVo.setPage(getCurrent());
        reqVo.setSize(getSize());

        PageVo<N> pageVo = new PageVo<>();
        pageVo.setCount(getTotal());
        pageVo.setPages(getPages());
        pageVo.setData(data);
        return pageVo;
    }
    public PageVo<T> buildVo() {
        return commonBuildVo(getRecords());
    }
    public <R> PageVo<R> buildVo(Function<T, R> function) {
        return commonBuildVo(Streams.stream(getRecords(), function));
    }
}
