package email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mvc.xml"})
@WebAppConfiguration
public class EmailSenderTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom("11111@qq.com");//发件人
        message.setTo("1111@qq.com");//收件人
        message.setSubject("Spring Email Test");//主题
        message.setText("hello world!!");//正文
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }





    /**
     * 发送带有附件的email
     *
     * @throws MessagingException
     */
    @Test
    public void sendEmailWithAttachment() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        //构造消息helper，第二个参数表明这个消息是multipart类型的
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("30075213@qq.com");
        helper.setTo("1047184259@qq.com");
        helper.setSubject("Spring Email Test");
        helper.setText("这是一个带有附件的消息。\n这是一个带有附件的消息。");
        //使用Spring的FileSystemResource来加载附件
        FileSystemResource image = new FileSystemResource("E:\\test.jpg");
        FileSystemResource image1 = new FileSystemResource("E:\\test1.jpg");
        System.out.println(image.exists());
        //添加附加，第一个参数为添加到Email中附件的名称，第二个人参数是图片资源
        // 注意第一个参数要添加文件的后缀名次，不然无法下载
        helper.addAttachment("hzxy.jpg", image);
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }


    /**
     * 发送富文本内容的Email
     *
     * @throws MessagingException
     */
    @Test
    public void sendRichEmail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("30075213@qq.com");
        helper.setTo("1047184259@qq.com");
        helper.setSubject("Spring Email Test");
        helper.setText("<html><body><img src='cid:testLogo'>"
                + "<h4>Hello World!!!</h4>"
                + "</body></html>", true);//第二个参数表明这是一个HTML
        //src='cid:testLogo'表明在消息中会有一部分是图片并以testLogo来进行标识
        //ClassPathResource image = new ClassPathResource("logo.jpg");
        FileSystemResource image = new FileSystemResource("E:\\hzxy.jpg");
        System.out.println(image.exists());
        helper.addInline("testLogo", image);//添加内联图片，第一个参数表明内联图片的标识符，第二个参数是图片的资源引用
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }


}
