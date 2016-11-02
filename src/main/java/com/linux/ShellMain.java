package com.linux;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * Created by yuandengfeng on 2016/11/1.
 */
public class ShellMain {


    public static void main(String[] arg) {

        try {
            JSch jsch = new JSch();


            String host = "192.168.0.81";
            if (arg.length > 0) {
                host = arg[0];
            } else {
                host = "root@192.168.0.81";
            }
            String user = "root";
            host = "192.168.0.81";

            Session session = jsch.getSession(user, host, 22);

            String passwd = "kunteng888";
            session.setPassword(passwd);
            JSch.setConfig("StrictHostKeyChecking", "no"); //设置无需knowhosts认证或session.setConfig("StrictHostKeyChecking", "no");


            // It must not be recommended, but if you want to skip host-key check,
            // invoke following,
            // session.setConfig("StrictHostKeyChecking", "no");

            //session.connect();
            session.connect(30000);   // making a connection with timeout.

            Channel channel = session.openChannel("shell");

            // Enable agent-forwarding.
            //((ChannelShell)channel).setAgentForwarding(true);

            channel.setInputStream(System.in);
      /*
      // a hack for MS-DOS prompt on Windows.
      channel.setInputStream(new FilterInputStream(System.in){
          public int read(byte[] b, int off, int len)throws IOException{
            return in.read(b, off, (len>1024?1024:len));
          }
        });
       */

            channel.setOutputStream(System.out);

      /*
      // Choose the pty-type "vt102".
      ((ChannelShell)channel).setPtyType("vt102");
      */

      /*
      // Set environment variable "LANG" as "ja_JP.eucJP".
      ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
      */

            //channel.connect();
            channel.connect(3 * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
