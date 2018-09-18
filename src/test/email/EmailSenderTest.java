package email;

import com.charlie.ssm.demo.email.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springMVC-config.xml"})
@WebAppConfiguration
public class EmailSenderTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleEmail(){
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom("11111@qq.com");//发件人
        message.setTo("1111@qq.com");//收件人
        message.setSubject("Spring Email Test");//主题
        message.setText("hello world!!");//正文
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }

}
