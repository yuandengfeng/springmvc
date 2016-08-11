package auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/4.
 */
public class Main {

    public static void main(String[] args)
    {
        RestTemplate restTemplate = new RestTemplate();


        //添加路由mac
        List<String> macs = new ArrayList<String>();
        macs.add("D4EE07252702");
        //		String param ="hi-wifi.cn,trk.apptracking.cn,setup.icloud.com,upp.itunes.apple.com,xp.apple.com,p47-buy.itunes.apple.com,sandbox.itunes.apple.com,captive.apple.com,guzzoni.apple.com,api.smoot.apple.com,itunes.apple.com,push.apple.com,open.adview.cn,pgdt.gtimg.cn,adfill.adview.cn,rtb.adview.cn,config.adview.cn,report.adview.cn,bid.adview.cn,mobads.baidu.com,m.baidu.com,baidu-img.cn,bdstatic.com,sp0.baidu.com,www.baidu.com,wn.pos.baidu.com,res.wx.qq.com,pay.weixin.qq.com,api.weixin.qq.com,open.weixin.qq.com,qlogo.cn,qpic.cn,mp.weixin.qq.com,weixin.qq.com,wappaygw.alipay.com,alipay.com,ok.yeepay.com,yeepay.com,alipayobjects.com,pinganfang.com,tca.sagetrc.com,www.google-analytics.com,stats.g.doubleclick.net,pin.qq.com,3g.qq.com,iadcn.com,hm.baidu.com,51hjb.com,2015hjb.com,f.sdo.com";
				String param = "hi-wifi.cn";
				List<String> domain = new ArrayList<String>();
				String[] params = param.split(",");
//				System.out.println(params.length);
				for (String temp : params) {
					domain.add(temp);
				}

        ////添加访客白名单
        String visitMacs ="00:00:00:00:00:00,00:00:00:00:00:01,00:00:00:00:00:02,00:00:00:00:00:03,00:00:00:00:00:04";
		List<String> vistiss = new ArrayList<String>();
		String  [] whiteMacs = visitMacs.split(",");
//		System.out.println(whiteMacs.length);
		for(String temp : whiteMacs){
			vistiss.add(temp);
		}



        Map<String, Object> data = new HashMap<String, Object>();
        data.put("domain_white_list", domain);
        data.put("mac_white_list", vistiss);
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("macs", macs); //将mac以list的形式存入map中
        valueMap.put("data", data); //将domain,visitmac以map<String,list>形式存入map中

        String json = JsonUtils.Bean2Json(valueMap);


        HttpHeaders requestHeaders = new HttpHeaders();
        String request_id = "98121f17-3007-4a72-a14e-c7d8df76fb50";
        requestHeaders.set("Request-Id", request_id);

        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(valueMap, requestHeaders);
        String result = restTemplate.postForObject("http://open.kunteng.org/test/wifi/wifidog/clear_white_list", request, String.class);
//        String result = restTemplate.postForObject("http://open.kunteng.org/test/wifi//wifidog/del_white_list",request, String.class);
//        String result = restTemplate.postForObject("http://open.kunteng.org/test/wifi/wifidog/add_white_list", request, String.class);
        System.out.println(result);

    }
}
