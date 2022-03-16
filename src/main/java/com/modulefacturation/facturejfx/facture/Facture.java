package com.modulefacturation.facturejfx.facture;


import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.modulefacturation.facturejfx.client.Client;
import com.modulefacturation.facturejfx.client.Prestataire;
import com.modulefacturation.facturejfx.client.Prestation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.itextpdf.io.font.constants.StandardFonts.HELVETICA;

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



    // ICI on crée le pdf de la facture. Buckle Up !  that's a wild ride.
    public static void generationPdf(Prestataire prestataire, Client client, ArrayList<Prestation> liste) throws Exception , FileNotFoundException {



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


/*        Document document = new Document(pdfDoc);
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(nomFacture));
        doc.open();*/

       // var bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);




        //création du document PDF
        String dest = "C:/Users/33011-31-19/Documents/Dev/Git/ModuleFactureJavaFX/"+nomFacture;// Creating a PdfWriter
        PdfWriter writer = new PdfWriter(dest);

        PdfDocument pdfDoc = new PdfDocument(writer);// Creating a PdfDocument

        // Creating a new page
        PdfPage pdfPage = pdfDoc.addNewPage();
        //pdfDoc.addNewPage();// Adding a new page

        Document doc = new Document(pdfDoc);// Creating a Document


        PdfFont font = PdfFontFactory.createFont(HELVETICA);



        //création du format général du document






        //création du paragraphe du logo
        var logo = new Paragraph("Ici mon logo");


        //création du paragraphe Titre facture avec date
        var facture = new Paragraph("Numéro de facture");


        //création du paragraphe info du prestataire
        var paragraphInfoFactureur = new Paragraph("Informations du prestataire");
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add(prestataire.getFirstName());
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add(prestataire.getLastName());
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add(prestataire.getAdress());
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add(prestataire.getMail());
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add(prestataire.getSiret());
        paragraphInfoFactureur.add(new Text("\n"));
        paragraphInfoFactureur.add(prestataire.getWeb());


        //création du paragraphe info client
        var paragraphInfoClient = new Paragraph("Informations Client");
        paragraphInfoClient.add(new Text("\n"));
        paragraphInfoClient.add(client.getFirstName());
        paragraphInfoClient.add(new Text("\n"));
        paragraphInfoClient.add(client.getLastName());
        paragraphInfoClient.add(new Text("\n"));
        paragraphInfoClient.add(client.getAdress());
        paragraphInfoClient.add(new Text("\n"));


        //création du paragraphe des prestations avec quantité et prix
        var presta = new Paragraph("Prestation");



        //Paragraphe du prix total
        var prix = new Paragraph("Prix Total");


        //Paragraphe d'info légales
        var informationLegales = new Paragraph("Pagraphes d'information légales a propos de la TVA toussa.");
        var piedDePage = new Paragraph("Information de pieds de page");


        //Création de la ligne séparatrice
        // Creating a PdfCanvas object
        PdfCanvas canvas = new PdfCanvas(pdfPage);
        // Initial point of the line
        canvas.moveTo(100, 300);
        // Drawing the line
        canvas.lineTo(500, 300);
        canvas.closePathStroke();


        //J'ajoute tous les éléments au doc
        //doc.add(table);

       // doc.add(new Chunk(ls));//Ligne séparatrice

        doc.add(logo);

       // doc.add(new Chunk(ls));//Ligne séparatrice

        doc.add(facture);

        //doc.add(new Chunk(ls));//Ligne séparatrice

        doc.add(paragraphInfoFactureur);

       // doc.add(new Chunk(ls));//Ligne séparatrice
//
        doc.add(paragraphInfoClient);

       // doc.add(new Chunk(ls));//Ligne séparatrice

        doc.add(presta);

        //.add(new Chunk(ls));//Ligne séparatrice

        doc.add(informationLegales);

       // doc.add(new Chunk(ls));//Ligne séparatrice

        doc.add(prix);

       // doc.add(new Chunk(ls));//Ligne séparatrice

        doc.add(piedDePage);


       // doc.addCreationDate();

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
