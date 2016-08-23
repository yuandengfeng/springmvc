package com.linux;

public class JshMain {

    public static void main(String[] args) {

        byte[] b = new byte[1024];

        //有效数据个数

        int n = 0;

        try{

            while(true){

                //提示信息

                System.out.println("请输入：");

                //读取数据

                n = System.in.read(b);

                //转换为字符串

                String s = new String(b,0,n - 2);

                //判断是否是quit

                if(s.equalsIgnoreCase("quit")){

                    break; //结束循环

                }

                //回显内容

                System.out.println("输入内容为：" + s);

            }

        }catch(Exception e){}

    }


}
	

