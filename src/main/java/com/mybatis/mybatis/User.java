package com.mybatis.mybatis;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/2/5.
 */
public class User {

    private int id;
    private String name;
    private int age;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String toString() {
//          byte[] b = name.getBytes();
         String str=null;
        try {
//            str = "User [id=" + id + ", name=" + new String(b,"utf-8") + ", age=" + age + "]";
            str = "User [id=" + id + ", name=" +name + ", age=" + age +",address="+address+ "]";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }



}
