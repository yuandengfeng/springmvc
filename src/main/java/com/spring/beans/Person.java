package com.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/2/14.
 */
public class Person {
    private String name;
    private int age;
    private Car car;
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
       return "name="+this.name+"   age="+this.age+"  car.ref="+this.car.toString();
   }
// public Person(String name,int age,Car car)
// {
//     super();
//     this.name=name;
//     this.car=car;
//     this.age=age;
// }

    /**
     * Created by Administrator on 2016/2/14.
     */
    public static class Main1 {

        public static void main(String args[])
        {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scope.xml");
         /*
         Bus car = (Bus)ctx.getBean("Bus");
            Bus car2 = (Bus)ctx.getBean("Bus");
            System.out.println(car ==car2);
          */
            /*
            ComboPooledDataSource db = (ComboPooledDataSource)ctx.getBean("datasource");
            System.out.println(db.getDriverClass());
            */
            NewPerson db = (NewPerson)ctx.getBean("newPerson");
            System.out.println(db);

        }
    }
}
