package com.spring.beans2;

/**
 * Created by Administrator on 2016/2/23.
 */
public class Car {
    private String brand;
//    private String corp;
    private double price;
//    private int maxSpeed;
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

//    public String getCorp() {
//        return corp;
//    }
//
//    public void setCorp(String corp) {
//        this.corp = corp;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public int getMaxSpeed() {
//        return maxSpeed;
//    }
//
//    public void setMaxSpeed(int maxSpeed) {
//        this.maxSpeed = maxSpeed;
//    }

  public Car(String brand,int price)
  {
      this.brand =brand;
      this.price=price;
  }

    public Car()
    {
        System.out.println("car constroct!");
    }


    @Override
    public String toString()
    {
//        return "brand="+this.brand+"   corp="+this.corp+"   price="+this.price+"   maxSpeed="+this.maxSpeed;
        return "brand="+this.brand+"   price="+this.price;
    }
}
