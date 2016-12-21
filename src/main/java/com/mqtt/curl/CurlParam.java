package com.mqtt.curl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by yuandengfeng on 2016/11/11.
 */
public class CurlParam {


    public static void main(String[] args)
    {
      JSONObject param2=param2();
        System.out.println(param2);
    }


    //    1.1获取发票库存
//    String SID=0;
//    String SIDParam={"fpzl":0};

    public static JSONObject param1(){
        JSONObject root = new JSONObject();
        root.put("fpzl",2);

        return root;


    }


    ////    1.2发票开具
//     String SID=1;
//     String SIDParam={"customerName":"XXX分公司","customerTaxNr":"41021811172116X",
// "customerAddressTel":"XX河南分公司0171-23812056","customerBankAccountNr":"中行开封西城支行25017056514158",
// "memo":"","payee":"","checker":"","issuer":"演示用户",
// "sellerBankAccountNr":"中国银行655023109872655432","sellerAddressTel":"安徽合肥0551-654321",
// "documentNr":"","discountAmount":0,"discountVat":0,
// "listed":false,"invoiceType:"0",
// "invoiceItems":[{"productName":"视同销售","includeTax":"0","taxRate":0.13,"value":111,
// "price":"","productSpec":"","tax":14.43,"productNum":"","productUnit":"",
// "discountAmount":0,"discountVat":0,"goodsNoVer":"1.0","goodsTaxNo":"101010102",
// "taxPre":"0","taxPreCon":"","cropGoodsNo":"101010102","zeroTax":"","taxDeduction":""}]};

    public static JSONObject param2(){
        JSONObject root = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject invoiceItems = new JSONObject();
        root.put("customerName","XXX分公司");
        root.put("customerTaxNr","41021811172116X");
        root.put("customerAddressTel","XX河南分公司0171-23812056");
        root.put("customerBankAccountNr","中行开封西城支行25017056514158");
        root.put("memo","");
        root.put("payee","");
        root.put("checker","");
        root.put("issuer","演示用户");
        root.put("sellerBankAccountNr","中国银行655023109872655432");
        root.put("sellerAddressTel","安徽合肥0551-654321");
        root.put("documentNr","");
        root.put("discountAmount",0);
        root.put("discountVat",0);
        root.put("listed",false);
        root.put("invoiceType",0);
        invoiceItems.put("productName","视同销售");
        invoiceItems.put("includeTax","0");
        invoiceItems.put("taxRate",0.13);
        invoiceItems.put("value",111);
        invoiceItems.put("price","");
        invoiceItems.put("productSpec","");
        invoiceItems.put("tax",14.43);
        invoiceItems.put("productNum","");
        invoiceItems.put("productUnit","");
        invoiceItems.put("discountAmount",0);
        invoiceItems.put("discountVat",0);
        invoiceItems.put("goodsNoVer","1.0");
        invoiceItems.put("goodsTaxNo","101010102");
        invoiceItems.put("taxPre","0");
        invoiceItems.put("taxPreCon","");
        invoiceItems.put("cropGoodsNo","101010102");
        invoiceItems.put("zeroTax","");
        invoiceItems.put("taxDeduction","");
        array.add(0,invoiceItems);
        root.put("invoiceItems",array);
        return root;
    }

//    1.3发票打印
//    String SID=2;
//    String SIDParam={"fpzl":0,"fphm":"00066795","fpdm":"3400121170"};

    public static JSONObject param3(){
        JSONObject root = new JSONObject();
        root.put("fpzl",2);
        root.put("fphm","08916715");
        root.put("fpdm","4200154320");
        root.put("showPrintDlg",0);
//        root.put("invoicetype",0);    //发票类型(0 专票；2 普票)，querykind= 2 时不可为空
//        root.put("invtypecode","01067347");  //发票代码，querykind= 2 时不可为空
//        root.put("invnumber","4200161130");    //发票号码，querykind= 2 时不可为空
        return  root;

}

//    1.4发票清单打印
//   String SID=3;
//   String SIDParam={"fpzl":0,"fphm":"00066795","fpdm":"3400121170"};

    public static JSONObject param4() {
        JSONObject root = new JSONObject();
        root.put("fpzl",0);
        root.put("fphm","05414807");
        root.put("fpdm","4200154130");
        root.put("shouPrintDlg",0);
        return  root;

}
    //    1.5发票作废
//    String SID=4;
//    String SIDParam={"fpzl":0,"fphm":"00066795","fpdm":"3400121170"};

    public static JSONObject param5(){
        JSONObject root = new JSONObject();
        root.put("fpzl",0);
        root.put("fphm","05414807");
        root.put("fpdm","4200154130");
        return root;

    }

//    1.6启动开票服务
//    String SID=12
//    String SIDParam={'nsrsbh':'340101999999023','fjh':'0',’certPassword’:’8888888’}

    public static JSONObject param61(){
        JSONObject root = new JSONObject();
        root.put("nsrsbh","91420100587995077E");
        root.put("fjh","0");
        root.put("certPassword","12345678");
        return root;
    }

//    1.6服务状态查询接口
//    String SID=17
//    String SIDParam={}

    public static JSONObject param62(){
        JSONObject root = new JSONObject();
        return root;
    }

//    1.7发票查询
//    String SID=40
//    String querykind="1"
//    String billno="doc000Test"

    public static JSONObject param7(){
        JSONObject root = new JSONObject();
        root.put("querykind",2);      //查询类别(1:根据销售单据号查询；2：根据发票信息查询)
        root.put("billno","");  //销售单号，querykind= 1 时不可为空
        root.put("invoicetype",0);    //发票类型(0 专票；2 普票)，querykind= 2 时不可为空
        root.put("invtypecode","01067347");  //发票代码，querykind= 2 时不可为空
        root.put("invnumber","4200161130");    //发票号码，querykind= 2 时不可为空
        return root;
    }

//    1.8设置调用模式
//    String SID=41
//    String SIDParam={"synchro"=1}
    public static JSONObject param8(){
    JSONObject root = new JSONObject();
    root.put("synchro",1);
    return root;
    }


//    1.9卷式发票开具
//    String SID=44
//    String SIDParam={"payee":"付款人","autoCompleteFlbm":false,"customerTaxNr":"341256789012345",
// "invoiceItems":[{"goodsNoVer":"12.0","tax":0.,"taxPre":"0","taxRate":0.,"includeTax":1,
// "productName":"西瓜","cropGoodsNo":"","value":10.,"productNum":0.,"taxPreCon":"",
// "goodsTaxNo":"10101150120","zeroTax":"","price":0.}],"invoiceType":"41","memo":"",
// "customerName":"测试购方单位"}
    public static JSONObject param9(){
        JSONObject root = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject invoiceItems = new JSONObject();
        root.put("customerName","测试购方单位");
        root.put("customerTaxNr","341256789012345");
        root.put("memo","");
        root.put("payee","付款人");
        root.put("autoCompleteFlbm",false); //新增的字段
        root.put("invoiceType",41);
        invoiceItems.put("productName","西瓜");
        invoiceItems.put("taxRate",0.06);
        invoiceItems.put("value",10);
        invoiceItems.put("price",0);
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        invoiceItems.put("tax",0);
        invoiceItems.put("productNum","");
        invoiceItems.put("goodsNoVer","1.0");
        invoiceItems.put("goodsTaxNo","10101150120");
        invoiceItems.put("taxPre","0");
        invoiceItems.put("taxPreCon","");
        invoiceItems.put("cropGoodsNo","");
        invoiceItems.put("zeroTax","");
        invoiceItems.put("taxDeduction","");
        array.add(0,invoiceItems);
        root.put("invoiceItems",array);
        return root;
}



}
