package com.rsa;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Administrator on 2017-03-22.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        String base64_publickey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXYAxWt5h6AADUD986DCGALx+roZoW/wgJ176kr+6LB+x72HUo9HJSbtmhAe2c0dXexCmi/Ze8AjYpal/KJFrW9w0PqLpXjPgQ8/p3ztGOtq0rfUvrjWig2f+GQIOFr/4hPVHKoLWjl9033xNou1RAwRqaIHX53F0Q3MxcIcEq/QIDAQAB";// getPublicKey(pairkeys);
        //System.out.println("public key:");
        //System.out.println(base64_publickey);


       // System.out.println("private key:");
        String base64_privatekey ="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJdgDFa3mHoAANQP3zoMIYAvH6uhmhb/CAnXvqSv7osH7HvYdSj0clJu2aEB7ZzR1d7EKaL9l7wCNilqX8okWtb3DQ+ouleM+BDz+nfO0Y62rSt9S+uNaKDZ/4ZAg4Wv/iE9UcqgtaOX3TffE2i7VEDBGpogdfncXRDczFwhwSr9AgMBAAECgYBDCCBOojxeAfRb869ZCBgpqDRxDz2p40NPAH+Gc/XBEUAyU1cGTMD3KekrOCxdXjpDzD/X0k5Hzf7n9hf27oRerD7yWfEa2fZeGFqhCkB8HSOMXO1Q1eU43dQdG0LmGY0e9Ger9dn05a0x+BuXjvmCmisQcdDri8ZsU8MB7j0IoQJBAPTze8xIvKaZ+U2P0Nhf1V3dPrxXQIvFTpqNlsEVau5DRV+0X/7h9in3Y16LyJthJRCTSlCqWXSzmZ84RW5P9fUCQQCeNAStKHqqp6khuMTt6NVbiDjAw7ZiRhDPzdygPHAf1R+491Pptie2RB067PLfGHg8T+1HrBrxYPjm1jBKALPpAkBdh5r9pchi1OgcdY2JEQ6niJMr1yUOOYiJ+wZVM5hg4c7bfEQrTpxv6cZHk5hFvBIrAhKeOm1t4iYGZbYdKOsBAkBA6x2kJME5eDVqNhTxf6LVva90Qpt79j59138abdKg7WboEu3nMK1ZGNhZztPXAoaMUUwQJiqkNGPO7cPBCkrJAkB8SyXxGruMg7F5b0A6pkwN3/v8W1VIumQ7RkId2EiIsxz7aFeCr7njhZc7VFrGXzr3CqcUwagmOxUDF3kyDgYZ";// getPrivateKey(pairkeys);
        //System.out.println(base64_privatekey);

        String willsigndata = "二级索引存储指定字段的索引，实际的指向位置是主键索引。当我们通过二级索引统计数据的时候，无需扫描数据文件；而通过主键索引统计数据时，由于主键索引与数据文件存放在一起，所以每次都会扫描数据文件，所以主键索引统计没有二级索引效率高";

        System.out.println("原始数据："+willsigndata);
        System.out.println("");
        System.out.println("");
        System.out.println("");

        byte[] encrypt = RsaUtils.encryptByRSA(Base64.decodeBase64(base64_publickey), willsigndata.getBytes("utf-8"));
        System.out.println("len: "+encrypt.length);
        String xx = RsaUtils.encodeBase64(encrypt);
        System.out.println("加密后的数据:" + xx);//加密后的base64字符串
        System.out.println("");
        System.out.println("");
        System.out.println("");
        byte[] decode = RsaUtils.decryptByRSA(Base64.decodeBase64(base64_privatekey), RsaUtils.decodeBase64(xx.getBytes()));
        System.out.println("解密后的数据：" + new String(decode, "utf-8"));

    }

}
