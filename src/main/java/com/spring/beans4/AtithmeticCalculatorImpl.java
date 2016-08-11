package com.spring.beans4;

/**
 * Created by Administrator on 2016/2/24.
 */
public class AtithmeticCalculatorImpl implements AtithmeticCalculator{


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
}
