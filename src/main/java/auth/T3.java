package auth;

import java.util.*;

import net.sf.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class T3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int m=0;m<=9;m++) {

			for (int i = 1; i <= 9; i++) {

					RestTemplate restTemplate = new RestTemplate();


					//添加路由mac
					List<String> macs = new ArrayList<String>();
//		macs.add("D4EE073BCB02");
//		macs.add("D4EE073BB73A");
//		macs.add("D4EE073BC522");
//		macs.add("D4EE073BCDF2");
//		macs.add("D4EE073BBA60");
//		macs.add("D4EE073BC7E4");
//		macs.add("D4EE073BBA4E");

					macs.add("D4EE07252702");

					//添加域名白名单
//		String param ="hi-wifi.cn,trk.apptracking.cn,setup.icloud.com,upp.itunes.apple.com,xp.apple.com,p47-buy.itunes.apple.com,sandbox.itunes.apple.com,captive.apple.com,guzzoni.apple.com,api.smoot.apple.com,itunes.apple.com,push.apple.com,open.adview.cn,pgdt.gtimg.cn,adfill.adview.cn,rtb.adview.cn,config.adview.cn,report.adview.cn,bid.adview.cn,mobads.baidu.com,m.baidu.com,baidu-img.cn,bdstatic.com,sp0.baidu.com,www.baidu.com,wn.pos.baidu.com,res.wx.qq.com,pay.weixin.qq.com,api.weixin.qq.com,open.weixin.qq.com,qlogo.cn,qpic.cn,mp.weixin.qq.com,weixin.qq.com,wappaygw.alipay.com,alipay.com,ok.yeepay.com,yeepay.com,alipayobjects.com,pinganfang.com,tca.sagetrc.com,www.google-analytics.com,stats.g.doubleclick.net,pin.qq.com,3g.qq.com,iadcn.com,hm.baidu.com,51hjb.com,2015hjb.com,f.sdo.com";
////				String param = "hi-wifi.cn,trk.apptracking.cn,setup.icloud.com,upp.itunes.apple.com,xp.apple.com";
//				List<String> domain = new ArrayList<String>();
//				String[] params = param.split(",");
////				System.out.println(params.length);
//				for (String temp : params) {
//					domain.add(temp);
//				}
					List<String> domain = new ArrayList<String>();
					for (int k = 10; k <= 49; k++) {
						domain.add("hi-wifi." + i + k + ".com");
					}
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("domain_white_list", domain);

					//添加访客白名单
//		String visitMacs ="00:00:00:00:00:00,00:00:00:00:00:01,00:00:00:00:00:02,00:00:00:00:00:03,00:00:00:00:00:04";
//		List<String> vistiss = new ArrayList<String>();
//		String  [] whiteMacs = visitMacs.split(",");
//		System.out.println(whiteMacs.length);
//		for(String temp : whiteMacs){
//			vistiss.add(temp);
//		}
					List<String> vistiss = new ArrayList<String>();
//		for(int i=0;i<=1;i++){

					for (int j = 10; j <= 49; j++) {
//				System.out.println("00:00:00:00:0"+i+":"+j);
						vistiss.add("00:00:00:00:0" + i + ":" + j);
					}
//		}

					data.put("mac_white_list", vistiss);

					Map<String, Object> valueMap = new HashMap<String, Object>();
					valueMap.put("macs", macs); //将mac以list的形式存入map中
					valueMap.put("data", data); //将domain,visitmac以map<String,list>形式存入map中

//		valueMap.put("domain_white_list", domain);

					String json = JsonUtils.Bean2Json(valueMap);
//				System.out.println(json);

//		JSONObject a = JSONObject.fromObject(json);
//		System.out.println(a.get("macs"));
//		System.out.println(a.get("domain_white_list"));
//		System.out.println("========================");
//		String tmp=a.get("domain_white_list").toString();


					HttpHeaders requestHeaders = new HttpHeaders();
					String request_id = "98121f17-3007-4a72-a14e-c7d8df76fb50";
					requestHeaders.set("Request-Id", request_id);
//		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//		requestHeaders.setContentType(type);
					HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(valueMap, requestHeaders);


//		String result = restTemplate.postForObject("http://open.kunteng.org/test/wifi/wifidog/white_list", request, String.class);
//				String result = restTemplate.postForObject("http://open.kunteng.org/test/wifi/wifidog/clear_white_list", request, String.class);
					String result = restTemplate.postForObject("http://open.kunteng.org/test/wifi/wifidog/add_white_list", request, String.class);
					System.out.println(result);
				if(m !=9 && i==9){

					try {

						Thread.sleep(1000*60*1);
						System.out.println("sleep 1 min");
						String clear = restTemplate.postForObject("http://open.kunteng.org/test/wifi/wifidog/clear_white_list", request, String.class);
						System.out.println(clear);
						Thread.sleep(1000*60*1);
						System.out.println("sleep 1 min  too");

					} catch (InterruptedException e) {
						e.printStackTrace();
					}


				}


			}

		}
	}

}
