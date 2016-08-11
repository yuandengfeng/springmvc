package com.spring.beans;

/**
 * Created by Administrator on 2016/2/12.
 */
public class Car {

    private String brand;
    private String corp;
    private double price;
    private int maxSpeed;

    public Car(String brand,String corp,double price)
    {
        super();
        this.brand=brand;
        this.corp=corp;
        this.price=price;
    }
    public Car(String brand,String corp,int maxSpeed)
    {
        super();
        this.brand=brand;
        this.corp=corp;
        this.maxSpeed=maxSpeed;
    }

     @Override
    public String toString()
     {
         return "brand="+this.brand+"   corp="+this.corp+"   price="+this.price+"   maxSpeed="+this.maxSpeed;
     }


}
