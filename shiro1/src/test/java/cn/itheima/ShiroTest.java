package cn.itheima;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * shiro测试
 */
public class ShiroTest {
    @Test
    public void testLogin() throws Exception{
        //1:创建securitymanager工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2:通过工厂对象创建出securitymanager对象
        SecurityManager manager = factory.getInstance();
        //3:讲他绑定到当前运行环境中,让系统随时都可以访问这个对象
        SecurityUtils.setSecurityManager(manager);

        //4:创建当前登录的主体  当前主体没有经过验证
        Subject subject = SecurityUtils.getSubject();

        //5:绑定主体登录的身份凭证 账号密码
        UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123");
        //6:主体登录
        System.out.println(token.toString());
        subject.login(token);

        //7:判断登录是否成功
        System.out.println(subject.isAuthenticated());

        subject.logout();

        System.out.println(subject.isAuthenticated());



    }
    @Test
    public void testLoginByMyRealm() throws Exception{
        //1:创建securitymanager工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2:通过工厂对象创建出securitymanager对象
        SecurityManager manager = factory.getInstance();
        //3:讲他绑定到当前运行环境中,让系统随时都可以访问这个对象
        SecurityUtils.setSecurityManager(manager);

        //4:创建当前登录的主体  当前主体没有经过验证
        Subject subject = SecurityUtils.getSubject();

        //5:绑定主体登录的身份凭证 账号密码
        UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123");
        //6:主体登录
        System.out.println(token.toString());
        subject.login(token);

        //7:判断登录是否成功
        System.out.println(subject.isAuthenticated());

        subject.logout();

        System.out.println(subject.isAuthenticated());



    }
    @Test
    public void testLoginByPasswordMyRealm() throws Exception{
        //1:创建securitymanager工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
        //2:通过工厂对象创建出securitymanager对象
        SecurityManager manager = factory.getInstance();
        //3:将他绑定到当前运行环境中,让系统随时都可以访问这个对象
        SecurityUtils.setSecurityManager(manager);

        //4:创建当前登录的主体  当前主体没有经过验证
        Subject subject = SecurityUtils.getSubject();

        //5:绑定主体登录的身份凭证 账号密码
        UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123");
        //6:主体登录
        System.out.println(token.toString());
        subject.login(token);

        //7:判断登录是否成功
        System.out.println(subject.isAuthenticated());

        subject.logout();

        System.out.println(subject.isAuthenticated());



    }

    @Test
    public void testHasRole() throws Exception{
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123");
        subject.login(token);

        /*
        用户角色验证
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2")));
        System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2","role3")));
        System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1","role2"))));
        System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1","role2","role3"))));

        //当用户有此权限不做任何操作,  当没有此权限时,报异常,UnauthorizedException 无权限异常
        subject.checkRole("role3");
        subject.checkRoles("role1","role2");

*/
        System.out.println(subject.isPermitted("user:create"));

        //多个返回布尔值数组
        System.out.println(Arrays.toString(subject.isPermitted("user:create","user:list")));
        //true表示全部拥有  false表示不全部拥有
        System.out.println(subject.isPermittedAll( "user:create","user:update","user:delete"));

        //拥有权限不做操作,如果没有 报异常
        subject.checkPermission("user:create");



    }
    @Test
    public void testHasRoleByRealm() throws Exception{
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permissin-realm.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123");
        subject.login(token);



        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:delete"));




    }
}
