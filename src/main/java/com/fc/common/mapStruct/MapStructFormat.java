package com.fc.common.mapStruct;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

/**
 * @author Florence
 * @since 2023/05/16
 */

@Deprecated
public class MapStructFormat {
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface DateToLong{}
    @DateToLong
    public static Long date2millis(Date date) {
        return date.getTime();
    }
}
