package com.spring.beans6;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/2/27.
 */

@Aspect
@Component
public class VlidationAspect {

    @Before("LoggingAspect.declareJointPointExpression()")
    public void validateArgs(JoinPoint joinPoint)
    {
        System.out.println("-->validate:"+ Arrays.asList(joinPoint.getArgs()));
    }
}
