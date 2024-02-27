package com.fc.common.enchance;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;


/**
 * @author Florence
 * @since 2023/04/29
 */


public class MyLambdaQueryWrapper<T> extends LambdaQueryWrapper<T> {

    @SafeVarargs
    public final MyLambdaQueryWrapper<T> like(Object val, SFunction<T, ?>... functions) {
        if (val == null) return this;
        String keyword = val.toString();
        if (keyword.length() == 0) return this;
        /**
         * nested可以将sql语句进行适当修改，如多or判断等
         * */
        return (MyLambdaQueryWrapper<T>) nested(w -> {
            for (SFunction<T, ?> func : functions) {
                w.like(func, keyword).or();
            }
        });
    }
}
