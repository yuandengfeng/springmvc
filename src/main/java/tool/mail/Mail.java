package tool.mail;

import org.apache.commons.mail.HtmlEmail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2016/4/25.
 */
public class Mail {

    public static int sendEmail(String hostName,String emailFrom,String nameFrom,String subject,String content,String username,String password) {
        int flag = 0;
        try {
            // 设置邮件信息
            HtmlEmail hemail = new HtmlEmail();
            hemail.setHostName(hostName);
            hemail.setAuthentication(username, password);// 发件服务器的帐号和密码

            // 发送给单个收件人邮箱
            // hemail.addTo(emailTo,nameTo);

        /* 发送给多个收件人邮箱 */
            List<InternetAddress> inter = new ArrayList<InternetAddress>();
            inter.add(new InternetAddress("yuandengfeng@kunteng.org"));
//            inter.add(new InternetAddress("qinxiaoyao@kunteng.org"));
//            inter.add(new InternetAddress("luzhirui@kunteng.org"));
            hemail.setTo(inter);

            hemail.setFrom(emailFrom, nameFrom);// 发件人、发件人称呼

            hemail.setSubject(subject);// 主题
            hemail.setCharset("GBK");// 设置编码格式
            hemail.setHtmlMsg(content);// 设置内容

//            List<String> attach_list = new ArrayList<String>();
//            attach_list.add("一级渠道" + fileName);
//            attach_list.add("二级渠道" + fileName);
//
//            // 添加附件
//            try {
//                for (String attach_f : attach_list) {
//                    EmailAttachment attachment = new EmailAttachment();
//                    attachment.setDisposition(EmailAttachment.ATTACHMENT);
//                    attachment.setName(MimeUtility.encodeText(attach_f)); // 设置附件名字
//                    attachment.setPath(path + attach_f);// 附件路径
//                    hemail.attach(attachment);// 添加附件
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            hemail.send();

//            // 邮件发送后删除磁盘上附件
//            for (String attach_file : attach_list) {
//                File f = new File(path + attach_file);
//                f.delete();
//            }

            flag = 1;
            System.out.println("发送成功了!");
            System.out.println("execute success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static int sendEmailTo( List<InternetAddress> emailTo,String subject,String content) {
        int flag = 0;
        try {
            // 设置邮件信息
            HtmlEmail hemail = new HtmlEmail();
            hemail.setHostName("smtp.exmail.qq.com");
            hemail.setAuthentication("yuandengfeng@kunteng.org", "KTroot1215");// 发件服务器的帐号和密码

            // 发送给单个收件人邮箱
            // hemail.addTo(emailTo,nameTo);
        /* 发送给多个收件人邮箱 */

//            List<InternetAddress> inter = new ArrayList<InternetAddress>();
//            inter.add(new InternetAddress(emailTo));
//            inter.add(new InternetAddress("dongqiang@kunteng.org"));
//            inter.add(new InternetAddress("shiyun@kunteng.org"));
            List<InternetAddress> inter =emailTo;
            hemail.setTo(inter);

            hemail.setFrom("yuandengfeng@kunteng.org","袁登峰");// 发件人、发件人称呼

            hemail.setSubject(subject);// 主题
            hemail.setCharset("GBK");// 设置编码格式
            hemail.setHtmlMsg(content);// 设置内容

//            List<String> attach_list = new ArrayList<String>();
//            attach_list.add("一级渠道" + fileName);
//            attach_list.add("二级渠道" + fileName);
//
//            // 添加附件
//            try {
//                for (String attach_f : attach_list) {
//                    EmailAttachment attachment = new EmailAttachment();
//                    attachment.setDisposition(EmailAttachment.ATTACHMENT);
//                    attachment.setName(MimeUtility.encodeText(attach_f)); // 设置附件名字
//                    attachment.setPath(path + attach_f);// 附件路径
//                    hemail.attach(attachment);// 添加附件
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            hemail.send();

//            // 邮件发送后删除磁盘上附件
//            for (String attach_file : attach_list) {
//                File f = new File(path + attach_file);
//                f.delete();
//            }

            flag = 1;
            System.out.println("发送成功了!");
            System.out.println("execute success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


//    private MimeMessage mimeMsg; //MIME邮件对象
//    private Session session; //邮件会话对象
//    private Properties props; //系统属性
//    private boolean needAuth = false; //smtp是否需要认证
//    //smtp认证用户名和密码
//    private String username;
//    private String password;
//    private Multipart mp; //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
//
//    /**
//     * Constructor
//     * @param smtp 邮件发送服务器
//     */
//    public Mail(String smtp){
//        setSmtpHost(smtp);
//        createMimeMessage();
//    }
//
//    /**
//     * 设置邮件发送服务器
//     * @param hostName String
//     */
//    public void setSmtpHost(String hostName) {
//        System.out.println("设置系统属性：mail.smtp.host = "+hostName);
//        if(props == null)
//            props = System.getProperties(); //获得系统属性对象
//        props.put("mail.smtp.host",hostName); //设置SMTP主机
//    }
//
//
//    /**
//     * 创建MIME邮件对象
//     * @return
//     */
//    public boolean createMimeMessage()
//    {
//        try {
//            System.out.println("准备获取邮件会话对象！");
//            session = Session.getDefaultInstance(props,null); //获得邮件会话对象
//        }
//        catch(Exception e){
//            System.err.println("获取邮件会话对象时发生错误！"+e);
//            return false;
//        }
//
//        System.out.println("准备创建MIME邮件对象！");
//        try {
//            mimeMsg = new MimeMessage(session); //创建MIME邮件对象
//            mp = new MimeMultipart();
//
//            return true;
//        } catch(Exception e){
//            System.err.println("创建MIME邮件对象失败！"+e);
//            return false;
//        }
//    }
//
//    /**
//     * 设置SMTP是否需要验证
//     * @param need
//     */
//    public void setNeedAuth(boolean need) {
//        System.out.println("设置smtp身份认证：mail.smtp.auth = "+need);
//        if(props == null) props = System.getProperties();
//        if(need){
//            props.put("mail.smtp.auth","true");
//        }else{
//            props.put("mail.smtp.auth","false");
//        }
//    }
//
//    /**
//     * 设置用户名和密码
//     * @param name
//     * @param pass
//     */
//    public void setNamePass(String name,String pass) {
//        username = name;
//        password = pass;
//    }
//
//    /**
//     * 设置邮件主题
//     * @param mailSubject
//     * @return
//     */
//    public boolean setSubject(String mailSubject) {
//        System.out.println("设置邮件主题！");
//        try{
//            mimeMsg.setSubject(mailSubject);
//            return true;
//        }
//        catch(Exception e) {
//            System.err.println("设置邮件主题发生错误！");
//            return false;
//        }
//    }
//
//    /**
//     * 设置邮件正文
//     * @param mailBody String
//     */
//    public boolean setBody(String mailBody) {
//        try{
//            BodyPart bp = new MimeBodyPart();
//            bp.setContent(""+mailBody,"text/html;charset=GBK");
//            mp.addBodyPart(bp);
//
//            return true;
//        } catch(Exception e){
//            System.err.println("设置邮件正文时发生错误！"+e);
//            return false;
//        }
//    }
//    /**
//     * 添加附件
//     * @param filename String
//     */
//    public boolean addFileAffix(String filename) {
//
//        System.out.println("增加邮件附件："+filename);
//        try{
//            BodyPart bp = new MimeBodyPart();
//            FileDataSource fileds = new FileDataSource(filename);
//            bp.setDataHandler(new DataHandler(fileds));
//            bp.setFileName(fileds.getName());
//
//            mp.addBodyPart(bp);
//
//            return true;
//        } catch(Exception e){
//            System.err.println("增加邮件附件："+filename+"发生错误！"+e);
//            return false;
//        }
//    }
//
//    /**
//     * 设置发信人
//     * @param from String
//     */
//    public boolean setFrom(String from) {
//        System.out.println("设置发信人！");
//        try{
//            mimeMsg.setFrom(new InternetAddress(from)); //设置发信人
//            return true;
//        } catch(Exception e) {
//            return false;
//        }
//    }
//    /**
//     * 设置收信人
//     * @param to String
//     */
//    public boolean setTo(String to){
//        if(to == null)return false;
//        try{
//            mimeMsg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
//            return true;
//        } catch(Exception e) {
//            return false;
//        }
//    }
//
//    /**
//     * 设置抄送人
//     * @param copyto String
//     */
//    public boolean setCopyTo(String copyto)
//    {
//        if(copyto == null)return false;
//        try{
//            mimeMsg.setRecipients(Message.RecipientType.CC,(Address[])InternetAddress.parse(copyto));
//            return true;
//        }
//        catch(Exception e)
//        { return false; }
//    }
//
//    /**
//     * 发送邮件
//     */
//    public boolean sendOut()
//    {
//        try{
//            mimeMsg.setContent(mp);
//            mimeMsg.saveChanges();
//            System.out.println("正在发送邮件....");
//
//            Session mailSession = Session.getInstance(props,null);
//            Transport transport = mailSession.getTransport("smtp");
//            transport.connect((String)props.get("mail.smtp.host"),username,password);
//            transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO));
//            transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC));
//            //transport.send(mimeMsg);
//
//            System.out.println("发送邮件成功！");
//            transport.close();
//
//            return true;
//        } catch(Exception e) {
//            System.err.println("邮件发送失败！"+e);
//            return false;
//        }
//    }
//
//    /**
//     * 调用sendOut方法完成邮件发送
//     * @param smtp
//     * @param from
//     * @param to
//     * @param subject
//     * @param content
//     * @param username
//     * @param password
//     * @return boolean
//     */
//    public static boolean send(String smtp,String from,String to,String subject,String content,String username,String password) {
//        Mail theMail = new Mail(smtp);
//        theMail.setNeedAuth(true); //需要验证
//
//        if(!theMail.setSubject(subject)) return false;
//        if(!theMail.setBody(content)) return false;
//        if(!theMail.setTo(to)) return false;
//        if(!theMail.setFrom(from)) return false;
//        theMail.setNamePass(username,password);
//
//        if(!theMail.sendOut()) return false;
//        return true;
//    }
//
//    /**
//     * 调用sendOut方法完成邮件发送,带抄送
//     * @param smtp
//     * @param from
//     * @param to
//     * @param copyto
//     * @param subject
//     * @param content
//     * @param username
//     * @param password
//     * @return boolean
//     */
//    public static boolean sendAndCc(String smtp,String from,String to,String copyto,String subject,String content,String username,String password) {
//        Mail theMail = new Mail(smtp);
//        theMail.setNeedAuth(true); //需要验证
//
//        if(!theMail.setSubject(subject)) return false;
//        if(!theMail.setBody(content)) return false;
//        if(!theMail.setTo(to)) return false;
//        if(!theMail.setCopyTo(copyto)) return false;
//        if(!theMail.setFrom(from)) return false;
//        theMail.setNamePass(username,password);
//
//        if(!theMail.sendOut()) return false;
//        return true;
//    }
//
//    /**
//     * 调用sendOut方法完成邮件发送,带附件
//     * @param smtp
//     * @param from
//     * @param to
//     * @param subject
//     * @param content
//     * @param username
//     * @param password
//     * @param filename 附件路径
//     * @return
//     */
//    public static boolean send(String smtp,String from,String to,String subject,String content,String username,String password,String filename) {
//        Mail theMail = new Mail(smtp);
//        theMail.setNeedAuth(true); //需要验证
//
//        if(!theMail.setSubject(subject)) return false;
//        if(!theMail.setBody(content)) return false;
//        if(!theMail.addFileAffix(filename)) return false;
//        if(!theMail.setTo(to)) return false;
//        if(!theMail.setFrom(from)) return false;
//        theMail.setNamePass(username,password);
//
//        if(!theMail.sendOut()) return false;
//        return true;
//    }
//
//    /**
//     * 调用sendOut方法完成邮件发送,带附件和抄送
//     * @param smtp
//     * @param from
//     * @param to
//     * @param copyto
//     * @param subject
//     * @param content
//     * @param username
//     * @param password
//     * @param filename
//     * @return
//     */
//    public static boolean sendAndCc(String smtp,String from,String to,String copyto,String subject,String content,String username,String password,String filename) {
//        Mail theMail = new Mail(smtp);
//        theMail.setNeedAuth(true); //需要验证
//
//        if(!theMail.setSubject(subject)) return false;
//        if(!theMail.setBody(content)) return false;
//        if(!theMail.addFileAffix(filename)) return false;
//        if(!theMail.setTo(to)) return false;
//        if(!theMail.setCopyTo(copyto)) return false;
//        if(!theMail.setFrom(from)) return false;
//        theMail.setNamePass(username,password);
//
//        if(!theMail.sendOut()) return false;
//        return true;
//    }
}
