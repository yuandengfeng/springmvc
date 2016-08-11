package com.spring.beans;

import com.spring.beans.*;
import org.hibernate.connection.DatasourceConnectionProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.applet.AppletContext;
import java.util.Properties;

/**
 * Created by Administrator on 2016/2/12.
 */
public class Main {

    public static void main(String args[])
    {

        //创建spring的IOC容器对象
        //ApplicationContext代表IOC容器
        //ClassPathXmlApplicationContext,该实现加载所有定义的bean
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");

        //从IOC容器中获取bean实例
//        HelloWorld helloWorld = (HelloWorld)ctx.getBean("helloWorld");
//        利用类值返回IOC容器中的bean，但要求IOC容器必须只能有一个该类型bean
  /*
   HelloWorld helloWorld = (HelloWorld)ctx.getBean(HelloWorld.class);
        System.out.println(helloWorld);


        //调用helloworld方法
       Car car = (Car)ctx.getBean("car");
        System.out.println(car);
        Car car2 = (Car)ctx.getBean("car2");
        System.out.println(car2);
   */
/*
      Person p = (Person)ctx.getBean("person");
        System.out.println(p);
        Collection c = (Collection)ctx.getBean("newPerson");
        System.out.println(c.getMapcar().size());
 */
     /*
       DataSource dataSource =(DataSource)ctx.getBean("dataSource");
        System.out.println(dataSource.getPro().toString());
      */
        Collection pc = (Collection)ctx.getBean("person6");
        System.out.println(pc);

    }
}
