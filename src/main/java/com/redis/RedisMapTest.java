package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/28.
 */
public class RedisMapTest {


    public static void main(String[] args) throws Exception {
        Jedis redis = new Jedis("192.168.20.26", 6379, 400000);
        redis.select(8);   //创建数据库
        redis.flushDB();    //清空库


        Map<String,String> data = new HashMap<String,String>();

         //hmset
         long start = System.currentTimeMillis();
          //直接hmset
          for (int i=0;i<10000;i++) {
              data.clear();
              data.put("k_" + i, "v_" + i);
              redis.hmset("key_" + i, data);
          }
        long end = System.currentTimeMillis();
        System.out.println("dbsize:[" + redis.dbSize() + "] .. ");
        System.out.println("hmset without pipeline used [" + (end - start) / 1000 + "] seconds ..");
        redis.select(8);
        redis.flushDB();
        //使用pipeline hmset
        Pipeline p = redis.pipelined();
        start = System.currentTimeMillis();
        for (int i=0;i<10000;i++) {
            data.clear();
            data.put("k_" + i, "v_" + i);
            p.hmset("key_" + i, data);
        }
        p.sync();
        end = System.currentTimeMillis();
        System.out.println("dbsize:[" + redis.dbSize() + "] .. ");
        System.out.println("hmset with pipeline used [" + (end - start) / 1000 + "] seconds ..");
         //hmget
//         Set<String> keys = redis.keys("*");
         Set<String> keys = redis.keys("key_*");
           //直接使用Jedis hgetall
        start = System.currentTimeMillis();
        Map<String,Map<String,String>> result = new HashMap<String,Map<String,String>>();
        for(String key : keys) {
            result.put(key, redis.hgetAll(key));
        }
        end = System.currentTimeMillis();
        System.out.println("result size:[" + result.size() + "] ..");
        System.out.println("hgetAll without pipeline used [" + (end - start) / 1000 + "] seconds ..");
         //使用pipeline hgetall
         Map<String,Response<Map<String,String>>> responses = new HashMap<String,Response<Map<String,String>>>(keys.size());
        result.clear();
        start = System.currentTimeMillis();
        for(String key : keys) {
            responses.put(key, p.hgetAll(key));
        }
        p.sync();
        for(String k : responses.keySet()) {
            result.put(k, responses.get(k).get());
        }
        end = System.currentTimeMillis();
        System.out.println("result size:[" + result.size() + "] ..");
        System.out.println("hgetAll with pipeline used [" + (end - start) / 1000 + "] seconds ..");
        redis.disconnect();
      }



}
