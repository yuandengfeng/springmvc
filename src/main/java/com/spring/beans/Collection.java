package com.spring.beans;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/14.
 */
public class Collection {

    private String name;
    private int age;
    private List<Car> cars;
    private Map<String,Car> mapcar;
    public Map<String, Car> getMapcar() {
        return mapcar;
    }

    public void setMapcar(Map<String, Car> mapcar) {
        this.mapcar = mapcar;
    }


    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
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
        return "name="+this.name+"   age="+this.age+"  car.ref="+this.cars.toString();
    }
//    public Collection(String name,int age,List<Car> cars)
//    {
//        super();
//        this.name=name;
//        this.cars=cars;
//        this.age=age;
//    }


}
