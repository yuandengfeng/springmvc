package com.spring.beans2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/2/23.
 */
public class Main2 {
    public static void main(String args[])
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beansfactory.xml");
//        Car car1 = (Car)ctx.getBean("car1");
        Car car1 = (Car)ctx.getBean("car2");
        System.out.println(car1);
    }

}
