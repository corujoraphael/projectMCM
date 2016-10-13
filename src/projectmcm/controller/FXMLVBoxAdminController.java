/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class FXMLVBoxAdminController implements Initializable {

    @FXML
    private MenuItem menuItemAdminSeguranca;
    @FXML
    private MenuItem menuItemAdminSair;
    @FXML
    private MenuItem menuItemGerenciarAgencias;
    @FXML
    private MenuItem menuItemGerenciarGerentes;     
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleMenuItemGerenciarAgencias() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/FXMLAnchorPaneAdminAgencias.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGerenciarGerentes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/FXMLAnchorPaneAdminGerentes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void teste(){
        System.out.println("teste");
    }
    
    
}