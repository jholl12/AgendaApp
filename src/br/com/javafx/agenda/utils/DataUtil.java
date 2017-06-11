package br.com.javafx.agenda.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Funções auxiliares para lidar com datas.
 * 
 * @author Jhonata Santos
 */
public class DataUtil {
	private static String DATE = "dd.MM.yyyy";
	
	/**
     * Retorna os dados no formato de String. 
     * 
     * @param date Data do tipo "LocalDate" a ser retornada como String
     * @return String formadada
     * @author Jhonata Santos
     */
	public static String formatToString(LocalDate date) {
		if(date == null) {
			return null;
		}
		
		DateTimeFormatter formata = DateTimeFormatter.ofPattern(DATE);
		String data = date.format(formata);
		
		return data;
	}
	
	/**
	 * Retorna os dados no formato de LocalDate.
	 * 
	 * @param date Data do tipo "String" a ser retornado como LocalDate
	 * @return LocalDate formatada
	 * @author Jhonata Santos
	 */
	public static LocalDate formatToLocalDate(String date) {
		if(date == null) {
			return null;
		}
		
		DateTimeFormatter formata = DateTimeFormatter.ofPattern(DATE);
		LocalDate data = formata.parse(date, LocalDate::from);
		return data;
	}

	/**
	 * Checa se a String recebida é uma data valida
	 * 
	 * @param date
	 * @return true se for uma data valida
	 * @author Jhonata Santos
	 */
	public static boolean validaData(String date) {
		return formatToLocalDate(date) != null;
	}
}
