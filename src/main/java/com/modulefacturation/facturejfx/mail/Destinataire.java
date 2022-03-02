package com.modulefacturation.facturejfx.mail;

public class Destinataire {

   public String email;
   public String firstName;
   public String lastName;
   public String path;
   public String envoyeur;


   public Destinataire(String email, String path, String envoyeur){
      this.email = email;
      this.path = path;
      this.envoyeur = envoyeur;
   }



}
