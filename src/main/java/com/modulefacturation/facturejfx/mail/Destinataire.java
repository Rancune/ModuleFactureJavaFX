package com.modulefacturation.facturejfx.mail;

public class Destinataire {

   private String email;
   private String firstName;
   private String lastName;
   private String path;
   private String envoyeur;


   public Destinataire(String email, String path, String envoyeur){
      this.email = email;
      this.path = path;
      this.envoyeur = envoyeur;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public String getEnvoyeur() {
      return envoyeur;
   }

   public void setEnvoyeur(String envoyeur) {
      this.envoyeur = envoyeur;
   }
}
