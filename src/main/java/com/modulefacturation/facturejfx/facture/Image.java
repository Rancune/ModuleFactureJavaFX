package com.modulefacturation.facturejfx.facture;

import com.itextpdf.io.image.ImageData;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class Image  {
    ImageView myImageView;
    String imFile;
     final FileChooser fileChooser = new FileChooser();

    public String getImFile() {
        return imFile;
    }

    public void setImFile(String imFile) {
        this.imFile = imFile;
    }

    public Image() {
    }

    public Image(ImageData data) {
    }


    public void importLogo(ActionEvent actionEvent) {

        // create a File chooser
        FileChooser fil_chooser = new FileChooser();


        // set initial File
        fil_chooser.setInitialDirectory(new File("C:\\"));


        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        imFile = file.getAbsolutePath();
        System.out.println(imFile);

       /* EventHandler<ActionEvent> btnLoadEventListener = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();

                //Set extension filter
                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

                //Show open file dialog
                File file = fileChooser.showOpenDialog(null);


                public void handle(ActionEvent e)
                {



                    if (file != null) {
                        label.setText(file.getAbsolutePath()
                                + "  selected");
                    }
                }

                *//*try {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    myImageView.setImage(image);
                } catch (IOException ex) {
                    System.Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
                }*//*

            }
        };*/


    }


}








