package com.fc.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Florence
 * @since 2023/04/27
 */
@ConfigurationProperties("jk")
@Component
public class JKProperty {

    private Cfg cfg;

    public Cfg getCfg() {
        return cfg;
    }

    public void setCfg(Cfg cfg) {
        this.cfg = cfg;
    }

    public static class Cfg {
        private String[] corsOrigins;

        public void setCorsOrigins(String[] corsOrigins) {
            this.corsOrigins = corsOrigins;
        }

        public String[] getCorsOrigins() {
            return corsOrigins;
        }
    }
}
