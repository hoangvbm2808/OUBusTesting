/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class MainAdminScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //File
    public void ActionSeeTourist() throws IOException {
        App.setRoot("ListTourAdmin");
    }
    
    //Exit
    public void ActionClose() {
        Platform.exit();
        System.exit(0);
    }
}
