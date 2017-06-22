package com.controller;

import com.rsa.RsaUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/30 0030.
 */
@Controller
@RequestMapping("/posinfo")
public class YunPosController {

    @RequestMapping(value= "/pos", method= RequestMethod.POST)
    @ResponseBody
//    public String  posinfo(@RequestParam String content) throws UnsupportedEncodingException {
      public String  posinfo(HttpServletRequest httpRequest) throws UnsupportedEncodingException {
        String base64_privatekey ="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJdgDFa3mHoAANQP3zoMIYAvH6uhmhb/CAnXvqSv7osH7HvYdSj0clJu2aEB7ZzR1d7EKaL9l7wCNilqX8okWtb3DQ+ouleM+BDz+nfO0Y62rSt9S+uNaKDZ/4ZAg4Wv/iE9UcqgtaOX3TffE2i7VEDBGpogdfncXRDczFwhwSr9AgMBAAECgYBDCCBOojxeAfRb869ZCBgpqDRxDz2p40NPAH+Gc/XBEUAyU1cGTMD3KekrOCxdXjpDzD/X0k5Hzf7n9hf27oRerD7yWfEa2fZeGFqhCkB8HSOMXO1Q1eU43dQdG0LmGY0e9Ger9dn05a0x+BuXjvmCmisQcdDri8ZsU8MB7j0IoQJBAPTze8xIvKaZ+U2P0Nhf1V3dPrxXQIvFTpqNlsEVau5DRV+0X/7h9in3Y16LyJthJRCTSlCqWXSzmZ84RW5P9fUCQQCeNAStKHqqp6khuMTt6NVbiDjAw7ZiRhDPzdygPHAf1R+491Pptie2RB067PLfGHg8T+1HrBrxYPjm1jBKALPpAkBdh5r9pchi1OgcdY2JEQ6niJMr1yUOOYiJ+wZVM5hg4c7bfEQrTpxv6cZHk5hFvBIrAhKeOm1t4iYGZbYdKOsBAkBA6x2kJME5eDVqNhTxf6LVva90Qpt79j59138abdKg7WboEu3nMK1ZGNhZztPXAoaMUUwQJiqkNGPO7cPBCkrJAkB8SyXxGruMg7F5b0A6pkwN3/v8W1VIumQ7RkId2EiIsxz7aFeCr7njhZc7VFrGXzr3CqcUwagmOxUDF3kyDgYZ";// getPrivateKey(pairkeys);
//        byte[] decode = RsaUtils.decryptByRSA(Base64.decodeBase64(base64_privatekey), RsaUtils.decodeBase64(content.getBytes()));
        byte[] decode = RsaUtils.decryptByRSA(Base64.decodeBase64(base64_privatekey), RsaUtils.decodeBase64(httpRequest.getParameter("content").getBytes()));
        String str =new String(decode, "utf-8");
        System.out.println(new Date()+"解密后的数据显示：" + str);
        return str;
    }

}
