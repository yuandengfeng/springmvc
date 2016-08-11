package com.redis;

import org.apache.commons.lang.math.RandomUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

/**
 * Created by Administrator on 2016/7/28.
 */
public class RedisEasyTest {

//    private static Jedis jedis = new Jedis("192.168.20.84",6379, 400000);

    private static String AUTH =null;

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
//          config.setMaxActive(MAX_ACTIVE);
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
//          config.setMaxWait(MAX_WAIT);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            System.out.println("====JedisPool====");
            jedisPool = new JedisPool(config, "192.168.20.26",6379, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    static Jedis jedis = jedisPool.getResource();


    private static Pipeline p = jedis.pipelined(); //进行批量操作

    private static int KEY_COUNT = 100;

    private static int FIELD_COUNT = 10;

    public void single() {
        jedis.select(8);
        for (int i = 0; i < KEY_COUNT; i++) {
            String key = RandomUtils.nextInt(5) + "";
//            System.out.println(key);
            for (int j = 0; j < FIELD_COUNT; j++) {

                jedis.hset(key, j + "", i + j + "");

                jedis.expire(key, 3600);  //表示过期失效


            }
        }

    }

    public void batch() {
        jedis.select(8);
        int index = 0;
        for (int i = 0; i < KEY_COUNT; i++) {
            String key = RandomUtils.nextInt(5) + "";
            for (int j = 0; j < FIELD_COUNT; j++) {
                p.hset(key, j + "", i + j + "");
                p.expire(key, 3600);
            }
            if (++index % 1000 == 0) {
                p.sync();
            }
        }
        p.sync();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        RedisEasyTest r = new RedisEasyTest();
        r.single();
        System.out.printf("single use %d sec \n", (System.currentTimeMillis() - start) / 1000);
        start = System.currentTimeMillis();
        r.batch();
        System.out.printf("batch use %d sec \n", (System.currentTimeMillis() - start) / 1000);

    }

}
