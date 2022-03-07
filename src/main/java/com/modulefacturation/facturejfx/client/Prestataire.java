package com.modulefacturation.facturejfx.client;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Prestataire {

    private String firstName;
    private String lastName;
    private String adress;
    private String mail;
    private String motDePasseMail;
    private String tel;
    private String siret;
    private String web;




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

        JSONObject prestataireObject = new JSONObject();
        prestataireObject.put("prestataire", prestataireDetails);


        //Add employees to list
        JSONArray factureListInfo = new JSONArray();
        factureListInfo.add(prestataireDetails);
        //employeeList.add(employeeObject2);


        //Write JSON file
        try (FileWriter file = new FileWriter("prestataire.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(prestataireObject.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public Prestataire() {
    }




    public Prestataire(String firstName, String lastName, String adress, String mail, String tel, String siret, String web) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.mail = mail;
        this.tel = tel;
        this.siret = siret;
        this.web = web;
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
}
