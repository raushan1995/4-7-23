package com.email.Service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;


@Service
public class EmailService {

    public boolean sendEmail(String subject, String message,String to)
    {

        boolean f =false;
        String from ="priyanshu1996r@gmail.com";
        
        sendAttach(message,subject,to,from);
        //variable for gmail
          String host = "smtp.gmail.com";

          Properties properties = System.getProperties();
          System.out.println("PROPERTIES" +properties);

          //host set

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",465);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("priyanshu1996r@gmail.com","znvv bwzm oyik tury");
            }
        });

        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);

        try{
            m.setFrom(from);

            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            m.setSubject(subject);

            m.setText(message);

            Transport.send(m);

            System.out.println("sent success");
            f = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return  f;
    }

    private void sendAttach(String message, String subject, String to,String from) {

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        System.out.println("PROPERTIES" +properties);

        //host set

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",465);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("priyanshu1996r@gmail.com","znvv bwzm oyik tury");
            }
        });

        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);

        try{
            m.setFrom(from);

            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            m.setSubject(subject);

            m.setText(message);

            Transport.send(m);

            System.out.println("sent success");

        }catch(Exception e){
            e.printStackTrace();
        }

        String path="C:\\Users\\rkuma568\\Downloads\\Report.jpeg";

        MimeMultipart mimeMultipart = new MimeMultipart();

        MimeBodyPart textMime = new MimeBodyPart();

        MimeBodyPart fileMime = new MimeBodyPart();

        try{
            textMime.setText(message);
            File file = new File(path);
            fileMime.attachFile(file);
            mimeMultipart.addBodyPart(textMime);
            mimeMultipart.addBodyPart(fileMime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

