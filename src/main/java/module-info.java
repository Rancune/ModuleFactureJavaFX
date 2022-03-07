module com.modulefacturation.facturejfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires itextpdf;
    requires javax.mail;
    requires java.activation;
    requires json.simple;

    opens com.modulefacturation.facturejfx to javafx.fxml;
    exports com.modulefacturation.facturejfx;
}