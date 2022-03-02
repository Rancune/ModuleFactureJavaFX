package com.modulefacturation.facturejfx.client;

public class Client {
    private String firstName;
    private String lastName;
    private String adress;
    private String mail;
    private String tel;

    public void checkCreation(){
        System.out.println("Objet client créé");
        System.out.println(this.getFirstName() +", "+ this.getLastName());
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
}
