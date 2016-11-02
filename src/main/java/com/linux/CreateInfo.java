package com.linux;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;

/**
 * Created by yuandengfeng on 2016/11/2.
 */
public class CreateInfo {

    private String host;
    private String user;
    private String passwd;

    public CreateInfo(String host,String user,String passwd)
    {
        this.host=host;
        this.user=user;
        this.passwd=passwd;
    }


    public  void createFtpInfo(String ftpUser,String ftpPasswd)
    {
        try {
            JSch jsch = new JSch();

            Session session = jsch.getSession(user, host, 22);
            session.setPassword(passwd);
            JSch.setConfig("StrictHostKeyChecking", "no"); //设置无需knowhosts认证或session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println("session.connect()");

//            String sh ="sh /home/vsftp/user/create";

//            String mkdir="mkdir -p /opt/nn \n touch /opt/nn/dd";
             //创建ftp账号命令
              String createFtpUser="mkdir -p /home/vsftp/"+ftpUser+
                      " \n mkdir -p /home/vsftp/"+ftpUser+"/aa"+
                      " \n mkdir -p /home/vsftp/"+ftpUser+"/bb"+
                      " \n mkdir -p /home/vsftp/"+ftpUser+"/cc"+
                      " \n chown -R virtusers:virtusers /home/vsftp/"+ftpUser+
                      " \n cp /etc/vsftpd/vconf/admin /etc/vsftpd/vconf/"+ftpUser+
                      " \n sed -i \"s/admin/"+ftpUser+"/g\" /etc/vsftpd/vconf/"+ftpUser+
                      " \n echo "+ftpUser+" >>/etc/vsftpd/virtusers" +
                      " \n echo "+ftpPasswd+" >>/etc/vsftpd/virtusers"+
                      " \n db_load -T -t hash -f /etc/vsftpd/virtusers /etc/vsftpd/virtusers.db";

            Channel channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand(createFtpUser);

            channel.setInputStream(null);
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


     public static void main(String[] args)
      {

          //连接到192.168.0.81，创建账号和密码都是aa的ftp
          CreateInfo cc =new  CreateInfo("192.168.0.81","root","kunteng888");
          cc.createFtpInfo("aa","aa");
      }






}
