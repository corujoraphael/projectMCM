package projectmcm.controller.admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projectmcm.model.dao.GerenteDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Gerente;

public class FXMLAnchorPaneAdminGerentesController implements Initializable {

    private Gerente logado;
    
    @FXML
    private TableView<Gerente> tableViewGerentes;
    @FXML
    private TableColumn<Gerente, String> tableColumnGerenteNome;
    @FXML
    private TableColumn<Gerente, String> tableColumnGerenteCpf;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonPesquisar;
    @FXML
    private TextField textFieldPesquisar;
    @FXML
    private Label labelGerenteCodigo;
    @FXML
    private Label labelGerenteNome;
    @FXML
    private Label labelGerenteEmail;    
    @FXML
    private Label labelGerenteCpf;
    @FXML
    private Label labelGerenteRg;
    @FXML
    private Label labelGerenteDataContratacao;
    @FXML
    private Label labelGerenteAgencia;
    

    private List<Gerente> listGerentes;
    private ObservableList<Gerente> observableListGerentes;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final GerenteDAO gerenteDAO = new GerenteDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gerenteDAO.setConnection(connection);
        carregarTableViewGerente();

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        if (!listGerentes.isEmpty())
            tableViewGerentes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewGerentes(newValue));

    }

    public void carregarTableViewGerente() {
        tableColumnGerenteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnGerenteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listGerentes = gerenteDAO.listar();
        if (!listGerentes.isEmpty()){
            observableListGerentes = FXCollections.observableArrayList(listGerentes);
            tableViewGerentes.setItems(observableListGerentes);
        }
    }
    
    public void selecionarItemTableViewGerentes(Gerente gerente){
        if (gerente != null) {
            labelGerenteNome.setText(gerente.getNome());
            labelGerenteEmail.setText(gerente.getEmail());
            labelGerenteCpf.setText(gerente.getCpf());
            labelGerenteRg.setText(gerente.getRg());
            labelGerenteDataContratacao.setText(gerente.getDataContratacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));            
            labelGerenteAgencia.setText(gerente.getAgencia().getNome());
        } else {
            labelGerenteNome.setText("");
            labelGerenteEmail.setText("");
            labelGerenteCpf.setText("");
            labelGerenteRg.setText("");
            labelGerenteDataContratacao.setText("");
            labelGerenteAgencia.setText("");
        }

    }
    
    @FXML
    public void handleButtonCadastrar() throws IOException {
        Gerente gerente = new Gerente();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminGerentesDialog(gerente);
        if (buttonConfirmarClicked) {
            gerenteDAO.inserir(gerente);
            carregarTableViewGerente();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Gerente gerente = null;
        if (!listGerentes.isEmpty())
            gerente = (Gerente)tableViewGerentes.getSelectionModel().getSelectedItem();
        if (gerente != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminGerentesDialog(gerente);
            if (buttonConfirmarClicked) {
                gerenteDAO.alterar(gerente);
                selecionarItemTableViewGerentes(null);
                carregarTableViewGerente();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um gerente na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Gerente gerente = null;
        if (!listGerentes.isEmpty())
            gerente = (Gerente)tableViewGerentes.getSelectionModel().getSelectedItem();
        if (gerente != null) {
            gerenteDAO.remover(gerente);
            carregarTableViewGerente();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um gerente na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonPesquisar() throws IOException {
        tableColumnGerenteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnGerenteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        if (!textFieldPesquisar.getText().equals("")){
            listGerentes = gerenteDAO.buscar(textFieldPesquisar.getText());
            if (!listGerentes.isEmpty()){
                observableListGerentes = FXCollections.observableArrayList(listGerentes);
                tableViewGerentes.setItems(observableListGerentes);
            }
        }else{
            carregarTableViewGerente();
        }
    }
    
    public boolean showFXMLAnchorPaneAdminGerentesDialog(Gerente gerente) throws IOException {   
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneAdminGerentesDialogController.class.getResource("/projectmcm/view/admin/FXMLAnchorPaneAdminGerentesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Gerente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o gerente no Controller.
        FXMLAnchorPaneAdminGerentesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        if(gerente.getIdFuncionario() == 0){
            controller.setLabelTitleAdminGerente("Cadastro de Gerente");
        }else{
            
            controller.setLabelTitleAdminGerente("Edição de Gerente");
        }
        controller.setGerente(gerente);
        

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

    public Gerente getLogado() {
        return logado;
    }

    public void setLogado(Gerente logado) {
        this.logado = logado;
    }



}