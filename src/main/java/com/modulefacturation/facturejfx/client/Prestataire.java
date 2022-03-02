package com.modulefacturation.facturejfx.client;

public class Prestataire {

    private String firstName;
    private String lastName;
    private String adress;
    private String mail;
    private String tel;
    private String siret;
    private String web;


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
}
