package com.spring.beans;

/**
 * Created by Administrator on 2016/2/12.
 */
public class HelloWorld {

    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello()
    {
       System.out.println("hell"+name);
    }
    public HelloWorld()
    {
        System.out.println("helloworld control");
    }

}
