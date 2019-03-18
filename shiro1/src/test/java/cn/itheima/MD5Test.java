package cn.itheima;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import sun.security.provider.MD5;

public class MD5Test {
    @Test
    public void MD5Test(){
        String password="123";//密码明文
        //MD5加密
        Md5Hash md5Hash=new Md5Hash(password);
        //202cb962ac59075b964b07152d234b70

        //MD5加密+盐
        md5Hash=new Md5Hash(password,"zhangsan");
        //4e7bdb88640b376ac6646b8f1ecfb558

        //MD5加密+盐+散列次数(迭代)
        md5Hash=new Md5Hash(password,"zhangsan",3);
        //dc5c3a487b9e7ec7e8680234af418631
        System.out.println(md5Hash);
    }
}
