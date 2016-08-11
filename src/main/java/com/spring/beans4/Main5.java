package com.spring.beans4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/2/24.
 */
public class Main5 {

    public static void main(String[] args)
    {

//        ArithmeticCalculator atithmeticCalculator=null;
        AtithmeticCalculator target =new AtithmeticCalculatorImpl();
        AtithmeticCalculator proxy =new AtithmeticCalculatorLoggingProxy(target).getLoggingProxy();
        int result =proxy.add(1,2);
        System.out.println(result);

//        int result =atithmeticCalculator.add(2,1);






    }
}
