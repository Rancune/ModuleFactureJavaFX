package com.modulefacturation.facturejfx.client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Prestataire {

    private String firstName;
    private String lastName;
    private String adress;
    private String mail;
    private String motDePasseMail;
    private String tel;
    private String siret;
    private String web;
    private String banque;
    private String iban;
    private String logo;



    public Prestataire(String lastName, String firstName, String adress, String mail, String motDePasseMail, String tel, String siret, String web, String banque, String iban) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.mail = mail;
        this.motDePasseMail = motDePasseMail;
        this.tel = tel;
        this.siret = siret;
        this.web = web;
        this.banque= banque;
        this.iban = iban;
    }


    //Sauvegarde des infos dans un JSON
    public void writeJson(){

        //Informations Prestataire
        JSONObject prestataireDetails = new JSONObject();
        prestataireDetails.put("firstName",  this.firstName);
        prestataireDetails.put("lastName", this.lastName);
        prestataireDetails.put("adress", this.adress);
        prestataireDetails.put("mail", this.mail);
        prestataireDetails.put("motDePasseMail", this.motDePasseMail);
        prestataireDetails.put("tel", this.tel);
        prestataireDetails.put("siret", this.siret);
        prestataireDetails.put("web", this.web);
        prestataireDetails.put("banque", this.banque);
        prestataireDetails.put("iban", this.iban);
        prestataireDetails.put("logo", this.logo);

        //JSONObject prestataireObject = new JSONObject();
       // prestataireObject.put("prestataire", prestataireDetails);




        JSONArray factureListInfo = new JSONArray();
        factureListInfo.add(prestataireDetails);



        //Write JSON file
        try (FileWriter file = new FileWriter("prestataire.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(prestataireDetails.toJSONString());
            System.out.println("Info enregistrées dans le JSON");
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Lecture des infos du JSON si existant pour populer les champs infos du presta
    public static Object readJson(){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("prestataire.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

           // JSONArray prestataireInfo = (JSONArray) obj;
            //System.out.println(prestataireInfo);

            //Iterate over prestataire array
            //prestataireInfo.forEach( emp -> parsePrestataireObject( (JSONObject) emp ) );
            System.out.println("parse du json effectué");
            return obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void parsePrestataireObject(JSONObject prestataire) {

        //Get employee object within list
        JSONObject prestataireObject = (JSONObject) prestataire.get("employee");

        //Get employee first name
        String firstName = (String) prestataireObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) prestataireObject.get("lastName");
        System.out.println(lastName);

        //Get employee website name
        String website = (String) prestataireObject.get("website");
        System.out.println(website);
    }


    public Prestataire() {
    }




    public Prestataire(String lastName, String firstName, String adress, String mail, String password, String tel, String siret, String web, String banque, String iban, String logo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.mail = mail;
        this.motDePasseMail = password;
        this.tel = tel;
        this.siret = siret;
        this.web = web;
        this.banque= banque;
        this.iban = iban;
        this.logo = logo;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getMotDePasseMail() {
        return motDePasseMail;
    }

    public void setMotDePasseMail(String motDePasseMail) {
        this.motDePasseMail = motDePasseMail;
    }

    public String getBanque() {return banque;}

    public void setBanque(String banque) {this.banque = banque;}

    public String getIban() {return iban;}

    public void setIban(String iban) {this.iban = iban;}

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
