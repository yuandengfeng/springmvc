package com.log4j.test2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Administrator on 2016/3/29.
 */
public class Test2 {

    private static Log log = LogFactory.getLog(Test2.class);
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
//        log.debug("This is debug message.");
//        log.warn("This is warn message.");
//        log.info("This is info message.");
//        log.error("This is error message.");
        log.debug("Test2");
        log.warn("Test2");
        log.info("Test2");
        log.error("Test2");
    }


}
