package com.mqtt.curl;

import curl.mqtt.api.MQTTCli;
import curl.mqtt.exe.CMD_EXE_script;
import curl.mqtt.util.Base64Util;
import curl.mqtt.util.CMD;
import curl.mqtt.util.ICMDCallback;
import net.sf.json.JSONObject;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yuandengfeng on 2016/11/15.
 */

public class MqttService {


//    String url="http://192.168.0.113:9999/InvSvr";
      String url="http://10.1.1.27:9999/InvSvr";
//    String mac="D4EE07251D06";
    String mac="D4EE07251D06";
    //初始化MQTTListener
    void init(){

        MQTTCli.getInstance();
        System.out.println("MQTTListener is getInstance");

      }

    public JSONObject curlExe(String SID,JSONObject param)
    {

//        System.out.println(url);
//        System.out.println(mac);

        init();  //初始化mqtt

//        String SID="0";      //服务号 0获取发票库存 1 发票开具 2 发票打印  3 发票清单打印 4 发票作废 12 启动开票服务
        String SIDParam= null;
        try {
            SIDParam= URLEncoder.encode(param.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("curl -s -d \"SID="+SID+"&SIDParam="+SIDParam+"\" "+url);

        CMD cmd24 = new CMD_EXE_script(Base64Util.encode(sb.toString().getBytes()), "1");
        cmd24.setSID("chaohui");
        cmd24.setDID(mac);  //指定接收命令的路由mac
//        System.out.println(cmd24.toString());
//        System.out.println("sctipt:" + CMD.toPString(cmd24));
        JSONObject result=synMethod(cmd24);
        return result;
    }



    public static JSONObject synMethod(CMD cmd24){

        final SynWait synWait = new SynWait();

//        System.out.println("--aaaa");
        try {
            MQTTCli.getInstance().send(cmd24, new ICMDCallback() {
                @Override
                public void process(CMD cmd, JSONObject response) throws Throwable {
//                    System.out.println("返回结果！");
//                    System.out.println(response);
//                    System.out.println(response.containsKey("log_stdout"));
                    String src= (String) response.get("log_stdout");

                    String str = URLDecoder.decode(src,"UTF-8");
                    System.out.println("str==="+str);
                    JSONObject log_stdout=JSONObject.fromObject(str);
                    log_stdout.put("_SRC_",src);
//                    JSONObject js = new JSONObject();
//                    js.put("content",src);
//                    synWait.setContent(js);
                    synWait.setContent(log_stdout);



                }
            });
        } catch (MqttException ex) {
            Logger.getLogger(MqttService.class.getName()).log(Level.SEVERE, null, ex);
        }

//        JSONObject result = new JSONObject();
        JSONObject obj = synWait.startAndWait();

          return obj;
    }

    public static class SynWait {
        JSONObject content = null;

        public static long TIMEOUT = 30000;

        public static long STEP = 1000;

        long timeout = 0;

        public JSONObject getContent() {
            return content;
        }

        public void setContent(JSONObject content) {
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
            } catch (Throwable t){
                t.printStackTrace();
            }
        }

        public JSONObject  startAndWait(){
            while((!hasValue())&&(!isTimeOut())){
//                System.out.println("ssss+:"+isTimeOut()+"\t"+hasValue()+"\t"+getContent());
                step();
//                if(isTimeOut())
//                {
////                   JSONObject timeout=new JSONObject();
////                    timeout.put("timeout","30000");
////                    this.setContent(timeout);
//                    break;
//                }
//                System.out.println("timeout"+timeout);
            }
            return getContent();
        }
    }




}
