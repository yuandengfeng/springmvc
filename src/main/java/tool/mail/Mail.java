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
            // �����ʼ���Ϣ
            HtmlEmail hemail = new HtmlEmail();
            hemail.setHostName(hostName);
            hemail.setAuthentication(username, password);// �������������ʺź�����

            // ���͸������ռ�������
            // hemail.addTo(emailTo,nameTo);

        /* ���͸�����ռ������� */
            List<InternetAddress> inter = new ArrayList<InternetAddress>();
            inter.add(new InternetAddress("yuandengfeng@kunteng.org"));
//            inter.add(new InternetAddress("qinxiaoyao@kunteng.org"));
//            inter.add(new InternetAddress("luzhirui@kunteng.org"));
            hemail.setTo(inter);

            hemail.setFrom(emailFrom, nameFrom);// �����ˡ������˳ƺ�

            hemail.setSubject(subject);// ����
            hemail.setCharset("GBK");// ���ñ����ʽ
            hemail.setHtmlMsg(content);// ��������

//            List<String> attach_list = new ArrayList<String>();
//            attach_list.add("һ������" + fileName);
//            attach_list.add("��������" + fileName);
//
//            // ��Ӹ���
//            try {
//                for (String attach_f : attach_list) {
//                    EmailAttachment attachment = new EmailAttachment();
//                    attachment.setDisposition(EmailAttachment.ATTACHMENT);
//                    attachment.setName(MimeUtility.encodeText(attach_f)); // ���ø�������
//                    attachment.setPath(path + attach_f);// ����·��
//                    hemail.attach(attachment);// ��Ӹ���
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            hemail.send();

//            // �ʼ����ͺ�ɾ�������ϸ���
//            for (String attach_file : attach_list) {
//                File f = new File(path + attach_file);
//                f.delete();
//            }

            flag = 1;
            System.out.println("���ͳɹ���!");
            System.out.println("execute success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static int sendEmailTo( List<InternetAddress> emailTo,String subject,String content) {
        int flag = 0;
        try {
            // �����ʼ���Ϣ
            HtmlEmail hemail = new HtmlEmail();
            hemail.setHostName("smtp.exmail.qq.com");
            hemail.setAuthentication("yuandengfeng@kunteng.org", "KTroot1215");// �������������ʺź�����

            // ���͸������ռ�������
            // hemail.addTo(emailTo,nameTo);
        /* ���͸�����ռ������� */

//            List<InternetAddress> inter = new ArrayList<InternetAddress>();
//            inter.add(new InternetAddress(emailTo));
//            inter.add(new InternetAddress("dongqiang@kunteng.org"));
//            inter.add(new InternetAddress("shiyun@kunteng.org"));
            List<InternetAddress> inter =emailTo;
            hemail.setTo(inter);

            hemail.setFrom("yuandengfeng@kunteng.org","Ԭ�Ƿ�");// �����ˡ������˳ƺ�

            hemail.setSubject(subject);// ����
            hemail.setCharset("GBK");// ���ñ����ʽ
            hemail.setHtmlMsg(content);// ��������

//            List<String> attach_list = new ArrayList<String>();
//            attach_list.add("һ������" + fileName);
//            attach_list.add("��������" + fileName);
//
//            // ��Ӹ���
//            try {
//                for (String attach_f : attach_list) {
//                    EmailAttachment attachment = new EmailAttachment();
//                    attachment.setDisposition(EmailAttachment.ATTACHMENT);
//                    attachment.setName(MimeUtility.encodeText(attach_f)); // ���ø�������
//                    attachment.setPath(path + attach_f);// ����·��
//                    hemail.attach(attachment);// ��Ӹ���
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            hemail.send();

//            // �ʼ����ͺ�ɾ�������ϸ���
//            for (String attach_file : attach_list) {
//                File f = new File(path + attach_file);
//                f.delete();
//            }

            flag = 1;
            System.out.println("���ͳɹ���!");
            System.out.println("execute success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


//    private MimeMessage mimeMsg; //MIME�ʼ�����
//    private Session session; //�ʼ��Ự����
//    private Properties props; //ϵͳ����
//    private boolean needAuth = false; //smtp�Ƿ���Ҫ��֤
//    //smtp��֤�û���������
//    private String username;
//    private String password;
//    private Multipart mp; //Multipart����,�ʼ�����,����,���������ݾ���ӵ����к�������MimeMessage����
//
//    /**
//     * Constructor
//     * @param smtp �ʼ����ͷ�����
//     */
//    public Mail(String smtp){
//        setSmtpHost(smtp);
//        createMimeMessage();
//    }
//
//    /**
//     * �����ʼ����ͷ�����
//     * @param hostName String
//     */
//    public void setSmtpHost(String hostName) {
//        System.out.println("����ϵͳ���ԣ�mail.smtp.host = "+hostName);
//        if(props == null)
//            props = System.getProperties(); //���ϵͳ���Զ���
//        props.put("mail.smtp.host",hostName); //����SMTP����
//    }
//
//
//    /**
//     * ����MIME�ʼ�����
//     * @return
//     */
//    public boolean createMimeMessage()
//    {
//        try {
//            System.out.println("׼����ȡ�ʼ��Ự����");
//            session = Session.getDefaultInstance(props,null); //����ʼ��Ự����
//        }
//        catch(Exception e){
//            System.err.println("��ȡ�ʼ��Ự����ʱ��������"+e);
//            return false;
//        }
//
//        System.out.println("׼������MIME�ʼ�����");
//        try {
//            mimeMsg = new MimeMessage(session); //����MIME�ʼ�����
//            mp = new MimeMultipart();
//
//            return true;
//        } catch(Exception e){
//            System.err.println("����MIME�ʼ�����ʧ�ܣ�"+e);
//            return false;
//        }
//    }
//
//    /**
//     * ����SMTP�Ƿ���Ҫ��֤
//     * @param need
//     */
//    public void setNeedAuth(boolean need) {
//        System.out.println("����smtp�����֤��mail.smtp.auth = "+need);
//        if(props == null) props = System.getProperties();
//        if(need){
//            props.put("mail.smtp.auth","true");
//        }else{
//            props.put("mail.smtp.auth","false");
//        }
//    }
//
//    /**
//     * �����û���������
//     * @param name
//     * @param pass
//     */
//    public void setNamePass(String name,String pass) {
//        username = name;
//        password = pass;
//    }
//
//    /**
//     * �����ʼ�����
//     * @param mailSubject
//     * @return
//     */
//    public boolean setSubject(String mailSubject) {
//        System.out.println("�����ʼ����⣡");
//        try{
//            mimeMsg.setSubject(mailSubject);
//            return true;
//        }
//        catch(Exception e) {
//            System.err.println("�����ʼ����ⷢ������");
//            return false;
//        }
//    }
//
//    /**
//     * �����ʼ�����
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
//            System.err.println("�����ʼ�����ʱ��������"+e);
//            return false;
//        }
//    }
//    /**
//     * ��Ӹ���
//     * @param filename String
//     */
//    public boolean addFileAffix(String filename) {
//
//        System.out.println("�����ʼ�������"+filename);
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
//            System.err.println("�����ʼ�������"+filename+"��������"+e);
//            return false;
//        }
//    }
//
//    /**
//     * ���÷�����
//     * @param from String
//     */
//    public boolean setFrom(String from) {
//        System.out.println("���÷����ˣ�");
//        try{
//            mimeMsg.setFrom(new InternetAddress(from)); //���÷�����
//            return true;
//        } catch(Exception e) {
//            return false;
//        }
//    }
//    /**
//     * ����������
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
//     * ���ó�����
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
//     * �����ʼ�
//     */
//    public boolean sendOut()
//    {
//        try{
//            mimeMsg.setContent(mp);
//            mimeMsg.saveChanges();
//            System.out.println("���ڷ����ʼ�....");
//
//            Session mailSession = Session.getInstance(props,null);
//            Transport transport = mailSession.getTransport("smtp");
//            transport.connect((String)props.get("mail.smtp.host"),username,password);
//            transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO));
//            transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC));
//            //transport.send(mimeMsg);
//
//            System.out.println("�����ʼ��ɹ���");
//            transport.close();
//
//            return true;
//        } catch(Exception e) {
//            System.err.println("�ʼ�����ʧ�ܣ�"+e);
//            return false;
//        }
//    }
//
//    /**
//     * ����sendOut��������ʼ�����
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
//        theMail.setNeedAuth(true); //��Ҫ��֤
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
//     * ����sendOut��������ʼ�����,������
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
//        theMail.setNeedAuth(true); //��Ҫ��֤
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
//     * ����sendOut��������ʼ�����,������
//     * @param smtp
//     * @param from
//     * @param to
//     * @param subject
//     * @param content
//     * @param username
//     * @param password
//     * @param filename ����·��
//     * @return
//     */
//    public static boolean send(String smtp,String from,String to,String subject,String content,String username,String password,String filename) {
//        Mail theMail = new Mail(smtp);
//        theMail.setNeedAuth(true); //��Ҫ��֤
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
//     * ����sendOut��������ʼ�����,�������ͳ���
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
//        theMail.setNeedAuth(true); //��Ҫ��֤
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
