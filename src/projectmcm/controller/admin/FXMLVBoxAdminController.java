/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import projectmcm.controller.FXMLAnchorPaneFuncionarioSegurancaController;
import projectmcm.model.domain.Funcionario;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class FXMLVBoxAdminController implements Initializable {

    private Funcionario logado;
    private int id;

    @FXML
    private MenuItem menuItemAdminSeguranca;
    @FXML
    private MenuItem menuItemAdminSair;
    @FXML
    private MenuItem menuItemGerenciarAgencias;
    @FXML
    private MenuItem menuItemGerenciarGerentes;
    @FXML
    private MenuItem menuItemGerenciarSituacoes;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Menu menuAdmin;
    @FXML
    private FXMLAnchorPaneFuncionarioSegurancaController funcSeguranca;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //menuAdmin.setText(logado.getNome());
    }

    @FXML
    public void handleMenuItemGerenciarAgencias() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/admin/FXMLAnchorPaneAdminAgencias.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuItemGerenciarGerentes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/admin/FXMLAnchorPaneAdminGerentes.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuItemGerenciarSituacoes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/admin/FXMLAnchorPaneAdminSituacoes.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuItemAdminSeguranca() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        AnchorPane a = (AnchorPane) loader.load(FXMLAnchorPaneFuncionarioSegurancaController.class.getResource("/projectmcm/view/FXMLAnchorPaneFuncionarioSeguranca.fxml"));
        
        FXMLAnchorPaneFuncionarioSegurancaController funcSeguranca = loader.<FXMLAnchorPaneFuncionarioSegurancaController>getController();
        //funcSeguranca.setFuncionarioLogado(logado);
        anchorPane.getChildren().setAll(a);
    }

    public Funcionario getLogado() {
        return logado;
    }

    public void setLogado(Funcionario logado) {
        this.logado = logado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
