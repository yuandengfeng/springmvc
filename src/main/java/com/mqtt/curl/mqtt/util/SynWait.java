package com.mqtt.curl.mqtt.util;

/**
 * Created by yuandengfeng on 2016/11/17.
 */
public  class SynWait<T> {
    T content = null;

    public static long TIMEOUT = 30000;

    public static long STEP = 1000;

    long timeout = 0;



    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {  //设置超时时间
        this.timeout = timeout;
    }

    public boolean hasValue(){
        return content!=null;
    }

    public boolean isTimeOut(){
        return timeout>=TIMEOUT;
    }

    public void step(){
        timeout+=STEP;   //设置步长
        try {
            Thread.sleep(STEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public T  startAndWait(){
        while((!hasValue())&&(!isTimeOut())){

            step();

        }
        return getContent();
    }
}
