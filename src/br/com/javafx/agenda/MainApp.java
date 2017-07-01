package br.com.javafx.agenda;

import java.io.IOException;
import br.com.javafx.agenda.controller.PessoaEditController;
import br.com.javafx.agenda.controller.PessoaOverviewController;
import br.com.javafx.agenda.dao.PessoaDAO;
import br.com.javafx.agenda.model.Pessoa;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *  
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private AnchorPane pessoaOverview;
	private ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();

    public MainApp() {
    	pessoas.addAll(new PessoaDAO().listarPessoas());
    }

    //  Retorna os dados como uma observable list de Pessoa. 
    public ObservableList<Pessoa> getPessoaData() {
        return pessoas;
    }
		
	/**
	 * Inicializa a aplicação 
	 * 
	 * @author Jhonata Santos
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Aplicativo de Endereços");

		// Aplica o icone na aplicação.
	    this.primaryStage.getIcons().add(new Image("file:resources/images/main_icon.png"));
		
		inicializaRootLayout();
		mostraPessoaOverview();
	}

	/**
	 * Inicializa o RootLayout (Layout base)
	 * 
	 * @author Jhonata Santos
	 */
	public void inicializaRootLayout() {
		try {
			// Carrega o root layout do arquivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Mostra a scene(cena) contendo o root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostra o PersonOverview dentro do RootLayout
	 * 
	 * @author Jhonata Santos
	 */
	public void mostraPessoaOverview() {
		try {
			// Carrega a pessoa overview do arquivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PessoaOverview.fxml"));
			pessoaOverview = (AnchorPane) loader.load();

			// Define a pessoa overview dentro do root layout
			rootLayout.setCenter(pessoaOverview);
			
			// Da ao controlador acesso ao main app
			PessoaOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Abre uma janela para editar detalhes para a pessoa especificada. Se o usuário clicar
	 * OK, as mudanças são salvas no objeto pessoa fornecido e retorna true.
	 * 
	 * @param pessoa O objeto pessoa a ser editado
	 * @return true Se o usuário clicou OK,  caso contrário false.
	 * @author Jhonata Santos
	 */
	public boolean mostraPessoaEditar(Pessoa pessoa) {
		try {
	        // Carrega o arquivo fxml e cria um novo stage para a janela popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PessoaEdit.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			// Cria o palco dialogStage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Editar Pessoa");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
			// Aplica o icone na aplicação.
	        dialogStage.getIcons().add(new Image("file:resources/images/edit_icon.png"));

	        // Define a pessoa no controller.
	        PessoaEditController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setPessoa(pessoa);

	        // Mostra a janela e espera até o usuário fechar.
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retorna o palco principal
	 * 
	 * @return
	 * @author Jhonata Santos
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
