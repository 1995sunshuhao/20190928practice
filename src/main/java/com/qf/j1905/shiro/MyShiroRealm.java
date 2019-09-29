package com.qf.j1905.shiro;

import com.qf.j1905.dao.PermissionMapper;
import com.qf.j1905.dao.UserMapper;
import com.qf.j1905.domain.Permission;
import com.qf.j1905.domain.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 54110 on 2019-09-19.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer userId = (Integer) principalCollection.getPrimaryPrincipal();
        List<Permission> permissionsByUserId = permissionMapper.findPermissionsByUserId(userId);

        Collection<String > perms = new HashSet<>();
        for (Permission perm: permissionsByUserId ) {
            perms.add(perm.getPermissionName());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        Integer userId = (Integer) authenticationToken.getPrincipal();
        User byUserId =  userMapper.findByUserId(userId);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(byUserId.getUserId(), byUserId.getUserPwd(), getName());
        return simpleAuthenticationInfo;
    }
}
