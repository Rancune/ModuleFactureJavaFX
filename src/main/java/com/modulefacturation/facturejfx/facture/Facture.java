package com.modulefacturation.facturejfx.facture;

import com.modulefacturation.facturejfx.client.Client;
import com.modulefacturation.facturejfx.client.Presta;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Facture {

/*    //Info obligatoire
    //Date de la facture
    //Numéro de la facture
    //date de la vente ou de la presta de service
    // l'identite du vendeur ou du presta
    l'identité de l'acheteur ou du client
    Le numéro d'identification à la TVA
    La désignation et le décompte des produits et services rendus
    Le prix catalogue
    Le taux de TVA légalement applicable
    L'éventuelle réduction de prix
    La somme totale à payer hors taxe (HT) et toutes taxes comprises (TTC)
    L'adresse de facturation
    Les informations sur le paiement
    L'existence et la durée de la garantie légale de conformité de 2 ans pour certains biens
    Ajouter la mention « TVA non applicable, art. 293 B du Code général des impôts ».


    Quelles sont les mentions obligatoires sur les factures ?
    La mention « Facture »
    Votre nom et vos coordonnées (ainsi que votre nom commercial si vous en avez un)
    Votre numéro de SIREN (Précisez « SIREN en cours d’attribution » si vous ne l’avez pas encore reçu)
    Votre numéro d’immatriculation RCS ou RM s’il a lieu (ou précisez que vous en êtes dispensé)
    Votre numéro de SIRET (si vous ne l’avez pas encore reçu le courrier de l’INSEE, précisez « SIRET en cours d’attribution »)
    Le numéro de la facture
    Votre numéro de TVA intracommunautaire (si vous en avez des clients à l’étranger)
    La date d’émission de la facture
    Le nom ou la raison sociale et les coordonnées de votre client (sauf opposition de sa part s’il s’agit d’un particulier)
    La dénomination de la prestation ou des produits vendus, et le décompte détaillé (une ligne par
            produit ou prestation avec le prix unitaire HT) : nature, quantité, référence, remise ou réduction
    ainsi que l’ensemble des caractéristiques qui ont une incidence sur le prix. Pour les prestations, précisez les matériaux et la main-d’œuvre.)
    La date de livraison
    Le montant à payer
    La réduction de prix ou la remise forfaitaire (s’il y en a une)
    La mention  « TVA non applicable, article 293B du code général des impôts » si vous n'êtes pas redevable de la TVA
    La mention « Membre d'une association agréée, le règlement des honoraires par chèque et carte bancaire est accepté » (si c’est le cas)
    Le nombre d’exemplaires de la facture
    La mention de l'assurance souscrite au titre de l'activité, les coordonnées de votre assureur et de la couverture
    géographique du contrat. (Uniquement pour les auto-entreprises dont l’activité exige une assurance professionnelle
    obligatoire, comme la garantie décennale par exemple.)*/


    //ICI on va créer le nom de la facture et le path où le pdf sera enregistré

    private static String nomFacture;
    private static String numFacture;
    private static String dateFacture;
    private static String path = "C:/Factures/";
    private static String completePath = path+nomFacture;




    // ICI on crée le pdf de la facture. Buckle Up !  that's a wild ride.
    public static void generationPdf(Client client, ArrayList<Presta> liste) throws DocumentException, FileNotFoundException {

        Document doc = new Document();

        //Le numéro de la facture doit être chronologique et sans rupture ... Fuck.
        // XXXXXX = date du jour

        //récupération du numéro de facture
        FactureNumberPersister numero = new FactureNumberPersister("NumeroFacture.txt");

        //numero.serialiseFacture();

        try {
            numFacture = String.valueOf(numero.deserialiseFacture().numero);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //récupération de la date du jour
       // SimpleDateFormat format = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
        dateFacture = String.valueOf(java.time.LocalDate.now());

        //concaténation du numéro de facture et de la date du jour pour avoir le nom du fichier pdf de la facture
        nomFacture = dateFacture + numFacture;
        System.out.println("Voici le nom de la facture : "+nomFacture);



        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(nomFacture));
        doc.open();

        var bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);

        var paragraph = new Paragraph("Informations Client");

        var table = new PdfPTable(1);

        var date = new PdfDate();

        //SimpleDateFormat format = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);

        //var para = new PdfRectangle();

        paragraph.add(String.valueOf(date));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(client.getFirstName());
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(client.getLastName());
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(client.getAdress());
        paragraph.add(Chunk.NEWLINE);

/*        public static void writeJsonSimpleDemo(String filename) throws Exception {
            JSONObject sampleObject = new JSONObject();
            sampleObject.put("name", "Stackabuser");
            sampleObject.put("age", 35);

            JSONArray messages = new JSONArray();
            messages.add("Hey!");
            messages.add("What's up?!");

            sampleObject.put("messages", messages);
            Files.write(Paths.get(filename), sampleObject.toJSONString().getBytes());
        }*/


        //Paragraphe d'info légales
        var informationLegales = new Paragraph("Pagraphes d'information légales a propos de la TVA toussa.");

/*        Stream.of("Chrono Unit", "Duration").forEach(table::addCell);

        Arrays.stream(ChronoUnit.values())
                .forEach(val -> {
                    table.addCell(val.toString());
                    table.addCell(val.getDuration().toString());
                });*/


        paragraph.add(table);
        doc.add(paragraph);
        doc.add(informationLegales);
        doc.close();
        System.out.println("génération du PDF effectué");

        // c'est l'heure d'incrementer la facture
        try {
            numero.Incrementation();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
