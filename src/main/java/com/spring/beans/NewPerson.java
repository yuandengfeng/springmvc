package com.spring.beans;

/**
 * Created by Administrator on 2016/2/14.
 */
public class NewPerson {
    private String name;
    private int age;
    private Bus bus;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "name="+this.name+"   age="+this.age+"  car.ref="+this.bus.toString()+"  info="+this.info;
    }


}
