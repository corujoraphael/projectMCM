package projectmcm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projectmcm.controller.admin.FXMLVBoxAdminController;
import projectmcm.controller.gerente.FXMLVBoxGerenteController;
import projectmcm.model.dao.FuncionarioDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Funcionario;

public class FXMLLoginPrincipalController implements Initializable {

    @FXML
    private Button loginButtonEntrar;
    @FXML
    private TextField loginTextFieldEmail;
    @FXML
    private PasswordField loginPassFieldSenha;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Funcionario funcionario = new Funcionario();
    private Stage dialogStage;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        funcionarioDAO.setConnection(connection);
    }

    public boolean handleLoginButtonEntrar() throws IOException {
        funcionarioDAO.setConnection(connection);
        funcionario.setEmail(loginTextFieldEmail.getText());
        funcionario.setSenha(loginPassFieldSenha.getText());
        setAux(funcionarioDAO.logar(funcionario));
        if (getAux() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Senha ou email inválidos");
            alert.showAndWait();
        } else {
            Parent root;
            Scene scene =null;
            if (funcionario.getTipo() == 1){
                    FXMLLoader loader = new FXMLLoader();
                    VBox page = loader.load(FXMLVBoxAdminController.class.getResource("/projectmcm/view/admin/FXMLVBoxAdmin.fxml"));
                    scene = new Scene(page);
                    FXMLVBoxAdminController controller = loader.getController();
                    //controller.setLogado(this.funcionario);
                    stage.close();
            }else if (funcionario.getTipo() == 2){
                    FXMLLoader loader = new FXMLLoader();
                    VBox page = loader.load(FXMLVBoxGerenteController.class.getResource("/projectmcm/view/gerente/FXMLVBoxGerente.fxml"));
                    scene = new Scene(page);
                    FXMLVBoxGerenteController controller = loader.getController();
                    //controller.setLogado(this.funcionario);
                    stage.close();
            }        
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Aluguel de carros - Project MCM");
            stage.setResizable(false);
            stage.show();
            return true;
        }
        return false;

    }

    public Funcionario getAux() {
        return funcionario;
    }

    public void setAux(Funcionario aux) {
        this.funcionario = aux;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
