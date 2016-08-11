package com.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ShellUtils {

	private static JSch jsch;
	private static Session session;

	/**
	 * 连接到指定的IP
	 * @throws JSchException
	 */
	public static void  connect(String user,String passwd,String host) throws JSchException {

		jsch = new JSch();
		session = jsch.getSession(user,host,22);
		session.setPassword(passwd);

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();
	}

	/**
	 *执行相关的命令 ?
	 * @throws JSchException
	 */
	public static void execCmd(String command,String user,String passwd,String host) throws JSchException {

		connect(user, passwd, host);
		System.err.println("connect to "+host);
		BufferedReader reader = null;
		Channel channel = null;

		try{
			if (command != null)
			{
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command);

				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);

				channel.connect();
				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				String buf = null;
				while((buf = reader.readLine()) != null)
				{
					System.out.println(buf);
				}
			}
		} catch(IOException e)
		{
			e.printStackTrace();
		}catch (JSchException e) {
			e.printStackTrace();
		} finally{

			try {

				reader.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

			channel.disconnect();
			session.disconnect();
		}


	}

	/**
	 * 执行相关命令
	 * 界面交互
	 * param args
	 * @throws IOException
	 */
	public static void reCmd(String user,String passwd,String host) throws JSchException, IOException {

		connect(user, passwd, host);
		System.err.println("connect to "+host);
//	BufferedReader reader = null;
		Channel channel = null;
		byte[] b = new byte[1024];
		int n = 0;
//try{
//     Channel channel = session.openChannel("exec");
		while(true){
			System.out.println("请输入：");
			n = System.in.read(b);
//			String command = new String(b,0,n - 2);
			String command = new String(b,0,n-3);
			if(command.equalsIgnoreCase("quit"))
			{
				break; //结束循环
			}
//		      Channel  channel = session.openChannel("exec");
			channel = session.openChannel("exec");
			System.out.println("已接受命令");
			((ChannelExec) channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			if(channel.isConnected()==false)
			{
				System.out.println(channel.isConnected());
				channel.connect();
				System.out.println("重新连接");
			}
			System.out.println("已接执行命令");
			InputStream in = channel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			System.out.println("打印输出结果");
			String buf = null;
			System.out.println(reader.read());
			if(reader.read() != -1)
			{
				while((buf = reader.readLine()) != null)
				{
					System.out.println(buf);
				}
				reader.close();
				System.out.println("输出结果打印完毕");
			}
			else
				continue;
			reader.close();
//		        channel.disconnect();
		}
//     } catch(IOException e)
//     {
//    	 e.printStackTrace();
//     }catch (JSchException e) {
//		e.printStackTrace();
//	} finally{

		System.out.println(":::::::::::::");
		channel.disconnect();
		session.disconnect();
//	}

	}




	public static void main(String[] args) throws IOException{

		try {
//			 String cmd = "cd /tmp" + ";" + "ls"; 
//			ShellUtils.execCmd(cmd, "root", "zteroot819", "10.8.4.70");
			ShellUtils.reCmd("root", "kunteng888", "192.168.20.70");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			System.out.println("out of host");
		}


	}





}
