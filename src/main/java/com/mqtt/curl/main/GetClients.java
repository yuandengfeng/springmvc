package com.mqtt.curl.main;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuandengfeng on 2017/1/6.
 */
public class GetClients {
    /**
     * 发送POST请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name),"UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name),"UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // 创建URL对象
            URL connURL = new URL(url);

            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            //设置登录验证=========================在需要登录验证的时候需要加上================================================
            String loginPassword = "admin"+ ":" +"public";
            String encoded = new sun.misc.BASE64Encoder().encode (loginPassword.getBytes());
            httpConn.setRequestProperty ("Authorization", "Basic " + encoded);
            //=========================================================================

            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args){
        Map<String, String> parameters = new HashMap<String, String>();
//        parameters.put("name", "sarin");
//        String result = sendGet("http://localhost:8080/servlets/HelloWorld", parameters);

        parameters.put("page_size","5799");
        parameters.put("curr_page","1");
        parameters.put("client_key","");
        String result = sendPost("http://192.168.20.128:18083/api/clients", parameters);
        JSONObject obj=JSONObject.fromObject(result);
        JSONArray array=JSONArray.fromObject(obj.getString("result"));
        File clientId = new File("G:\\坤腾\\超汇VIPLog接口\\cc\\clientId.csv");
        String str=null;
        List<String> li = new ArrayList<String>();
        for(int i=0;i<array.size();i++){
            JSONObject jn= array.getJSONObject(i);
            str=jn.get("clientId")+","+jn.getString("ipaddress");
            li.add(str);

        }
        try {
//            FileUtils.writeStringToFile(clientId,str,true);
            FileUtils.writeLines(clientId,li,true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
