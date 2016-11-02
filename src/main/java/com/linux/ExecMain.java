package com.linux;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;

/**
 * Created by yuandengfeng on 2016/11/1.
 */
public class ExecMain {

    public static void main(String[] arg) {
        try {
            JSch jsch = new JSch();

            String host = "192.168.0.81";
            String user = "root";
            String passwd = "kunteng888";

            Session session = jsch.getSession(user, host, 22);
            session.setPassword(passwd);
            JSch.setConfig("StrictHostKeyChecking", "no"); //设置无需knowhosts认证或session.setConfig("StrictHostKeyChecking", "no");

            session.connect();
//            String command = "set|grep SSH";
            String command = "mkdir -p /opt/xx/tt/dsd";  //一次只能一个命令生效，可将多个命令拼接在一起，或执行一个shell脚本


            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);


            // X Forwarding
            // channel.setXForwarding(true);

            //channel.setInputStream(System.in);
            channel.setInputStream(null);

            //channel.setOutputStream(System.out);

            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
            //((ChannelExec)channel).setErrStream(fos);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }





}
