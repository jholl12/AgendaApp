package br.com.javafx.agenda.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classe destinada a todos os m�todo de apresenta��o de Pop UP com mensagens de
 * a��o
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class AlertaUtil {

	/**
	 * M�todo respons�vel por apresentar o Pop UP de Warning quando for
	 * solicitada uma a��o mas n�o estiver ningu�m selecionado
	 * 
	 * @author Jhonata Santos
	 */
	public static void alertaWarningSelecao() {
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Nenhuma sele��o");
		alerta.setHeaderText("Nenhuma pessoa foi selecionada");
		alerta.setContentText("Por favor selecione uma pessoa na lista.");

		alerta.showAndWait();
	}

	/**
	 * M�todo respons�vel por apresentar o Pop UP de Confirmation quando for
	 * solictada a a��o de exclus�o de uma pessoa
	 * 
	 * @return o tipo do bot�o clicado "OK" ou "CANCEL"
	 * @author Jhonata Santos
	 */
	public static ButtonType alertaConfirmationExclusao() {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Confirmar exclus�o");
		alerta.setHeaderText("Voc� est� prestes a excluir uma pessoa de sua agenda");
		alerta.setContentText("Deseja realmente excluir esta pessoa?");

		Optional<ButtonType> resultado = alerta.showAndWait();

		if (resultado.get() == ButtonType.OK) {
			return ButtonType.OK;
		} else {
			return ButtonType.CANCEL;
		}
	}
}
