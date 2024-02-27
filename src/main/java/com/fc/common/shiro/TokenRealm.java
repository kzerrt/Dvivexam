package com.fc.common.shiro;

import com.fc.common.cache.Caches;
import com.fc.pojo.dto.SysUserDto;
import com.fc.pojo.po.SysResource;
import com.fc.pojo.po.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author Florence
 * @since 2023/06/12
 */

@Slf4j
public class TokenRealm extends AuthorizingRealm {

    public TokenRealm(TokenMatcher tokenMatcher) {
        super(tokenMatcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        log.debug("TokenRealm - supports - {}", token);
        return token instanceof Token;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("doGetAuthorizationInfo - {}", principals);
        // 拿到token
        String token = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        SysUserDto user = Caches.getToken(token);
        if (user.getRoles() != null) {
            for (SysRole role : user.getRoles()) {
                info.addRole(role.getName());
            }
        }
        if (user.getResources() != null) {
            for (SysResource resource : user.getResources()) {
                info.addStringPermission(resource.getPermission());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String tk = (String) authenticationToken.getPrincipal();
        return new SimpleAuthenticationInfo(tk,tk,getName());
    }
}
