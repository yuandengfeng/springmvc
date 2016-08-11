package com.spring.beans2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/23.
 */
public class InstanceCarFactory {

    private Map<String,Car> cars=null;
    public InstanceCarFactory()
    {
        cars = new HashMap<String,Car>();
        cars.put("audi",new Car("audi",1000));
        cars.put("ford",new Car("ford",1200));
    }

    public Car getCar(String name)
    {
        return cars.get(name);
    }

}
