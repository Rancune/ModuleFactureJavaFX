package com.modulefacturation.facturejfx.facture;

import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.modulefacturation.facturejfx.client.Client;
import com.modulefacturation.facturejfx.client.Prestation;
import com.modulefacturation.facturejfx.client.Prestataire;


import com.itextpdf.text.*;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.itextpdf.text.BaseColor.LIGHT_GRAY;

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
    public static void generationPdf(Prestataire prestataire, Client client, ArrayList<Prestation> liste) throws DocumentException, FileNotFoundException {

        Document doc = new Document();

        //Le numéro de la facture doit être chronologique et sans rupture ... Fuck.


        //récupération du numéro de facture
        FactureNumberPersister numero = new FactureNumberPersister("NumeroFacture.txt");
        try {
            numFacture = String.valueOf(numero.deserialiseFacture().numero);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //récupération de la date du jour
       // SimpleDateFormat format = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
        dateFacture = String.valueOf(LocalDate.now());

        //concaténation du numéro de facture et de la date du jour pour avoir le nom du fichier pdf de la facture
        nomFacture = dateFacture + "-000" + numFacture +".pdf";
        System.out.println("Voici le nom de la facture : "+nomFacture);



        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(nomFacture));
        doc.open();

        var bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);

        var paragraphInfoClient = new Paragraph("Informations Client");
        var paragraphInfoFactureur = new Paragraph("Informations du prestataire");


        paragraphInfoFactureur.add(Chunk.NEWLINE);
        paragraphInfoFactureur.add(prestataire.getFirstName());
        paragraphInfoFactureur.add(Chunk.NEWLINE);
        paragraphInfoFactureur.add(prestataire.getLastName());
        paragraphInfoFactureur.add(Chunk.NEWLINE);
        paragraphInfoFactureur.add(prestataire.getAdress());
        paragraphInfoFactureur.add(Chunk.NEWLINE);
        paragraphInfoFactureur.add(prestataire.getMail());
        paragraphInfoFactureur.add(Chunk.NEWLINE);
        paragraphInfoFactureur.add(prestataire.getSiret());
        paragraphInfoFactureur.add(Chunk.NEWLINE);
        paragraphInfoFactureur.add(prestataire.getWeb());



        paragraphInfoClient.add(Chunk.NEWLINE);
        paragraphInfoClient.add(client.getFirstName());
        paragraphInfoClient.add(Chunk.NEWLINE);
        paragraphInfoClient.add(client.getLastName());
        paragraphInfoClient.add(Chunk.NEWLINE);
        paragraphInfoClient.add(client.getAdress());
        paragraphInfoClient.add(Chunk.NEWLINE);


        var table = new PdfPTable(1);

        var date = new PdfDate();
        var azdad = new PdfRectangle(10,10,15,15);
        var paraRect = new Paragraph();

        paraRect.add(String.valueOf(Chunk.CREATIONDATE));

        //Création de la ligne séparatrice
        LineSeparator ls = new LineSeparator();
        ls.setLineColor(LIGHT_GRAY);

        // instantiating a PdfCanvas object
        //PdfCanvas canvas = new PdfCanvas(pdfPage);

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



        paragraphInfoClient.add(table);


        //J'ajoute tous les éléments au doc
        doc.add(paragraphInfoFactureur);

        doc.add(new Chunk(ls));//Ligne séparatrice

        doc.add(paragraphInfoClient);

        doc.add(new Chunk(ls));//Ligne séparatrice
        doc.add(paraRect);

        doc.add(new Chunk(ls));//Ligne séparatrice
        doc.add(informationLegales);
        doc.addCreationDate();

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
