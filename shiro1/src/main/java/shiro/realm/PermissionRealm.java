package shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;

public class PermissionRealm extends AuthorizingRealm {
    @Override
    //授权操作
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        //得到认证过后的用户名
        String username = (String) principalCollection.getPrimaryPrincipal();

        //模拟数据库通过用户名查询出角色和权限 放入AuthorizationInfo中
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<String> permissions = new ArrayList<>();

        roles.add("role1");
        permissions.add("user:delete");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);



        return info;
    }













    @Override
    //认证操作
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
        String password="123";
        //info表示realm登录比对信息: 参数1:用户信息(真实登录中是登录对象user对象) 参数2:密码 参数3:当前realm名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        return info;
    }

    @Override
    public String getName() {
        return "PermissionRealm";
    }
}
