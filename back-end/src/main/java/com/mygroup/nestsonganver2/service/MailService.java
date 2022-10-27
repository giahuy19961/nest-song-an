/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.constant.*;
import com.mygroup.nestsonganver2.utils.MailConfig;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mygroup.nestsonganver2.utils.Utils;
/**
 *
 * @author dd220
 */
public class MailService {
    
    public MailService() {}
    
//    private static MailService mailService;
//    public static MailService getMailService() {
//        if (mailService == null)
//            mailService = new MailService();
//        return mailService;
//    }
    
    Properties properties = new Properties();
    Session session;
    
    private void setProperties(){
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", MailConfig.HOST_NAME);
        properties.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.port", MailConfig.SSL_PORT);
    }
    
    private void setSession() {
        session = Session.getDefaultInstance(this.properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
            }
        });
    }
    
    public String sendMail(String email) throws Exception {
        setProperties();;
        setSession();
        MimeMessage message = new MimeMessage(this.session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Sending OTP to " + email);
        String OTP = Utils.GetOTP(6);
        message.setText("Your OTP to is: " + OTP);

        Transport.send(message);
        return OTP;
    }
}
