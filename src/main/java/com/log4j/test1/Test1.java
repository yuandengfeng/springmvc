package com.log4j.test1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Administrator on 2016/3/29.
 */
public class Test1 {

//    private static Logger logger = Logger.getLogger(Test1.class);
//
//    public static void main(String[] args) {
////     System.out.println("This is println message.");
//    // 记录debug级别的信息
//    logger.debug("This is debug message.");
//
//    // 记录info级别的信息
//    logger.info("This is info message.");
//     // 记录error级别的信息
//        try{
//           int a =1%0;
//        }catch (Exception e)
//        {
////            logger.error("数据除0了");
//            logger.error(e,e.fillInStackTrace());
//        }
//
//    }

    private static Log log = LogFactory.getLog(Test1.class);
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        log.debug("Test1");
        log.warn("Test1");
        log.info("Test1");
        log.error("Test1");
    }



}
