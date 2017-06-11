package br.com.javafx.agenda.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe modelo para uma pessoa
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class Pessoa {

	private StringProperty nome;
	private StringProperty sobrenome;
	private ObjectProperty<LocalDate> dataAnivers�rio;
	private StringProperty email;
	private IntegerProperty cpf;
	private Endereco endereco;
		
	/**
	 * Inicializa o onbjeto com as informa��es vazias
	 * 
	 * @author Jhonata Santos
	 */
	public Pessoa() {
		this(null, null, null, 0, null, null, null, 0, 0);
	}
	
	/**
	 * Inicializa o objeto com as informa��es j� completas
	 * 
	 * @param nome
	 * @param sobrenome
	 * @param rua
	 * @param bairro
	 * @param cidade
	 * @param cep
	 * @param numero
	 * @author Jhonata Santos
	 */
	public Pessoa(String nome, String sobrenome, String email, int cpf, String rua, String bairro, String cidade, int cep, int numero) {
		this.nome = new SimpleStringProperty(nome);
		this.sobrenome = new SimpleStringProperty(sobrenome);
		this.email = new SimpleStringProperty(email);
		this.cpf = new SimpleIntegerProperty(cpf);
		this.dataAnivers�rio = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
       
		this.endereco = new Endereco(rua, bairro, cidade, cep, numero);
	}
	

	/***************************************************************************
     *                                                                         *
     * GETTERS			                                                       *
     *                                                                         *
     **************************************************************************/
	
	/**
	 * Obt�m o nome da pessoa
	 * 
	 * @return um nome como String
	 * @author Jhonata Santos
	 */
	public String getNome() {
		return nome.get();
	}
	
	/**
	 * Obt�m o sobrenome da pessoa
	 * 
	 * @return um sobrenome como String
	 * @author Jhonata Santos
	 */
	public String getSobrenome() {
		return sobrenome.get();
	}

	/**
	 * Obt�m a data de anivers�rio da pessoa
	 * 
	 * @return uma data
	 * @author Jhonata Santos
	 */
	public LocalDate getDataAniversario() {
		return dataAnivers�rio.get();
	}
	
	/**
	 * Obt�m o email da pessoa
	 * 
	 * @return um email como String
	 * @author Jhonata Santos
	 */
	public String getEmail() {
		return email.get();
	}
	
	/**
	 * Obt�m o cpf da pessoa
	 * 
	 * @return um cpf como int
	 * @author Jhonata Santos
	 */
	public int getCpf() {
		return cpf.get();
	}
	
	/**
	 * Obt�m o objeto com o endere�o da pessoa
	 *
	 * @return um objeto com todo endere�o 
	 * @author Jhonata Santos
	 */
	public Endereco getEndereco() {
		return endereco;
	}
	
	/***************************************************************************
     *                                                                         *
     * PROPERTYS                                                               *
     *                                                                         *
     **************************************************************************/
	
	/**
	 * Obt�m o nome de uma pessoa via property
	 * 
	 * @return o nome da pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty nomeProperty() {
		return nome;
	}
	
	/**
	 * Obt�m o sobrenome de uma pessoa via property
	 * 
	 * @return o sobrenome da pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty sobrenomeProperty() {
		return sobrenome;
	}

	/**
	 * Obt�m a data de aniver�rio de uma pessoa via property
	 * 
	 * @return a data de anivers�rio da pessoa como ObjectProperty
	 * @author Jhonata Santos
	 */
	public ObjectProperty<LocalDate> dataAniversarioProperty() {
		return dataAnivers�rio;
	}
	
	/**
	 * Obt�m o email de uma pessoa via property
	 * 
	 * @return o email da pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty emailProperty() {
		return email;
	}
	
	/**
	 * Obt�m o cpf de uma pessoa via property
	 * 
	 * @return o cpf da pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public IntegerProperty cpfProperty() {
		return cpf;
	}
	
	/***************************************************************************
     *                                                                         *
     * SETTERS                                                                 *
     *                                                                         *
     **************************************************************************/
	
	/**
	 * Preenche a vari�vel nome
	 * 
	 * @param nome
	 * @author Jhonata Santos
	 */
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	/**
	 * Preenche a vari�vel sobrenome
	 * 
	 * @param sobrenome
	 * @author Jhonata Santos
	 */
	public void setSobreNome(String sobrenome) {
		this.sobrenome.set(sobrenome);
	}

	/**
	 * Preenche a vari�vel data de anviers�rio
	 * 
	 * @param string
	 * @author Jhonata Santos
	 */
	public void setDataAnivers�rio(LocalDate dataAniversario) {
		this.dataAnivers�rio.set(dataAniversario);
	}
	
	/**
	 * Preenche a vari�vel email
	 * 
	 * @param email
	 * @author Jhonata Santos
	 */
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	/**
	 * Preenche a vari�vel cpf
	 * 
	 * @param cpf
	 * @author Jhonata Santos
	 */
	public void setCpf(Integer cpf) {
		this.cpf.set(cpf);
	}
}
