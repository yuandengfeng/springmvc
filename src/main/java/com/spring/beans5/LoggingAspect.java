package com.spring.beans5;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/2/24.
 */
//把这个类声明为一个切面：需要把该类放入到IOC容器中，再声明为一个切面

@Aspect
@Component
public class LoggingAspect {

    /*
    定义一个方法，用于声明切入点表达式，一般地，该方法中不需要添入其他代码。
     */
    @Pointcut("execution(public int com.spring.beans5.ArithmeticCalculator.*(..))")
    public void declareJointPointExpression(){}


    //声明该方法是一个前置通知：在目标方法开始前执行
    //在com.spring.beans4.ArithmeticCalculator 接口的每一个实现类的每一个方法开始之前执行一段代码
//    @Before("execution(public int com.spring.beans4.ArithmeticCalculator.*(..))")
    @Before("declareJointPointExpression()")
    public void beforeMethod(JoinPoint joinPoint){

        String methodName = joinPoint.getSignature().getName();
        Object []args =joinPoint.getArgs();
        System.out.println("Before The method begins");
    }

    //在方法执行之后执行的代码，无论该方法是否出现异常
//    @After("execution(public int com.spring.beans4.AtithmeticCalculator.*(..))")
        @After("declareJointPointExpression()")
    public void afterMethod(JoinPoint joinPoint){

        String methodName = joinPoint.getSignature().getName();
        System.out.println("After The method ends");
    }
     //在方法正常结束执行的代码
    //返回通知是可以访问到方法的返回值的
//    @AfterReturning(value = "execution(public int com.spring.beans4.ArithmeticCalculator.*(..))",returning = "result")
     @AfterReturning(value = "declareJointPointExpression()",returning = "result")
    public void afterReturning(JoinPoint joinPoint)
    {
        String methodName =joinPoint.getSignature().getName();
        System.out.println("AfterReturning The method ends");
    }
//在方法出现异常时会执行代码，可以访问到异常对象，且可以指定在
//出现特定异常时执行通知
//@AfterThrowing(value = "execution(public int com.spring.beans5.AtithmeticCalculator.*(..))",throwing = "ex")
@AfterThrowing(value = "declareJointPointExpression()",throwing = "ex")
public void afterThrowing(JoinPoint joinPoint,Exception ex)
{
    String methodName =joinPoint.getSignature().getName();
    System.out.println("The method occurs excetion");
}

    /*
    环绕通知需要携带ProceedingJoinPoint类型的参数。
    环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数
    可以决定是否执行目标方法
    且环绕通知必须有返回值，返回值即为目标方法的返回值
     */
    @Around("execution(public int com.spring.beans4.AtithmeticCalculator.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjd)
    {
        Object result=null;
                try{
                    System.out.println("The method begin");
                    result=pjd.proceed();

                }catch (Throwable e)
                {
                    e.printStackTrace();
                }
        return result;
    }






}
