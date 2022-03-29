package com.modulefacturation.facturejfx.client;

import java.util.ArrayList;

import static com.itextpdf.layout.properties.TextAlignment.CENTER;

public class Prestation {

    public String presta;
    public int quantité;
    public int tarif;
    public int total = quantité * tarif;
    private ArrayList<Prestation> liste = new ArrayList<Prestation>();

    public Prestation(String presta, int quantité, int tarif) {
        this.presta = presta;
        this.quantité = quantité;
        this.tarif = tarif;
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


    // Ajouter la presta à la liste des presta à facturer
    public void addToListe(Prestation prestation){
        liste.add(prestation);
    }




    public void checkCreation(){

        System.out.println("Objet Prestation Créé");

        liste.forEach(String nom) -> {
            System.out.println(nom);
        };

        for (Prestation listeP: liste) {

            System.out.println("liste des prestas : "+listeP.presta);


        }
    }



}
