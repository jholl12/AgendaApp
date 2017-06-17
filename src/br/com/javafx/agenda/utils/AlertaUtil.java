package br.com.javafx.agenda.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classe destinada a todos os método de apresentação de Pop UP com mensagens de
 * ação
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class AlertaUtil {

	/**
	 * Método responsável por apresentar o Pop UP de Warning quando for
	 * solicitada uma ação mas não estiver ninguém selecionado
	 * 
	 * @author Jhonata Santos
	 */
	public static void alertaWarningSelecao() {
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Nenhuma seleção");
		alerta.setHeaderText("Nenhuma pessoa foi selecionada");
		alerta.setContentText("Por favor selecione uma pessoa na lista.");

		alerta.showAndWait();
	}

	/**
	 * Método responsável por apresentar o Pop UP de Confirmation quando for
	 * solictada a ação de exclusão de uma pessoa
	 * 
	 * @return o tipo do botão clicado "OK" ou "CANCEL"
	 * @author Jhonata Santos
	 */
	public static ButtonType alertaConfirmationExclusao() {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Confirmar exclusão");
		alerta.setHeaderText("Você está prestes a excluir uma pessoa de sua agenda");
		alerta.setContentText("Deseja realmente excluir esta pessoa?");

		Optional<ButtonType> resultado = alerta.showAndWait();

		if (resultado.get() == ButtonType.OK) {
			return ButtonType.OK;
		} else {
			return ButtonType.CANCEL;
		}
	}
}
