package com.modulefacturation.facturejfx.client;


public class Prestation {

    public String presta;
    public int quantité;
    public int tarif;
    public int total = quantité * tarif;


    public Prestation() {
    }

    public Prestation(String presta, int quantité, int tarif) {
        this.presta = presta;
        this.quantité = quantité;
        this.tarif = tarif;
        System.out.println("ceci est le nom de la presta cree : " +presta);
    }

    public String getPresta() {
        return presta;
    }

    public int getQuantité() {
        return quantité;
    }

    public int getTarif() {
        return tarif;
    }

    public int getTotal() {
        return total;
    }

    //Constructor Setter
    public void setPresta(String presta) {
        this.presta = presta;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }




}
