package com.modulefacturation.facturejfx.facture;

//package com.itextpdf.samples.sandbox.events;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Footer {
    public static final String DEST = "C:/Factures/text_footer.pdf";
    public static String textFooter= " - Nom, numéro SIREN et adresse de l’auto-entrepreneur " ;
    public static String textFooter1= " - Mention de l'assurance professionnelle obligatoire pour les artisans ou les autoentrepreneurs\n" +
            "exerçant une activité artisanale pour laquelle une assurance\n" +
            "professionnelle est obligatoire comme la garantie décennale (article 22-2 de la loi n°" +
            "96-603 du 5 juillet 1996) " ;
    public static String textFooter2= " Mention de l’assurance souscrite au titre de l’activité, les coordonnées de l’assureur" +
            "ou du garant, la couverture géographique du contrat ou de la garantie." ;

/*    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new Footer().manipulatePdf(DEST);
    }*/

    public void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new TextFooterEventHandler(doc));

        for (int i = 0; i < 3; i++) {
            doc.add(new Paragraph("Test " + (i + 1)));
            if (i != 2) {
                doc.add(new AreaBreak());
            }
        }

        doc.close();
    }


    public static class TextFooterEventHandler implements IEventHandler {
        protected Document doc;

        public TextFooterEventHandler(Document doc) {
            this.doc = doc;
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
            float headerY = pageSize.getTop() - doc.getTopMargin() + 10;
            float footerY = doc.getBottomMargin();
            float footerY1 = doc.getBottomMargin()-10;
            float footerY2 = doc.getBottomMargin()-20;
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
