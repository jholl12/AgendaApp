package br.com.javafx.agenda.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import br.com.javafx.agenda.model.Pessoa;
import br.com.javafx.agenda.model.Endereco;
import br.com.javafx.agenda.utils.DataUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * View para editar detalhes de uma pessoa.
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class PessoaEditViewController {

	@FXML
	private TextField nomeInput;
	@FXML
	private TextField sobrenomeInput;
	@FXML
	private TextField dataAniversarioInput;
	@FXML
	private TextField emailInput;
	@FXML
	private TextField cpfInput;
	
	@FXML
	private TextField ruaInput;
	@FXML
	private TextField bairroInput;
	@FXML
	private TextField cidadeInput;
	@FXML
	private TextField cepInput;
	@FXML
	private TextField numeroInput;
	
	private Stage dialogStage;
	private Pessoa pessoa;
	private boolean okClicked = false;
	
	/**
     * Inicializa a classe controlle. Este m�todo � chamado automaticamente
     * ap�s o arquivo fxml ter sido carregado.
     * 
     * @author Jhonata Santos
     */
    @FXML
    private void initialize() {
    
    }
    
	/**
     * Define o palco deste dialog.
     * 
     * @param dialogStage
     * @author Jhonata Santos
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setPessoa(Pessoa pessoa) {
    	this.pessoa = pessoa;
    	
    	if(pessoa.nomeProperty().get() != null) {
	    	nomeInput.setText(pessoa.getNome());
	    	sobrenomeInput.setText(pessoa.getSobrenome());
	    	dataAniversarioInput.setText(DataUtil.formatToString(pessoa.getDataAniversario())); // Converte para String
	    	emailInput.setText(pessoa.getEmail());
	    	cpfInput.setText(pessoa.getCpf()); 
	    	
	    	ruaInput.setText(pessoa.getEndereco().getRua());
	    	bairroInput.setText(pessoa.getEndereco().getBairro());
	    	cidadeInput.setText(pessoa.getEndereco().getCidade());
	    	cepInput.setText(Integer.toString(pessoa.getEndereco().getCep())); // Converte para Integer
	    	numeroInput.setText(Integer.toString(pessoa.getEndereco().getNumero())); // Converte para Integer
    	} else {
    		nomeInput.setText("");
	    	sobrenomeInput.setText("");
	    	dataAniversarioInput.setText(""); 
	    	emailInput.setText("");
	    	cpfInput.setText(""); 
	    	
	    	ruaInput.setText("");
	    	bairroInput.setText("");
	    	cidadeInput.setText("");
	    	cepInput.setText(""); 
	    	numeroInput.setText("");
    	}
    }
    
    /**
     * Retorna true se o usu�rio clicar OK, caso contr�rio false.
     * 
     * @return do tipo boolean
     * @author Jhonata Santos
     * 
     */
    public boolean isOkClicked() {
    	return okClicked;
    }
 
    /**
     * Chamado quando o usu�rio clica no bot�o ok para editar
     * 
     * @author Jhonata Santos
     */
    @FXML
    private void clickOk() {
    	if(isInputValid()){
    		pessoa.setNome(nomeInput.getText());
    		pessoa.setSobreNome(sobrenomeInput.getText());
    		pessoa.setDataAnivers�rio(DataUtil.formatToLocalDate(dataAniversarioInput.getText())); 
    		pessoa.setEmail(emailInput.getText());
    		pessoa.setCpf(cpfInput.getText());
    		
    		Endereco endereco = pessoa.getEndereco();
    		endereco.setRua(ruaInput.getText());
    		endereco.setBairro(bairroInput.getText());
    		endereco.setCidade(cidadeInput.getText());
    		endereco.setCep(Integer.parseInt(cepInput.getText()));
    		endereco.setNumero(Integer.parseInt(numeroInput.getText()));
    		
    		okClicked = true;
    		dialogStage.close();
    	}
    }
    
    /**
     * Chamado quando o usu�rio clica no bot�o cancelar
     * 
     * @author Jhonata Santos
     */
    @FXML
    public void clickCancelar() {
    	dialogStage.close();
    }
    
    /**
     * Valida a entrada do usu�rio nos campos de texto se os valores s�o validos.
     * 
     * @return true se a entrada � v�lida
     * @author Jhonata Santos
     */
    private boolean isInputValid() {
        String errorMessage = "";

        // Verifica se o nome � nulo ou de tamanho 0
        if (nomeInput.getText() == null || nomeInput.getText().length() == 0) {
            errorMessage += "Nome inv�lido!\n"; 
        }
        // Verifica se o sobrenome � nulo ou de tamanho 0
        if (sobrenomeInput.getText() == null || sobrenomeInput.getText().length() == 0) {
            errorMessage += "Sobrenome inv�lido!\n"; 
        }
        // Verifica se a data de anivers�rio � nulo ou de tamanho 0
        if (dataAniversarioInput.getText() == null || dataAniversarioInput.getText().length() == 0) {
            errorMessage += "Data de Anivers�rio inv�lido!\n";
        } else {
            if (!DataUtil.validaData(dataAniversarioInput.getText())) {
                errorMessage += "Anivers�rio inv�lido. Use o formato dd.mm.yyyy!\n";
            }
        }
        // Verifica se email � nulo ou de tamanho 0
        if (emailInput.getText() == null || emailInput.getText().length() == 0) {
            errorMessage += "Email inv�lido!\n";
        }
        // Verifica se cpf � nulo ou de tamanho 0
        if (cpfInput.getText() == null || cpfInput.getText().length() == 0) {
            errorMessage += "CPF inv�lido!\n";
        } else {
            // tenta converter o cpf em um int.
            try {
                Integer.parseInt(cpfInput.getText());
            } catch (NumberFormatException e) {
                errorMessage += "CPF inv�lido (deve ser um inteiro)!\n"; 
            }
        }
        
        // Verifica se a rua � nulo ou de tamanho 0
        if (ruaInput.getText() == null || ruaInput.getText().length() == 0) {
            errorMessage += "Rua inv�lida!\n"; 
        }
        // Verifica se o bairro � nulo ou de tamanho 0
        if (bairroInput.getText() == null || bairroInput.getText().length() == 0) {
            errorMessage += "Bairro inv�lido!\n"; 
        } 
        // Verifica se a cidade � nulo ou de tamanho 0
        if (cidadeInput.getText() == null || cidadeInput.getText().length() == 0) {
            errorMessage += "Cidade inv�lido!\n"; 
        }
        // Verifica se o cep � nulo ou de tamanho 0
        if (cepInput.getText() == null || cepInput.getText().length() == 0) {
            errorMessage += "CEP inv�lido!\n"; 
        } else {
            // tenta converter o cep em um int.
            try {
                Integer.parseInt(cepInput.getText());
            } catch (NumberFormatException e) {
                errorMessage += "CEP inv�lido (deve ser um inteiro)!\n"; 
            }
        }
        // Verifica se o numero � nulo ou de tamanho 0
        if (numeroInput.getText() == null || numeroInput.getText().length() == 0) {
            errorMessage += "Numero inv�lido!\n"; 
        } else {
            // tenta converter o numero em um int.
            try {
                Integer.parseInt(numeroInput.getText());
            } catch (NumberFormatException e) {
                errorMessage += "N�mero inv�lido (deve ser um inteiro)!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Campos Inv�lidos");
            alerta.setHeaderText("Por favor, corrija os campos inv�lidos");
            alerta.setContentText(errorMessage);
            alerta.showAndWait();

            return false;
        }
    }
}
