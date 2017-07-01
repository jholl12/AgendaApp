package br.com.javafx.agenda.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
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

	private StringProperty nome = new SimpleStringProperty();
	private StringProperty sobrenome = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dataAniversário = new SimpleObjectProperty<LocalDate>();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty cpf = new SimpleStringProperty();
	private Endereco endereco;
		
	/**
	 * Inicializa o objeto com as informações vazias
	 * 
	 * @author Jhonata Santos
	 */
	public Pessoa() {
		this.endereco = new Endereco();
	}
	
	/**
	 * Inicializa o objeto com as informações já completas de pessoa e endereço
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
	public Pessoa(String nome, String sobrenome, String data, String email, String cpf, String rua, String bairro, String cidade, int cep, int numero) {
		this.nome.set(nome);
		this.sobrenome.set(sobrenome);
		this.email.set(email);
		this.cpf.set(cpf);
		this.dataAniversário.set(LocalDate.parse(data));
		this.endereco = new Endereco(rua, bairro, cidade, cep, numero);
	}
	

	/***************************************************************************
     *                                                                         *
     * GETTERS			                                                       *
     *                                                                         *
     **************************************************************************/
	
	/**
	 * Obtém o nome da pessoa
	 * 
	 * @return um nome como String
	 * @author Jhonata Santos
	 */
	public String getNome() {
		return nome.get();
	}
	
	/**
	 * Obtém o sobrenome da pessoa
	 * 
	 * @return um sobrenome como String
	 * @author Jhonata Santos
	 */
	public String getSobrenome() {
		return sobrenome.get();
	}

	/**
	 * Obtém a data de aniversário da pessoa
	 * 
	 * @return uma data
	 * @author Jhonata Santos
	 */
	public LocalDate getDataAniversario() {
		return dataAniversário.get();
	}
	
	/**
	 * Obtém o email da pessoa
	 * 
	 * @return um email como String
	 * @author Jhonata Santos
	 */
	public String getEmail() {
		return email.get();
	}
	
	/**
	 * Obtém o cpf da pessoa
	 * 
	 * @return um cpf como int
	 * @author Jhonata Santos
	 */
	public String getCpf() {
		return cpf.get();
	}
	
	/**
	 * Obtém o objeto com o endereço da pessoa
	 *
	 * @return um objeto com todo endereço 
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
	 * Obtém o nome de uma pessoa via property
	 * 
	 * @return o nome da pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty nomeProperty() {
		return nome;
	}
	
	/**
	 * Obtém o sobrenome de uma pessoa via property
	 * 
	 * @return o sobrenome da pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty sobrenomeProperty() {
		return sobrenome;
	}

	/**
	 * Obtém a data de aniverário de uma pessoa via property
	 * 
	 * @return a data de aniversário da pessoa como ObjectProperty
	 * @author Jhonata Santos
	 */
	public ObjectProperty<LocalDate> dataAniversarioProperty() {
		return dataAniversário;
	}
	
	/**
	 * Obtém o email de uma pessoa via property
	 * 
	 * @return o email da pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty emailProperty() {
		return email;
	}
	
	/**
	 * Obtém o cpf de uma pessoa via property
	 * 
	 * @return o cpf da pessoa como StringProperty
	 * @author Jhonata Santos
	 */
	public StringProperty cpfProperty() {
		return cpf;
	}
	
	/***************************************************************************
     *                                                                         *
     * SETTERS                                                                 *
     *                                                                         *
     **************************************************************************/
	
	/**
	 * Preenche a variável nome
	 * 
	 * @param nome
	 * @author Jhonata Santos
	 */
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	/**
	 * Preenche a variável sobrenome
	 * 
	 * @param sobrenome
	 * @author Jhonata Santos
	 */
	public void setSobreNome(String sobrenome) {
		this.sobrenome.set(sobrenome);
	}

	/**
	 * Preenche a variável data de anviersário
	 * 
	 * @param string
	 * @author Jhonata Santos
	 */
	public void setDataAniversário(LocalDate dataAniversario) {
		this.dataAniversário.set(dataAniversario);
	}
	
	/**
	 * Preenche a variável email
	 * 
	 * @param email
	 * @author Jhonata Santos
	 */
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	/**
	 * Preenche a variável cpf
	 * 
	 * @param cpf
	 * @author Jhonata Santos
	 */
	public void setCpf(String cpf) {
		this.cpf.set(cpf);
	}
}
