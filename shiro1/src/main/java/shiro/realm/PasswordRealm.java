package shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class PasswordRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return "PasswordRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //参数token表示 登录是包装的usernamepasswordtoken
        //通过用户名到数据库中查找到用户信息,封装成AuthenticationInfo对象并返回
        //获取token中的用户名
        String username = (String) token.getPrincipal();

        //通过用户名查询数据库 账号密码
        //假设数据库用户名zhangsan 密码123
        if (!"zhangsan".equals(username)){
            return null;
        }
        //模擬数据库加密之后的密文:密码+账号+散列次数3
        String password="dc5c3a487b9e7ec7e8680234af418631";
        //info表示realm登录比对信息: 参数1:用户信息(真实登录中是登录对象user对象) 参数2:密码 参数3:盐 参数4:当前realm名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes("zhangsan"),getName());
        return info;
    }
}
