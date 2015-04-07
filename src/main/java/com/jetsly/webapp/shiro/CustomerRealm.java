package main.java.com.jetsly.webapp.shiro;

import main.java.com.jetsly.webapp.domain.User;
import main.java.com.jetsly.webapp.persistence.UserMapper;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.shiro.realm.AuthorizingRealm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

//自定义的指定Shiro验证用户登录的类
@Component
public class CustomerRealm extends AuthorizingRealm {

  @Autowired
  private  UserMapper userMapper;

  //验证当前登录的Subject
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token)
      throws AuthenticationException {
    final UsernamePasswordToken credentials = (UsernamePasswordToken) token;
    final User user = userMapper.findByUserName(credentials.getUsername());
    if (user == null) {
        throw new UnknownAccountException("Account does not exist");
    }
    final String passWord=new String(credentials.getPassword());
    if (!user.getUserName().equals(passWord)){
        throw new IncorrectCredentialsException("PassWord didn't match");
    }
    //SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName)
    return new SimpleAuthenticationInfo(credentials.getPrincipal(), credentials.getCredentials(),
              ByteSource.Util.bytes(credentials.getCredentials()), credentials.getUsername());
  }

  //为当前登录的Subject授予角色和权限
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
    final int totalRoles = 1;
    final Set<String> roleNames = new LinkedHashSet<>(totalRoles);
    roleNames.add("admin");
    final Set<String> permissionNames = new LinkedHashSet<>();
    final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
    info.setStringPermissions(permissionNames);
    return info;
  }

}
