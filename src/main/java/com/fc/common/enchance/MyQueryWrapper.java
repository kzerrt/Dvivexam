package com.fc.common.enchance;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * @author Florence
 * @since 2023/05/07
 */


public class MyQueryWrapper<T> extends QueryWrapper<T> {

    public final MyQueryWrapper<T> like(Object val, String... columns) {
        if (val == null) return this;
        String keyword = val.toString();
        if (keyword.length() == 0) return this;
        /**
         * nested可以将sql语句进行适当修改，如多or判断等
         * */
        return (MyQueryWrapper<T>) nested(w -> {
            for (String column : columns) {
                w.like(column, keyword).or();
            }
        });
    }
}
