package com.linux;

public class JshMain {

    public static void main(String[] args) {

        byte[] b = new byte[1024];

        //��Ч���ݸ���

        int n = 0;

        try{

            while(true){

                //��ʾ��Ϣ

                System.out.println("�����룺");

                //��ȡ����

                n = System.in.read(b);

                //ת��Ϊ�ַ���

                String s = new String(b,0,n - 2);

                //�ж��Ƿ���quit

                if(s.equalsIgnoreCase("quit")){

                    break; //����ѭ��

                }

                //��������

                System.out.println("��������Ϊ��" + s);

            }

        }catch(Exception e){}

    }


}
	

