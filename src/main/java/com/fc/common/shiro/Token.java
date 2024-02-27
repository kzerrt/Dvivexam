package com.fc.common.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 将用户token进行shiro判断权限
 * @author Florence
 * @since 2023/06/12
 */


public class Token implements AuthenticationToken {
    private final String token;

    public Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
