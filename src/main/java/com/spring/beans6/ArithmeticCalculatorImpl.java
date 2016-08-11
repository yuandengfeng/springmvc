package com.spring.beans6;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/2/24.
 */

public class ArithmeticCalculatorImpl implements ArithmeticCalculator {


    @Override
    public int add(int i, int j) {
        int result =i+j;
        return result;
    }

    @Override
    public int sub(int i, int j) {
        int result =i-j;
        return result;
    }

    @Override
    public int mul(int i, int j) {
        int result =i*j;
        return result;
    }

    @Override
    public double div(int i, int j) {
        int result =i/j;
        return result;
    }
}
