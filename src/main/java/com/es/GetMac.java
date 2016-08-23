package com.es;

import com.alibaba.fastjson.JSONObject;
import com.es.util.ESClient;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/12.
 */

//该类是用于查询wifiauth索引login表中的访客mac,除重
public class GetMac implements Runnable {

    private String index;   //索引

    private  String type;   //类型
    private  String filed;  //查询字段
    ReEsService es = new ReEsService();
    public GetMac(String index,String type,String filed )
    {
        this.index=index;
        this.type=type;
        this.filed=filed;

    }


    //显示查询结果,并封装成List<JSONObject>
    static Set<String> srcMac=null;
    public static Set<String> showResult(String index,String type,String filed,int size)
    {
        srcMac=new HashSet<String>();
        TransportClient client = ESClient.getTransportClient();
//      SearchRequestBuilder result1 = client.prepareSearch("wifiauth").setTypes(type).setQuery(QueryBuilders.queryStringQuery("*").defaultField(filed).defaultOperator(QueryStringQueryBuilder.Operator.AND)).setFrom(0).setSize(size);
        SearchRequestBuilder result1 = client.prepareSearch(index).setTypes(type).setQuery(QueryBuilders.queryStringQuery("*").defaultField(filed).defaultOperator(QueryStringQueryBuilder.Operator.AND)).setFrom(0).setSize(size);
        try {
            SearchResponse searchResponse = result1.execute().actionGet();
//          a = json1.getHits().getTotalHits();
            SearchHits hits = searchResponse.getHits();
//        System.out.println("查询到记录数=" + hits.totalHits());
            SearchHit[] searchHists = hits.getHits();
//        System.out.println(searchHists.length);
            for(SearchHit hit:searchHists){
//             String type =hit.getType();
//                String id = hit.getId();
                System.out.println(hit.getSource().toString());
                String mac =String.valueOf(hit.getSource().get("mac"));

//                String outgoing =String.valueOf(hit.getSource().get("outgoing"));
//                String timestamp =String.valueOf(hit.getSource().get("@timestamp"));
//                JSONObject js = new JSONObject();
//              js.put("type",type);
//                js.put("id",id);
//                js.put("incoming",incoming);
//                js.put("outgoing",outgoing);
//                js.put("@timestamp",timestamp);
                srcMac.add(mac);
//              System.out.println( id + "||" + incoming + "||" + outgoing+"||"+timestamp);
            }
        } catch (ElasticsearchException e) {
            LoggerFactory.getLogger(ReEsService.class).warn("{}", e);
        }
        return srcMac;

    }

    @Override
    public void run() {

        int totalNum= es.searchTotalNum(index,type,filed);
        Set<String> macs = GetMac.showResult(index,type,filed, totalNum);
        String macstr=null;
        System.out.println("macs======="+macs.size());
       for(String mac :macs)
       {

           System.out.println(mac);

       }

    }


     public static void main(String args[])
     {

//             GetMac srcMac = new GetMac ("wifiauth", "login", "@timestamp");
         GetMac srcMac = new GetMac ("wifiauth", "login", "mac");
             Thread t1 = new Thread(srcMac);
             t1.start();

     }
}
