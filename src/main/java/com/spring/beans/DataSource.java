package com.spring.beans;

import java.util.Properties;

/**
 * Created by Administrator on 2016/2/14.
 */
public class DataSource {
    private Properties pro;
    public Properties getPro() {
        return pro;
    }

    public void setPro(Properties pro) {
        this.pro = pro;
    }
    @Override
    public String toString()
    {
        return "pro="+this.pro;
    }

}
