package tool.mail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class Main1 {

    public static void main(String[] args){
        String hostName = "smtp.exmail.qq.com";   //SMTP������
//        String hostName = "smtp.ym.163.com";
        String emailFrom = "yuandengfeng@kunteng.org";    //������
//        String emailFrom = "yuandengfeng@ambimmort.com";
        String  nameFrom= "Ԭ�Ƿ�";     //������
//        String copyto = "������";
        String subject = "����";
        String content = "����";
        String username="yuandengfeng@kunteng.org";
//        String username="yuandengfeng@ambimmort.com";
        String password="KTroot1215";
        List<InternetAddress> inter = new ArrayList<InternetAddress>();
        try {
            inter.add(new InternetAddress("yuandengfeng@kunteng.org"));
            inter.add(new InternetAddress("dongqiang@kunteng.org"));
            inter.add(new InternetAddress("shiyun@kunteng.org"));
        } catch (AddressException e) {
            e.printStackTrace();
        }

//        Mail.sendEmail( hostName, emailFrom, nameFrom, subject,  content, username, password);
        Mail.sendEmailTo(inter,"����","����");
    }
}
