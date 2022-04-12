package com.modulefacturation.facturejfx.facture;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.properties.TextAlignment;
import com.modulefacturation.facturejfx.client.Prestataire;

import java.io.IOException;

public class Footer {
    //public static final String DEST = "C:/Factures/text_footer.pdf";
    public static String textFooter = " init ";
    public static String textFooter1 = " Mention de l’assurance souscrite au titre de l’activité, les coordonnées de l’assureur ou du garant, la couverture géographique du contrat ou de la garantie. ";
    public static String textFooter2 = " ";

    public static Prestataire prestataire;


    public static String getTextFooter() {
        return textFooter;
    }

    public static void setTextFooter(String textFooter) {
        Footer.textFooter = textFooter;
    }

    public static String getTextFooter1(String nom,String siret, String adress) {
        textFooter = " "+prestataire.getFirstName()+" "+prestataire.getLastName() + ", numero de siret : " +prestataire.getSiret()+ ", "+prestataire.getAdress();
        return textFooter1;
    }

    public static void setTextFooter1(String textFooter1) {
        Footer.textFooter1 = textFooter1;
    }

    public static String getTextFooter2() {
        return textFooter2;
    }

    public static void setTextFooter2(String textFooter2) {
        Footer.textFooter2 = textFooter2;
    }

    public static class TextFooterEventHandler implements IEventHandler {
        protected Document doc;

        public TextFooterEventHandler(Document doc, String firstname, String lastName, String siret, String adress) {
            this.doc = doc;
            textFooter = " "+firstname+" "+lastName + ", numero de siret : " +siret+ ", "+adress;
        }

        @Override
        public void handleEvent(Event currentEvent) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) currentEvent;
            Rectangle pageSize = docEvent.getPage().getPageSize();
            PdfFont font = null;
            try {
                font = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);
            } catch (IOException e) {

                // Such an exception isn't expected to occur,
                // because helvetica is one of standard fonts
                System.err.println(e.getMessage());
            }


            float coordX = ((pageSize.getLeft() + doc.getLeftMargin()) + (pageSize.getRight() - doc.getRightMargin())) / 2;
            // float headerY = pageSize.getTop() - doc.getTopMargin() + 10;
            float footerY = doc.getBottomMargin();
            float footerY1 = doc.getBottomMargin() - 10;
            float footerY2 = doc.getBottomMargin() - 20;
            Canvas canvas = new Canvas(docEvent.getPage(), pageSize);
            canvas

                    // If the exception has been thrown, the font variable is not initialized.
                    // Therefore null will be set and iText will use the default font - Helvetica
                    .setFont(font)
                    .setFontSize(7)
                    .showTextAligned(textFooter, coordX, footerY, TextAlignment.CENTER)
                    .showTextAligned(textFooter1, coordX, footerY1, TextAlignment.CENTER)
                    .showTextAligned(textFooter2, coordX, footerY2, TextAlignment.CENTER)
                    .close();
        }
    }
}
