package ru.carbay.promoter.services.pager_duty;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class PagerDutyAlerts {

    private static final String pager_duty_email = "promoter@carbay.pagerduty.com";
    private static final String support_email_username = "support@carbay.ru";
    private static final String support_email_password = "1ab*90229033";

    public static void alert(String subject, String msg) {
        String username = PagerDutyAlerts.support_email_username;
        String password = PagerDutyAlerts.support_email_password;
        String from = "Carbay Support <support@carbay.ru>";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        final String u = username;
        final String p = password;
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(u, p);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);
            message.setContent(message, "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pager_duty_email));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        alert("test sub", "test msg");
    }
}
