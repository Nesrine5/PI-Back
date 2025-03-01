package com.bezkoder.spring.datajpa.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;



   public void sendEmail(String to, String body, String topic){
        try {
            System.out.println("Mail sending is started");
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("mutlueren01@gmail.com");
            simpleMailMessage.setTo("nesrine.kattoussi@etudiant-fsegt.utm.tn");
            simpleMailMessage.setSubject("demande de chèque");
            simpleMailMessage.setText("Cher client,nous vous informons que votre demande de chéque n°23234553 de type Chéquier Correspondance a été accepter tu peut se presenter  à la banque pour retirer à partir de 08/03/2022");
            javaMailSender.send(simpleMailMessage);
            System.out.println("Mail sending is completed");
        }catch (Exception e){
            System.out.println("Error occured: "+e.getMessage());
        }
    }
   public void sendEmailRef(String to, String body, String topic){
       try {
           System.out.println("Mail sending is started");
           SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
           simpleMailMessage.setFrom("mutlueren01@gmail.com");
           simpleMailMessage.setTo("nesrine.kattoussi@etudiant-fsegt.utm.tn");
           simpleMailMessage.setSubject("demande de chèque");
           simpleMailMessage.setText("Cher client,nous vous informons que votre demande de chéque n°23234553 de type Chéquier Correspondance a été refuser");
           javaMailSender.send(simpleMailMessage);
           System.out.println("Mail sending is completed");
       }catch (Exception e){
           System.out.println("Error occured: "+e.getMessage());
       }
   }
   public void sendEmailAcCarte(String to, String body, String topic){
       try {
           System.out.println("Mail sending is started");
           SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
           simpleMailMessage.setFrom("mutlueren01@gmail.com");
           simpleMailMessage.setTo("nesrine.kattoussi@etudiant-fsegt.utm.tn");
           simpleMailMessage.setSubject("demande de carte");
           simpleMailMessage.setText("Cher client,nous vous informons que votre demande de carte n°23234553 de type Visa Premier Card a été accepter tu peut se presenter  à la banque pour retirer à partir de 08/03/2022");
           javaMailSender.send(simpleMailMessage);
           System.out.println("Mail sending is completed");
       }catch (Exception e){
           System.out.println("Error occured: "+e.getMessage());
       }
   }
  public void sendEmailRefCarte(String to, String body, String topic){
      try {
          System.out.println("Mail sending is started");
          SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
          simpleMailMessage.setFrom("mutlueren01@gmail.com");
          simpleMailMessage.setTo("nesrine.kattoussi@etudiant-fsegt.utm.tn");
          simpleMailMessage.setSubject("demande de carte");
          simpleMailMessage.setText("Cher client,nous vous informons que votre demande de carte n°23234553 de type Visa Premier Card a été refuser");
          javaMailSender.send(simpleMailMessage);
          System.out.println("Mail sending is completed");
      }catch (Exception e){
          System.out.println("Error occured: "+e.getMessage());
      }
  }
}