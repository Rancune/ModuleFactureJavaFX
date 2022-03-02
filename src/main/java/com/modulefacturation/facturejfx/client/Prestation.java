package com.modulefacturation.facturejfx.client;

import java.util.ArrayList;

public class Prestation {

    public String presta;
    public int quantité;
    public int tarif;
    public int total = quantité * tarif;
    private ArrayList<Prestation> liste = new ArrayList<Prestation>();



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


    // Ajouter la presta à la liste des presta à facturer
    public void addToListe(Prestation prestation){
        liste.add(prestation);
    }




    public void checkCreation(){
        System.out.println("Objet Prestation Créé");
    }


}
