package com.modulefacturation.facturejfx.facture;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FactureNumberPersister {

    private String filestore = "NumeroFacture.txt";

    public FactureNumberPersister(String filestore) {
        this.filestore = filestore;
        NumerotationFacture numerotationFacture = new NumerotationFacture();
    }

    public FactureNumberPersister() {

    }

    public NumerotationFacture FactureNumberPersister() {
        NumerotationFacture numerotationFacture = new NumerotationFacture();
        return numerotationFacture;

    }

    public void serialiseFacture(Integer numero2store) throws IOException {

        Properties p = new Properties();
        p.setProperty("numero", String.valueOf(numero2store));

        FileWriter fw = new FileWriter("NumeroFacture.txt");
        p.store(fw, "Numero de facture enregistré");
        fw.close();

    }


    public NumerotationFacture deserialiseFacture() throws IOException {

        Properties p = new Properties();
        FileReader fr = new FileReader(filestore);
        p.load(fr);
        fr.close();

        NumerotationFacture numeroAncienneFacture = new NumerotationFacture();
        numeroAncienneFacture.numero = Integer.parseInt(p.getProperty("numero"));

        return numeroAncienneFacture;
    }

    public void Incrementation() throws IOException {

        serialiseFacture(FactureNumberPersister().numero = ++(deserialiseFacture().numero));

    }
}

