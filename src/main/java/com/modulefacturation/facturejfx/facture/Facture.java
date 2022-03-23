package com.modulefacturation.facturejfx.facture;



import com.dlsc.formsfx.model.structure.Element;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.ElementPropertyContainer;
import com.itextpdf.layout.borders.Border;
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
import java.util.function.Consumer;

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
        /*SimpleDateFormat format = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);*/
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
        Document doc = new Document(pdfDoc);// Creating a Document
        PdfFont font = PdfFontFactory.createFont(HELVETICA);//création de la font du document

        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new Footer.TextFooterEventHandler(doc));


        //création du format général du document


        //création du paragraphe du logo
        var logo = new Paragraph("Ici mon logo");
        //TODO ajouter l'import de l'image pour le logo


        //création du paragraphe Titre facture avec date
        var facture = new Paragraph("Numéro de facture");
        //TODO ajouter le numéro de facture et la date de la facture


        //création du paragraphe info du prestataire
        var paragraphInfoFactureur = new Paragraph("Emetteur de la facture :");
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
        var paragraphInfoClient = new Paragraph("Client :");
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
        piedDePage.setTextAlignment(TextAlignment.RIGHT);




/*        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfCanvas canvas = new PdfCanvas(docEvent.getPage());
        Rectangle pageSize = docEvent.getPage().getPageSize();
        canvas.beginText();
        try {
            canvas.setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE), 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        canvas.moveText((pageSize.getRight() - doc.getRightMargin() - (pageSize.getLeft() + doc.getLeftMargin())) / 2 + doc.getLeftMargin(), pageSize.getTop() - doc.getTopMargin() + 10)
                .showText("this is a header")
                .moveText(0, (pageSize.getBottom() + doc.getBottomMargin()) - (pageSize.getTop() + doc.getTopMargin()) - 20)
                .showText("this is a footer")
                .endText()
                .release();*/



      //Création de la ligne séparatrice

    class CustomDottedLine extends DottedLine {
        private Rectangle pageSize;

        public CustomDottedLine(Rectangle pageSize) {
            this.pageSize = pageSize;
        }

        @Override
        public void draw(PdfCanvas canvas, Rectangle drawArea) {
            // Dotted line from the left edge of the page to the right edge.
            super.draw(canvas, new Rectangle(pageSize.getLeft(), drawArea.getBottom(), pageSize.getWidth(), drawArea.getHeight()));
        }
    }
    /*
        // Creating a PdfCanvas object
        PdfCanvas canvas = new PdfCanvas(pdfPage);
        // Initial point of the line
        canvas.moveTo(100, 300);
        // Drawing the line
        canvas.lineTo(500, 300);
        canvas.closePathStroke();*/



        //Création de tableau Infos
        // Creating a table object
        float [] dimensionsInfos = {500F, 500F};
        Table tableInfos = new Table(dimensionsInfos);
        tableInfos.setAutoLayout();
        tableInfos.setBorder(Border.NO_BORDER);
        // Adding cells to the table
        tableInfos.addCell(new Cell().add(logo));
        tableInfos.addCell(new Cell().add(facture));
        tableInfos.addCell(new Cell().add(paragraphInfoFactureur));
        tableInfos.addCell(new Cell().add(paragraphInfoClient));


        //Création du tableau des prestations
        float [] dimensionsPresta = {500F, 100F, 100F};
        Table tablePresta = new Table(dimensionsPresta);

        //liste.stream().forEach((Consumer<? super Prestation>) tablePresta.addCell(new Cell().add()));






        //J'ajoute tous les éléments au doc
        doc.add(tableInfos);

        doc.add(new LineSeparator(new CustomDottedLine(pdfDoc.getDefaultPageSize())));


        doc.add(tablePresta);
        doc.add(new LineSeparator(new CustomDottedLine(pdfDoc.getDefaultPageSize())));


        doc.add(prix);


        doc.add(informationLegales);

        // Ajout du numéro de page
/*        for (int i = 1; i <= doc.getPdfDocument().getNumberOfPages(); i++) {
            Rectangle pageSize = doc.getPdfDocument().getPage(i).getPageSize();
            float x = pageSize.getWidth() / 2;
            float y = pageSize.getBottom() - 30;
            doc.showTextAligned(piedDePage, x, y, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);
        }

        int numberOfPages = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {

            // Write aligned text to the specified by parameters point
            doc.showTextAligned(new Paragraph(String.format("page %s de %s", i, numberOfPages)),559, 807, i, TextAlignment.RIGHT, VerticalAlignment.TOP, 0);
        }*/

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
