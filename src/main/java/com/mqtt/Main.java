package com.mqtt;


import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/7/13.
 */
public class Main {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        Date times = calendar.getTime();
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Calendar.getInstance().getTime());
            }
        },times,5*1000);


        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                new MqttClient().client();

            }
        });
    }

}
