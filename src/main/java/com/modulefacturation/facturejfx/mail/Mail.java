package com.modulefacturation.facturejfx.mail;

import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Mail {


    public static String testUserMail = "brian.afpa@gmail.com";
    public static String testUserPassword = "33Apfa33";



    // for example, smtp.mailgun.org
    private static  String SMTP_SERVER = "smtp-relay.gmail.com"; //renseigner le serv SMTP utilisé
    private static  String USERNAME = testUserMail; // renseigner l'adresse mail du compte qui va envoyer le mail
    private static  String PASSWORD = testUserPassword; // renseigner le mot de passe du compte mail qui va envoyer le mail

    private static String EMAIL_FROM = testUserMail;
    private static  String EMAIL_TO = testUserMail;
    private static  String EMAIL_TO_CC = "";

    private static  String EMAIL_SUBJECT = "Test Send Email via SMTP (ATTACHMENT)";
    private static  String EMAIL_TEXT = "Hello Java Mail \n This is a test for attachment ";

    public Mail() {
    }


    //Mon Constructeur
    public Mail(Destinataire destinataire){
        System.out.println(destinataire.getEmail());
        this.EMAIL_FROM = destinataire.getEnvoyeur();
        this.EMAIL_TO = destinataire.getEmail();
       //this.path = destinataire.path;
    }



    public void envoyerMail() {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            msg.setFrom(new InternetAddress(EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

            msg.setSubject(EMAIL_SUBJECT);

            // text
            MimeBodyPart p1 = new MimeBodyPart();
            p1.setText(EMAIL_TEXT);

            // file
            MimeBodyPart p2 = new MimeBodyPart();
            FileDataSource fds = new FileDataSource("example.txt");// ici on recup le fichier joint
            //FileDataSource fds = new FileDataSource(path);
            p2.setDataHandler(new DataHandler(fds));
            p2.setFileName(fds.getName());

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(p1);
            mp.addBodyPart(p2);

            msg.setContent(mp);


            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);//telnet smtp.gmail.com 25 pour voir si on se connect au serv gmail ou si le parefeu bloque la connexion


            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}

// Java program to send email




