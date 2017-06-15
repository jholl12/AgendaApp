package br.com.javafx.agenda.controller;

import br.com.javafx.agenda.MainApp;
import br.com.javafx.agenda.model.Pessoa;
import br.com.javafx.agenda.utils.DataUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * View para ver os detalhes gerais de pessoas.
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class PessoaOverviewViewController {

	@FXML
	private TableView<Pessoa> pessoaTabela;
	@FXML
	private TableColumn<Pessoa, String> nomeColuna;
	@FXML
	private TableColumn<Pessoa, String> sobrenomeColuna;

	@FXML
	private Label nome;
	@FXML
	private Label sobrenome;
	@FXML
	private Label dataAniversario;
	@FXML
	private Label email;
	@FXML
	private Label cpf;
	@FXML
	private Label rua;
	@FXML
	private Label bairro;
	@FXML
	private Label cidade;
	@FXML
	private Label cep;
	@FXML
	private Label numero;

	// Refer�ncia para a aplica��o main
	private MainApp mainApp;

	/**
	 * Inicializa a classe controller. Este m�todo � chamado automaticamente
	 * ap�s o arquivo fxml ter sido carregado.
	 * 
	 * @author Jhonata Santos
	 */
	@FXML
	private void initialize() {
		// Inicializa a tabela de pessoa com duas colunas
		nomeColuna.setCellValueFactory(
				// Classe an�nima
				new Callback<CellDataFeatures<Pessoa, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Pessoa, String> cellData) {
				return cellData.getValue().nomeProperty();
			}
		});
		
		sobrenomeColuna.setCellValueFactory(
				new Callback<CellDataFeatures<Pessoa, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Pessoa, String> cellData) {
				return cellData.getValue().sobrenomeProperty();
			}
		});
		
		// Limpa os detalhes da pessoa
		mostrarDetalhesPessoa(null);
		
		// Detecta mudan�a de sele��o e mostra os detalhes da pessoa quando houver mudan�a
		pessoaTabela.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Pessoa>() {
			@Override
			public void changed(ObservableValue<? extends Pessoa> observable, Pessoa oldValue, Pessoa newValue) {
				mostrarDetalhesPessoa(newValue);
			}
		});
	}

	/**
     * � chamado pela aplica��o principal para dar uma refer�ncia de volta a si mesmo.
     * 
     * @param mainApp
     * @author Jhonata Santos
     */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
        // Adiciona os dados da observable list na tabela
		pessoaTabela.setItems(mainApp.getPessoaData());
	}
	
	// A��ES
	
	/**
	 * M�todo respons�vel por mostrar todos os detalhes de uma pessoa
	 * 
	 * @param pessoa Recebe um objeto do tipo pessoa
	 * @author Jhonata Santos
	 */
	private void mostrarDetalhesPessoa(Pessoa pessoa) {
		// Verifica se o objeto recebido � nulo
		if(pessoa != null) {
			// Preenche as labels com as informa��es do objeto pessoa
			nome.setText(pessoa.getNome());
			sobrenome.setText(pessoa.getSobrenome());
			dataAniversario.setText(DataUtil.formatToString(pessoa.getDataAniversario())); // Converte para String
			email.setText(pessoa.getEmail());
			cpf.setText(pessoa.getCpf());
			rua.setText(pessoa.getEndereco().getRua());
			bairro.setText(pessoa.getEndereco().getBairro());
			cidade.setText(pessoa.getEndereco().getCidade());
			cep.setText(Integer.toString(pessoa.getEndereco().getCep())); // Converte para String
			numero.setText(Integer.toString(pessoa.getEndereco().getNumero())); // Converte para String
		} else {
			// Se a pessoa for null, remove todos os textos
			nome.setText("");
			sobrenome.setText("");
			dataAniversario.setText("");
			email.setText("");
			cpf.setText("");
			rua.setText("");
			bairro.setText("");
			cidade.setText("");
			cep.setText("");
			numero.setText("");
		}
	}

	/**
	 * M�todo respons�vel por remover a pessoa que estiver selecionada na
	 * TableView ao clicar no bot�o de Excluir
	 * 
	 * @author Jhonata Santos
	 */
	@FXML
	private void deletarPessoa() {
		// Obtem o indice da pessoa selecionada
		int pessoaSelecionada = pessoaTabela.getSelectionModel().getSelectedIndex();
		
		// Verifica a pessoa selecionada
		if(pessoaSelecionada >= 0) {
			pessoaTabela.getItems().remove(pessoaSelecionada);
		} else {
			// Caso n�o tenha sele��o apresenta um alerta
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Nenhuma sele��o");
			alerta.setHeaderText("Nenhuma pessoa foi selecionada");
			alerta.setContentText("Por favor selecione uma pessoa na lista.");
			
			alerta.showAndWait();
		}
	}
	
	/**
	 * Chamado quando o usu�rio clica no bot�o novo. Abre uma janela para editar
	 * detalhes da nova pessoa.
	 * 
	 * @author Jhonata Santos
	 */
	@FXML
	private void novaPessoa() {
		Pessoa tempPessoa = new Pessoa();
		boolean okClicked = mainApp.mostraPessoaEditar(tempPessoa);
		
		if(okClicked) {
			mainApp.getPessoaData().add(tempPessoa);
		}
	}
	
	/**
	 * Chamado quando o usu�rio clica no bot�o editar. Abre a janela para editar
	 * detalhes da pessoa selecionada.
	 * 
	 * @author Jhonata Santos
	 */
	@FXML
	private void editarPessoa() {
	    Pessoa pessoaSelecionada = pessoaTabela.getSelectionModel().getSelectedItem();
	    if (pessoaSelecionada != null) {
	        boolean okClicked = mainApp.mostraPessoaEditar(pessoaSelecionada);
	        if (okClicked) {
	            mostrarDetalhesPessoa(pessoaSelecionada);
	        }

	    } else {
	    	// Caso n�o tenha sele��o apresenta um alerta
	    	Alert alerta = new Alert(AlertType.WARNING);
	    	alerta.setTitle("Nenhuma sele��o");
	    	alerta.setHeaderText("Nenhuma pessoa foi selecionada");
	    	alerta.setContentText("Por favor selecione uma pessoa na lista.");
	    	
	    	alerta.showAndWait();
	    }
	}
}
