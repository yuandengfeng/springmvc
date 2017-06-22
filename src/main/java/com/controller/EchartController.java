package com.controller;

import com.es.Echarts;
import com.es.UtcTime;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by yuandengfeng on 2017/2/10.
 */
@Controller
@RequestMapping("/es")
public class EchartController {

//    http://localhost:8080/springmvc/es/ratelog?mac=D4EE0732F834&startTime=2017-02-09%2000:00:00&endTime=2017-02-09%2023:59:59
    @RequestMapping("/ratelog")
    @ResponseBody
    public JSONArray ratelog(@RequestParam String mac,@RequestParam String startTime,@RequestParam String endTime) {
//    public JSONArray ratelog() {
//        String startTime= UtcTime.getDateString("2017-02-09 00:00:00");
//        String endTime=UtcTime.getDateString("2017-02-09 23:59:59");
//        String mac="D4EE0732F834";
//        return Echarts.getRatelog(startTime,endTime,mac);

        return Echarts.getRatelog(UtcTime.getDateString(startTime), UtcTime.getDateString(endTime),mac);
    }

    @RequestMapping(value= "/getip")
    @ResponseBody
    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    @RequestMapping(value= "/test")
    @ResponseBody
    public String  test() throws UnsupportedEncodingException {
        System.out.println("测试例子");
        return "test";
    }


}
