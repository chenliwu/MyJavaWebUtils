package email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springMVC-config.xml"})
@WebAppConfiguration
public class EmailSenderTest1 {


    @Test
    public void sendSimpleEmail() {
        try{
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            //使用SSL，这个设置很关键
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            mailSender.setJavaMailProperties(properties);
            mailSender.setHost("smtp.exmail.qqzz.com");
            mailSender.setPort(465);
            mailSender.setUsername("123123@bytter.com");
            mailSender.setPassword("123123");

            SimpleMailMessage message = new SimpleMailMessage();//消息构造器
            message.setFrom("12123123@bytter.com");//发件人
            message.setTo("123131321@qq.com");//收件人
            message.setSubject("Spring Email Test");//主题
            message.setText("hello world!!");//正文
            mailSender.send(message);
            System.out.println("邮件发送完毕");
        }catch (Exception e){
            System.out.println("异常："+e.getMessage());
        }
    }

    /**
     * 发送富文本内容的Email
     *
     * @throws MessagingException
     */
    @Test
    public void sendRichEmail() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        //使用SSL，这个设置很关键
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(properties);
        mailSender.setHost("smtp.exmail.qq.com");
        mailSender.setPort(465);
        mailSender.setUsername("12312321@bytter.com");
        mailSender.setPassword("1231231");

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("12312@bytter.com");
            helper.setTo("12312321@qq.com");
            helper.setSubject("Spring Email Test");
            helper.setText("<html><body><img src='cid:testLogo'>"
                    + "<h4>Hello World!!!</h4>"
                    + "</body></html>", true);//第二个参数表明这是一个HTML

            mailSender.send(message);
            System.out.println("邮件发送完毕");
        } catch (Exception e){
            System.out.println("异常："+e.getMessage());
        }

    }


}
