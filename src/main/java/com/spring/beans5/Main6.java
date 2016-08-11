package com.spring.beans5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/2/26.
 */
public class Main6 {

    public static void main(String[] args)
    {
                //1.创建srping的IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aspect.xml");

        //2.从IOC容器中获取bean的实例
//        ArithmeticCalculator arithmeticCalculator =ctx.getBean(ArithmeticCalculator.class);
        ArithmeticCalculator arithmeticCalculator =(ArithmeticCalculator)ctx.getBean("arithmeticCalculator");
        //3.使用bean
//        double result =arithmeticCalculator.div(6,2);
        int result =arithmeticCalculator.add(6,2);
        System.out.println(result);
    }

}
