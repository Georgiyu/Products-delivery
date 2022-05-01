package com.example.kyrsovaia;



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailService {
    public static void SendingEmail(String email, String name) throws IOException, MessagingException {

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host","smtp.yandex.ru");
        properties.put("mail.smtp.user", "georgyposysaev@yandex.ru");
        properties.put("mail.smtp.socketFactory.port",465);
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port",465);
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message1 = new MimeMessage(mailSession);
        message1.setFrom(new InternetAddress("georgyposysaev@yandex.ru"));
        String pochta = email;
        message1.addRecipient(Message.RecipientType.TO, new InternetAddress(pochta));
        message1.setSubject("Письмо от 'Доставка продуктов'");
        message1.setText("Доброго времени суток " + name + ". Мы получили вашу заявку для записи на прием. Вскоре, на указанный в заявке номер, перезвонит наш менеджер для уточнения деталей заказа." + "\n" + "\n" + "\n" + "С наилучшими пожеланиями" + "\n" + "Доставка продуктов");
        Transport tr = mailSession.getTransport();
        tr.connect("georgyposysaev@yandex.ru", "romapivovarov");
        tr.sendMessage(message1, message1.getAllRecipients());
        tr.close();
    }
}

