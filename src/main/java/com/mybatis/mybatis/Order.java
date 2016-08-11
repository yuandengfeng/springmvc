package com.mybatis.mybatis;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Order {

    private int order_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public float getOrder_price() {
        return order_price;
    }

    public void setOrder_price(float order_price) {
        this.order_price = order_price;
    }

    private String order_no;
    private float order_price;



    @Override
    public String toString() {
//          byte[] b = name.getBytes();
        String str=null;
        try {
//            str = "User [id=" + id + ", name=" + new String(b,"utf-8") + ", age=" + age + "]";
            str = "order  id=" + order_id + ", order_no=" +order_no + ", price=" + order_price;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}

