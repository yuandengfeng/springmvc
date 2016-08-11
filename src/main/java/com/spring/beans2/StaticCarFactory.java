package com.spring.beans2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/23.
 */
public class StaticCarFactory {
    private static Map<String,Car> cars = new HashMap<String,Car>();
    static{
        cars.put("audi",new Car("audi",60000));
        cars.put("ford",new Car("ford",30000));
    }

    public static Car getCar(String name)
    {
        return cars.get(name);
    }

}
