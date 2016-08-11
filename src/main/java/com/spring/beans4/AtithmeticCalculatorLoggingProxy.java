package com.spring.beans4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/2/24.
 */
public class AtithmeticCalculatorLoggingProxy {

    private AtithmeticCalculator target;

    public AtithmeticCalculatorLoggingProxy(AtithmeticCalculator target)
    {
        this.target=target;
    }

    public AtithmeticCalculator getLoggingProxy()
    {
        AtithmeticCalculator proxy=null;
        ClassLoader loader =target.getClass().getClassLoader();
        Class [] interfaces =new Class[]{AtithmeticCalculator.class};
        InvocationHandler h=new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               String methodName =method.getName();
                System.out.println("invocate......");
                Object result =method.invoke(target,args);
                System.out.println("The method end");
                return result;
            }
        };
        proxy =(AtithmeticCalculator)Proxy.newProxyInstance(loader,interfaces,h);
        return proxy;
    }


}
