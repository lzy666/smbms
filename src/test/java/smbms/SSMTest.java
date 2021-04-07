package smbms;

import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-02 16:11</p>
 **/
public class SSMTest {

    private ClassPathXmlApplicationContext context;

    @BeforeEach
    public void before(){
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test1(){
       /* UserService userService = context.getBean(UserService.class);
        System.out.println("userService = " + userService);
*/
        ProviderService providerService = context.getBean(ProviderService.class);
        System.out.println("providerService = " + providerService);
    }



}
