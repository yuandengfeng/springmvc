package com.spring.beans3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/2/23.
 */
public class Main4 {

    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");
        TestObject to = (TestObject)ctx.getBean("testObject");
        System.out.println(to);

        UserController userController =(UserController)ctx.getBean("userController");
        System.out.println(userController);

        UserService userService = (UserService)ctx.getBean("userService");
        System.out.println(userService);
        userService.add();

        UserRepostory userRepostory = (UserRepostory)ctx.getBean("userRepostory");
        System.out.println(userRepostory);
    }

}
