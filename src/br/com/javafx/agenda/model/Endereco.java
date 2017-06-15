package br.com.javafx.agenda.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe modelo para um endereço de uma pessoa
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class Endereco {

	private StringProperty rua;
	private StringProperty bairro;
	private StringProperty cidade;
	private IntegerProperty cep;
	private IntegerProperty numero;
		
	/**
	 * Inicializa o onbjeto com as informações vazias
	 * 
	 * @author Jhonata Santos
	 */
	public Endereco() {
		this(null, null, null,0, 0);
	}
	
	/**
	 * Inicializa o objeto com as informações já completas
	 * 
	 * @param rua
	 * @param bairro
	 * @param cidade
	 * @param cep
	 * @param numero
	 * @author Jhonata Santos
	 */
	public Endereco(String rua, String bairro, String cidade, int cep, int numero) {
		this.rua = new SimpleStringProperty(rua);
		this.bairro = new SimpleStringProperty(bairro);
		this.cidade = new SimpleStringProperty(cidade);
		this.cep = new SimpleIntegerProperty(cep);
		this.numero = new SimpleIntegerProperty(numero);
	}
	
	/***************************************************************************
     *                                                                         *
     * GETTERS                                                                 *
     *                                                                         *
     **************************************************************************/
	
	/**
	 * Obtém a rua de uma pessoa
	 * 
	 * @return a rua como String
	 * @author Jhonata Santos
	 */
	public String getRua() {
		return rua.get();
	}
	
	/**
	 * Obtém o bairro de uma pessoa
	 * 
	 * @return um bairro como String
	 * @author Jhonata Santos
	 */
	public String getBairro() {
		return bairro.get();
	}
	
	/**
	 * Obtém a cidade de uma pessoa
	 * 
	 * @return uma cidade como String
	 * @author Jhonata Santos
	 */
	public String getCidade() {
		return cidade.get();
	}
	
	/**
	 * Obtém o cep de uma pessoa
	 * 
	 * @return um cep como int
	 * @author Jhonata Santos
	 */
	public int getCep() {
		return cep.get();
	}
	
	/**
	 * Obtém o número da residência da pessoa
	 * 
	 * @return um número como int
	 * @author Jhonata Santos
	 */
	public int getNumero() {
		return numero.get();
	}
	
	/***************************************************************************
     *                                                                         *
     * PROPERTYS                                                               *
     *                                                                         *
     **************************************************************************/
	
	/**
	 * Obtém a rua de uma pessoa via property
	 * 
	 * @return a rua da pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty ruaProperty() {
		return rua;	
	}
	
	/**
	 * Obtém o bairro de uma pessoa via property
	 * 
	 * @return o bairro de uma pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty bairroProperty() {
		return bairro;
	}
	
	/**
	 * Obtém a cidade de uma pessoa via property
	 * 
	 * @return a cidade de uma pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty cidadeProperty() {
		return cidade;
	}
	
	/**
	 * Obtém o cep de uma pessoa vi property
	 * 
	 * @return o cep de uma pessoa como IntegerProperty
	 * @author Jhonata Santos
	 */	
	public IntegerProperty cepProperty() {
		return cep;
	}
	
	/**
	 * Obtém o número da residência de uma pessoa como property
	 * 
	 * @return o número da pessoa como IntegerProperty
	 * @author Jhonata Santos
	 */
	public IntegerProperty numeroProperty() {
		return numero;
	}
	
	/***************************************************************************
     *                                                                         *
     * SETTERS                                                         		   *
     *                                                                         *
     **************************************************************************/

	/**
	 * Preenche a variável rua
	 * 
	 * @param rua
	 * @author Jhonata Santos
	 */
	public void setRua(String rua) {
		this.rua.set(rua);
	}
	
	/**
	 * Preenche a variável bairro
	 * 
	 * @param bairro
	 * @author Jhonata Santos
	 */
	public void setBairro(String bairro) {
		this.bairro.set(bairro);
	}
	
	/**
	 * Preenche a variável cidade
	 * 
	 * @param cidade
	 * @author Jhonata Santos
	 */
	public void setCidade(String cidade) {
		this.cidade.set(cidade);
	}
	
	/**
	 * Preenche a variável cep
	 * 
	 * @param cep
	 * @author Jhonata Santos
	 */
	public void setCep(Integer cep) {
		this.cep.set(cep);
	}
	
	/**
	 * Preenche a variável número
	 * 
	 * @param numero
	 * @author Jhonata Santos
	 */
	public void setNumero(Integer numero) {
		this.numero.set(numero);
	}	
}
