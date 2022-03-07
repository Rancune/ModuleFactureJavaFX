package com.modulefacturation.facturejfx;

import com.itextpdf.text.DocumentException;
import com.modulefacturation.facturejfx.client.Client;
import com.modulefacturation.facturejfx.client.Prestataire;
import com.modulefacturation.facturejfx.client.Prestation;
import com.modulefacturation.facturejfx.facture.Facture;
import com.modulefacturation.facturejfx.mail.Mail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;

import java.io.FileNotFoundException;
import java.util.ArrayList;


// IS THIS MY CONTROLLER ? FUCK YEAH IT IS !
public class FactureController {

    @FXML
    public TextFlow alertSuccess;
    @FXML
    public TextFlow alertSuccessPresta;
    @FXML
    public TextField nom;
    @FXML
    public TextField prenom;
    @FXML
    public TextField adresse;
    @FXML
    public TextField mail;
    @FXML
    public TextArea prestation;
    @FXML
    public TextField quantity;
    @FXML
    public TextField prix;
    @FXML
    private Label welcomeText;
    @FXML
    private Label titre;
    @FXML
    private TextFlow alertDanger;
    @FXML
    private TextFlow alertDangerPresta;
    @FXML
    private GridPane vueClient;
    @FXML
    private GridPane vuePrestataire;
    @FXML
    private TextField nomPresta;
    @FXML
    private TextField prenomPresta;
    @FXML
    private TextField adressePresta;
    @FXML
    private TextField mailPresta;
    @FXML
    private TextField siretPresta;
    @FXML
    private TextField webPresta;
    @FXML
    private TextField telPresta;




    private ArrayList<Prestation> listepresta = new ArrayList<Prestation>();




    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void generationFacture() {
        alertSuccess.setVisible(true);
        alertDanger.setVisible(false);

        //récupération des infos du prestataire
       Prestataire prestataire = new Prestataire();
   /*   prestataire.setFirstName();
        prestataire.setLastName();
        prestataire.setAdress();
        prestataire.setTel();
        prestataire.setMail();
        prestataire.setWeb();*/


        //récupération des infos client
        Client client = new Client();
        client.setFirstName(nom.getText());
        client.setLastName(prenom.getText());
        client.setAdress(adresse.getText());
        client.checkCreation();


        //récupération des infos presta
        Prestation prestas = new Prestation();
        prestas.setPresta(prestation.getText());
        prestas.setQuantité(Integer.parseInt(quantity.getText()));
        prestas.setTarif(Integer.parseInt(prix.getText()));
        // prestation.addToliste(prestation);
        prestas.checkCreation();


        //création du pdf avec les infos client et presta
        Facture facture = new Facture();
        try {
            facture.generationPdf(prestataire, client, listepresta);

        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }


/*
                //Je vais créer un mail et l'envoyer
                String path = null;
                String envoyeur = null;

                Destinataire destinataire = new Destinataire(client.getMail(), path, envoyeur);
                Mail email = new Mail(destinataire);
                email.envoyerMail();
                */


        //On envoie le mail
        Mail mail = new Mail();
        // mail.envoyerMail();
        // System.out.println("Email envoyé correctement");

    }

    @FXML
    public void wipeFacture(ActionEvent actionEvent) {
        alertDanger.setVisible(true);
        alertSuccess.setVisible(false);
    }

    public void retourPrestataire(ActionEvent actionEvent) {
        vueClient.setVisible(false);
        vueClient.setDisable(true);
        vuePrestataire.setDisable(false);
        vuePrestataire.setVisible(true);

    }

    public void sauvegardeInfoPrestataire(ActionEvent actionEvent) {
        Prestataire prestataire = new Prestataire(
                nomPresta.getText(),
                prenomPresta.getText(),
                adressePresta.getText(),
                mailPresta.getText(),
                siretPresta.getText(),
                webPresta.getText(),
                telPresta.getText()
                );
        System.out.println("Informations du prestataire Enregistrées");
        alertSuccessPresta.setVisible(true);
        alertDangerPresta.setVisible(false);
    }

    public void retourClient(ActionEvent actionEvent) {
        vueClient.setVisible(true);
        vueClient.setDisable(false);
        vuePrestataire.setDisable(true);
        vuePrestataire.setVisible(false);
    }
}