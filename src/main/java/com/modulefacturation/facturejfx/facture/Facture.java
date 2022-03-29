package com.modulefacturation.facturejfx.facture;



import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.modulefacturation.facturejfx.client.Client;
import com.modulefacturation.facturejfx.client.Prestataire;
import com.modulefacturation.facturejfx.client.Prestation;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


import static com.itextpdf.io.font.constants.StandardFonts.HELVETICA;
import static com.itextpdf.layout.properties.TextAlignment.*;

public class Facture {

/*  //Info obligatoire
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
    private static double total = 0.0;




    // ICI on crée le pdf de la facture. Buckle Up !  that's a wild ride.
    public static void generationPdf(Prestataire prestataire, Client client, ArrayList<Prestation> liste) throws Exception , FileNotFoundException {



        //Le numéro de la facture doit être chronologique et sans rupture ... Fuck.


        //********************création du format général du document******************************


        //récupération du numéro de facture
        FactureNumberPersister numero = new FactureNumberPersister("NumeroFacture.txt");
        try {
            numFacture = String.valueOf(numero.deserialiseFacture().numero);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //récupération de la date du jour
        /*SimpleDateFormat format = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);*/
        dateFacture = String.valueOf(LocalDate.now());


        //concaténation du numéro de facture et de la date du jour pour avoir le nom du fichier pdf de la facture
        nomFacture = dateFacture + "-000" + numFacture +".pdf";
        System.out.println("Voici le nom de la facture : "+nomFacture);


        //création du document PDF
        String dest = "C:/Users/33011-31-19/Documents/Dev/Git/ModuleFactureJavaFX/"+nomFacture;// Creating a PdfWriter
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);// Creating a PdfDocument


        // Creating a new page
        PdfPage pdfPage = pdfDoc.addNewPage();
        Document doc = new Document(pdfDoc);// Creating a Document
        PdfFont font = PdfFontFactory.createFont(HELVETICA);//création de la font du document








        //********************création des différents paragraphes du document******************************


        //création du paragraphe du logo
        var logo = new Paragraph("Ici mon logo");
        logo.setFont(font).setFontSize(24).setBold();
        //TODO ajouter l'import de l'image pour le logo


        //création du paragraphe Titre facture avec date
        var facture = new Paragraph("Facture N°"+numFacture);
        facture.setFont(font).setFontSize(24).setBold();
        var date  = new Paragraph(dateFacture);



        //création du paragraphe info du prestataire
        var paragraphInfoFactureur = new Paragraph("Facturé par :");
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add(prestataire.getFirstName() +" "+prestataire.getLastName());
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add(prestataire.getAdress());
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add(prestataire.getMail());
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add("Siret : " +prestataire.getSiret());
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add("Web : "+prestataire.getWeb());


        //création du paragraphe info client
        var paragraphInfoClient = new Paragraph("Facturé à :");
        paragraphInfoClient.add(new Text("\n"));
        paragraphInfoClient.add(client.getFirstName());
        paragraphInfoClient.add(new Text("\n"));
        paragraphInfoClient.add(client.getLastName());
        paragraphInfoClient.add(new Text("\n"));
        paragraphInfoClient.add(client.getAdress());
        paragraphInfoClient.add(new Text("\n"));
        paragraphInfoClient.add(client.getMail());


        //Paragraphe du prix
        var paragraphePrix = new Paragraph();
        paragraphePrix.add(new Text("Total HT :").setBold().setFontSize(20));
        paragraphePrix.add(new Text("\n"));
        paragraphePrix.add(new Text("TVA non applicable, art. 293 B du CGI").setFontSize(8));


        //Paragraphe du total
        var paragrapheTotal = new Paragraph();
        paragrapheTotal.add(total+" €").setFontSize(20).setBold();


        //Paragraphe d'info légales
        var paragraphInformationLegales = new Paragraph("Instruction de paiement").setFontSize(8);
        paragraphInformationLegales.add(new Text("\n"));
        paragraphInformationLegales.add("Date de réglement : ");
        paragraphInformationLegales.add(new Text("\n"));
        paragraphInformationLegales.add("Date d'éxécution de la vente : ");
        paragraphInformationLegales.add(new Text("\n"));
        paragraphInformationLegales.add("Ou de la prestation : ");
        paragraphInformationLegales.add(new Text("\n"));
        paragraphInformationLegales.add("Taux de pénalités à compter du : ");
        paragraphInformationLegales.add(new Text("\n"));
        paragraphInformationLegales.add("En l’absence de paiement : ");
        paragraphInformationLegales.add(new Text("\n"));
        paragraphInformationLegales.add("Conditions d’escompte : ");


        //Création du pied de page
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new Footer.TextFooterEventHandler(doc));//Alors celui la m'aura bien fait chier.







        //***********************************CREATION DES TABLES à ajouter dans le PDF*************************

        //Création de tableau Logo
        // Creating a table object
        float [] dimensionsLogo = {500F, 500F};
        Table tableLogo = new Table(dimensionsLogo);
        //tableLogo.setAutoLayout();
        //tableLogo.setBorder(null);
        tableLogo.setMarginBottom(70);
        // Adding cells to the table
        tableLogo.addCell(new Cell().add(logo).setTextAlignment(CENTER).setBorder(null));
        tableLogo.addCell(new Cell().add(facture).add(date).setTextAlignment(CENTER).setBorder(null));


        //Création du tableau des infos prestataire et client
        float [] dimensionsInfos = {500F, 500F};
        Table tableInfos = new Table(dimensionsInfos);
        //tableInfos.setAutoLayout();
        //tableInfos.setBorder(Border.NO_BORDER);
        tableInfos.setMarginBottom(50);
        // Adding cells to the table
        tableInfos.addCell(new Cell().add(paragraphInfoFactureur).setBorder(null));
        tableInfos.addCell(new Cell().add(paragraphInfoClient).setBorder(null));


        //Création du tableau des prestations
        float [] dimensionsPresta = {500F, 100F, 100F};
        Table tablePresta = new Table(dimensionsPresta);
        tablePresta.setMarginBottom(20);
        tablePresta.addCell("Prestation").setTextAlignment(CENTER);
        tablePresta.addCell("Quantité").setTextAlignment(CENTER);
        tablePresta.addCell("Prix unitaire").setTextAlignment(CENTER);
        tablePresta.addCell("Prix unitaire").setTextAlignment(CENTER);
        tablePresta.addCell("Prix unitaire").setTextAlignment(CENTER);
        //TODO listing des prestations dans le tableau depuis la liste prestas en parametre
        //TODO les prestas sont alignées à gauche le reste au centre

       // public void ajoutPrestationAuTableau(Prestation prestation){

       //}
        liste.stream().forEach(entry -> System.out.println(entry.getPresta()));
        liste.stream().forEach(entry ->tablePresta.addCell(entry.getPresta()));
        //liste.stream().forEach(System.out.println("test listing"));

        System.out.println("presta ajoutée : avant la boucle");

        for (Prestation listeP: liste) {
            tablePresta.addCell(listeP.presta).setTextAlignment(CENTER);
            System.out.println("presta ajoutée : "+listeP.presta);
            tablePresta.addCell(String.valueOf(listeP.quantité)).setTextAlignment(CENTER);
            tablePresta.addCell(String.valueOf(listeP.tarif)).setTextAlignment(CENTER);

        }

       // liste.forEach((Consumer<? super Prestation>) tablePresta.addCell("testpresta"));





        //Création du tableau du prix et des information légales
        float [] dimensionsPrix = {500F, 150f, 200f};
        Table tablePrix = new Table(dimensionsPrix);
        tablePrix.addCell(paragraphInformationLegales.setTextAlignment(LEFT).setBorder(null));
        tablePrix.addCell(paragraphePrix.setTextAlignment(RIGHT)).setBorder(null);
        tablePrix.addCell(paragrapheTotal.setTextAlignment(RIGHT).setBorder(null));






        //**********************Ajout des tables au document PDF****************************

        doc.add(tableLogo);

        doc.add(tableInfos);

        doc.add(tablePresta);

        doc.add(tablePrix);


        // Ajout du numéro de page
        int numberOfPages = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            Rectangle pageSize = doc.getPdfDocument().getPage(i).getPageSize();
            float x = pageSize.getWidth() / 2;
            float y = pageSize.getBottom() - 30;
            // Write aligned text to the specified by parameters point
            doc.showTextAligned(new Paragraph(String.format("page %s de %s", i, numberOfPages)),x, y, i, RIGHT, VerticalAlignment.BOTTOM, 0);
        }


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
