package com.modulefacturation.facturejfx.facture;

import com.modulefacturation.facturejfx.client.Prestation;

import java.util.ArrayList;
import java.util.List;

public class TableauPrestation {

    public List<Prestation> liste = new ArrayList<Prestation>();

    public TableauPrestation() {
    }

    public List<Prestation> getListe() {
        return liste;
    }

    public void setListe(List<Prestation> liste) {
        this.liste = liste;
    }

    public void ajoutPrestationsListe(Prestation presta){
        liste.add(presta);
        liste.stream().forEach(s -> System.out.println("voici la liste des presta : "+ s.getPresta() +" "+ s.getQuantité()));
    }


}
