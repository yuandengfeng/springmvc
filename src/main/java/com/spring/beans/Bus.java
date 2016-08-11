package com.spring.beans;

/**
 * Created by Administrator on 2016/2/14.
 */
public class Bus {
    private String brand;
    private int price;
    private double tyrePerimeter;

    public double getTyrePerimeter() {
        return tyrePerimeter;
    }

    public void setTyrePerimeter(double tyrePerimeter) {
        this.tyrePerimeter = tyrePerimeter;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    @Override
    public String toString()
    {
        return "price="+this.price+"   brand="+this.brand +"  tyrePerimeter="+this.getTyrePerimeter();
    }



}
