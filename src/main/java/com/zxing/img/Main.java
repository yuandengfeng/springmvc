package com.zxing.img;

/**
 * Created by yuandengfeng on 2017/2/17.
 */
public class Main {
    public static void main(String[] args) {
//        String content="你好";
        String content="《姝•燕》 陈年累月犹瞬间，燕飞花开春归来。最喜人生好时刻，美满岁月汝相随。";
//        String content="http://entrance.kunteng.org/weixintest/mm.jpg";
        BarcodeFactory.
                encode(content,300, 300, "H:\\ll.jpg", "H:\\2013-01.jpg");
    }
}
