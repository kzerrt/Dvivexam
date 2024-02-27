package com.fc.common.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Florence
 * @since 2023/05/06
 */


public class Streams {
    public static <T, R> List<R> stream(Collection<T> list, Function<T, R> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }
}
