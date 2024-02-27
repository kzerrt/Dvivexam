package com.fc.common.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

/**
 * @author Florence
 * @since 2023/05/23
 */


public class Caches {
    private static final CacheManager MAG;
    private static final Cache<Object, Object> TOKEN_CACHE;
    static {
        URL url = Caches.class.getClassLoader().getResource("ehcache.xml");
        assert url != null;
        Configuration cfg = new XmlConfiguration(url);
        MAG = CacheManagerBuilder.newCacheManager(cfg);
        MAG.init();
        // 缓存对象
        TOKEN_CACHE = MAG.getCache("token", Object.class, Object.class);
    }
    public static void putToken(Object key, Object value) {
        if (key == null || value == null) return;
        TOKEN_CACHE.put(key, value);
    }

    public static void removeToken(Object key) {
        if (key == null) return;
        TOKEN_CACHE.remove(key);
    }

    public static <T> T getToken(Object key) {
        if (key == null) return null;
        return (T) TOKEN_CACHE.get(key);
    }

    public static void clearToken() {
        TOKEN_CACHE.clear();
    }

}
