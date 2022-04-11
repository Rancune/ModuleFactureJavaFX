package com.modulefacturation.facturejfx.client;

import com.modulefacturation.facturejfx.facture.TableauPrestation;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.layout.properties.TextAlignment.CENTER;

public class Prestation {

    public String presta;
    public int quantité;

    public Prestation() {
    }

    public int tarif;
    public int total = quantité * tarif;
    //public List<Prestation> liste = new ArrayList<>();
    public TableauPrestation tableauPrestation;

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
      //  liste.add(prestation);
        System.out.println();
    }




    public void checkCreation(){

        System.out.println("C est cree");


       // liste.stream().forEach(s -> System.out.println("voici la liste des presta : "+ s.getPresta()));



        /*for (Prestation listeP: liste) {

            System.out.println("liste des prestas : "+listeP.presta);


        }*/
    }



}
