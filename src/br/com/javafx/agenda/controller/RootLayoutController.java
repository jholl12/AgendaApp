package br.com.javafx.agenda.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RootLayoutController {

	@FXML 
	public void clickAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Agenda Eletrônica");
		alert.setHeaderText("Sobre");
		alert.setContentText("Autor: Jhonata Santos \nWebsite: www.world-jhonata-santos.com.br \nVersão 1.0 \nData: Julho 2017");
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file:resources/images/about_icon.png"));
		
		alert.showAndWait();
	}
	
	/**
	 * Encerra a aplicação por completo
	 * 
	 * @author Jhonata Santos
	 */
	@FXML 
	public void clickExit() {
		System.exit(0);
	}
}
