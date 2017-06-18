package br.com.javafx.agenda.controller;

import br.com.javafx.agenda.MainApp;
import br.com.javafx.agenda.dao.PessoaDAO;
import br.com.javafx.agenda.model.Pessoa;
import br.com.javafx.agenda.utils.AlertaUtil;
import br.com.javafx.agenda.utils.DataUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
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
public class PessoaOverviewController {

	@FXML private TableView<Pessoa> tableView;
	@FXML private TableColumn<Pessoa, String> nomeColuna;
	@FXML private TableColumn<Pessoa, String> sobrenomeColuna;

	@FXML private Label nomeLabel;
	@FXML private Label sobrenomeLabel;
	@FXML private Label dataAniversarioLabel;
	@FXML private Label emailLabel;
	@FXML private Label cpfLabel;
	@FXML private Label ruaLabel;
	@FXML private Label bairroLabel;
	@FXML private Label cidadeLabel;
	@FXML private Label cepLabel;
	@FXML private Label numeroLabel;

	// Referência para a aplicação main
	private MainApp mainApp;

	/**
	 * Este método é chamado automaticamente após o arquivo fxml ter sido
	 * carregado.
	 * 
	 * @author Jhonata Santos
	 */
	@FXML
	private void initialize() {
		// Inicializa a tabela de pessoa com duas colunas
		nomeColuna.setCellValueFactory(
				// Classe anônima
				new Callback<CellDataFeatures<Pessoa, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Pessoa, String> cellData) {
						return cellData.getValue().nomeProperty();
					}
				});

		sobrenomeColuna.setCellValueFactory(new Callback<CellDataFeatures<Pessoa, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Pessoa, String> cellData) {
				return cellData.getValue().sobrenomeProperty();
			}
		});

		// Limpa os detalhes da pessoa
		mostrarDetalhesPessoa(null);

		// Detecta mudança de seleção e mostra os detalhes da pessoa quando houver mudança
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pessoa>() {
			@Override
			public void changed(ObservableValue<? extends Pessoa> observable, Pessoa oldValue, Pessoa newValue) {
				mostrarDetalhesPessoa(newValue);
			}
		});
	}

	/**
	 * É chamado pela aplicação principal para dar uma referência de volta a si
	 * mesmo.
	 * 
	 * @param mainApp
	 * @author Jhonata Santos
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Adiciona os dados da observable list na tabela
		tableView.setItems(mainApp.getPessoaData());
	}

	// AÇÕES

	/**
	 * Método responsável por mostrar todos os detalhes de uma pessoa
	 * 
	 * @param pessoa
	 *            Recebe um objeto do tipo pessoa
	 * @author Jhonata Santos
	 */
	private void mostrarDetalhesPessoa(Pessoa pessoa) {
		// Verifica se o objeto recebido é nulo
		if (pessoa != null) {
			// Preenche as labels com as informações do objeto pessoa
			nomeLabel.setText(pessoa.getNome());
			sobrenomeLabel.setText(pessoa.getSobrenome());
			dataAniversarioLabel.setText(DataUtil.formatToString(pessoa.getDataAniversario()));
			emailLabel.setText(pessoa.getEmail());
			cpfLabel.setText(pessoa.getCpf());
			ruaLabel.setText(pessoa.getEndereco().getRua());
			bairroLabel.setText(pessoa.getEndereco().getBairro());
			cidadeLabel.setText(pessoa.getEndereco().getCidade());
			cepLabel.setText(Integer.toString(pessoa.getEndereco().getCep()));
			numeroLabel.setText(Integer.toString(pessoa.getEndereco().getNumero())); 
		} else {
			// Se a pessoa for null, remove todos os textos
			nomeLabel.setText("");
			sobrenomeLabel.setText("");
			dataAniversarioLabel.setText("");
			emailLabel.setText("");
			cpfLabel.setText("");
			ruaLabel.setText("");
			bairroLabel.setText("");
			cidadeLabel.setText("");
			cepLabel.setText("");
			numeroLabel.setText("");
		}
	}

	/**
	 * Método responsável por remover a pessoa que estiver selecionada na
	 * TableView ao clicar no botão de Excluir
	 * 
	 * @author Jhonata Santos
	 */
	@FXML
	private void deletarPessoa() {
		// Obtem o indice da pessoa selecionada
		int pessoaSelecionada = tableView.getSelectionModel().getSelectedIndex();

		// Verifica a pessoa selecionada
		if (pessoaSelecionada >= 0) {
			Pessoa pessoa = tableView.getItems().get(pessoaSelecionada);
			ButtonType resultado = AlertaUtil.alertaConfirmationExclusao();

			// Verifica o botão clicado no Pop UP
			if (resultado == ButtonType.OK) {
				new PessoaDAO().excluirPessoa(pessoa);
				tableView.getItems().remove(pessoaSelecionada);
			}
		} else {
			AlertaUtil.alertaWarningSelecao();
		}
	}

	/**
	 * Chamado quando o usuário clica no botão novo. Abre uma janela para editar
	 * detalhes da nova pessoa.
	 * 
	 * @author Jhonata Santos
	 */
	@FXML
	private void novaPessoa() {
		Pessoa tempPessoa = new Pessoa();
		boolean okClicked = mainApp.mostraPessoaEditar(tempPessoa);

		if (okClicked) {
			mainApp.getPessoaData().add(tempPessoa);
		}
	}

	/**
	 * Chamado quando o usuário clica no botão editar. Abre a janela para editar
	 * detalhes da pessoa selecionada.
	 * 
	 * @author Jhonata Santos
	 */
	@FXML
	private void editarPessoa() {
		Pessoa pessoaSelecionada = tableView.getSelectionModel().getSelectedItem();
		if (pessoaSelecionada != null) {
			boolean okClicked = mainApp.mostraPessoaEditar(pessoaSelecionada);
			if (okClicked) {
				mostrarDetalhesPessoa(pessoaSelecionada);
			}

		} else {
			AlertaUtil.alertaWarningSelecao();
		}
	}
}
