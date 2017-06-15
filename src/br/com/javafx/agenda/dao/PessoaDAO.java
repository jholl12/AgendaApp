package br.com.javafx.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.javafx.agenda.db.ConnectionFactory;
import br.com.javafx.agenda.model.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por cuidar do acesso aos dados da classe {@link Pessoa}
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class PessoaDAO {
	
	/**
	 * Lista todos as pessoas existentes no banco de dados referente a tabela
	 * de pessoas e enderecos
	 * 
	 * @return todos os produtos do banco
	 * @author Jhonata Santos
	 */
	public ObservableList<Pessoa> listarPessoas() {
		ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
		Connection conn = new  ConnectionFactory().getConnection();
		String query = "SELECT pessoas.*, enderecos.* FROM agendaapp.pessoas " +
					   "INNER JOIN enderecos ON pessoas.id_endereco = enderecos.id";
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Pessoa pessoa = new Pessoa(
				result.getString("nome"),
				result.getString("sobrenome"),
				result.getString("dataAniversario").substring(0, 10),
				result.getString("email"),
				result.getString("cpf"),
				result.getString("rua"), 
				result.getString("bairro"), 
				result.getString("cidade"), 
				result.getInt("cep"), 
				result.getInt("numero")
				);
				
				pessoas.add(pessoa);
			}
			result.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return pessoas;
	}

	/**
	 * 
	 * 
	 * @author Jhonata Santos
	 */
	public void adicionarPessoa() {
		
	}
	
	/**
	 * 
	 * 
	 * @author Jhonata Santos
	 */
	public void editarPessoa() {
		
	}
	
	/**
	 * 
	 * 
	 * @author Jhonata Santos
	 */
	public void excluirPessoa() {
		
	}
}
