package br.com.javafx.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
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
	 * Lista todos as pessoas existentes no banco de dados referente a tabela de
	 * pessoas e enderecos
	 * 
	 * @return todos os produtos do banco
	 * @author Jhonata Santos
	 */
	public ObservableList<Pessoa> listarPessoas() {
		ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
		Connection conn = new ConnectionFactory().getConnection();
		String query = "SELECT pessoas.*, enderecos.* FROM pessoas "
				+ "INNER JOIN enderecos ON pessoas.id_endereco = enderecos.id";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Pessoa pessoa = new Pessoa(result.getString("nome"), result.getString("sobrenome"),
						result.getString("dataAniversario").substring(0, 10), result.getString("email"),
						result.getString("cpf"), result.getString("rua"), result.getString("bairro"),
						result.getString("cidade"), result.getInt("cep"), result.getInt("numero"));

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
	 * Realiza a inserção de uma nova pessoa no banco de dados referente a tabela de 
	 * pessoas e tabela de endereços
	 * 
	 * @author Jhonata Santos
	 */
	public void adicionarPessoa(Pessoa pessoa) {
		Connection conn = new ConnectionFactory().getConnection();
		String queryInsertPessoa = "INSERT INTO pessoas (nome, sobrenome, dataAniversario, email, cpf, id_endereco)" +
				       "VALUES (?, ?, ?, ?, ?, LAST_INSERT_ID())";
		
		String queryInsertEndereco = "INSERT INTO enderecos (rua, bairro, cidade, cep, numero)" +
			       "VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(queryInsertEndereco);
			ps.setString(1, pessoa.getEndereco().getRua());
			ps.setString(2, pessoa.getEndereco().getBairro());
			ps.setString(3, pessoa.getEndereco().getCidade());
			ps.setInt(4, pessoa.getEndereco().getCep());
			ps.setInt(5, pessoa.getEndereco().getNumero());
			
			ps.execute();
			
			ps = conn.prepareStatement(queryInsertPessoa);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getSobrenome());
			ps.setDate(3, Date.valueOf(pessoa.getDataAniversario()));
			ps.setString(4, pessoa.getEmail());
			ps.setString(5, pessoa.getCpf());
			
			ps.execute();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * 
	 * @author Jhonata Santos
	 * @param pessoa 
	 */
	public void editarPessoa(Pessoa pessoa) {
		Connection conn = new ConnectionFactory().getConnection();
		String queryUpdateEndereco = "UPDATE enderecos SET  rua = ?, bairro = ?, cidade = ?, cep = ?, numero = ? WHERE id = ?;";
		String queryUpdatePessoa = "UPDATE pessoas SET nome = ?, sobrenome = ?, dataAniversario = ?, email = ?, cpf = ? WHERE cpf = ?;";
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(queryUpdateEndereco);
			ps.setString(1, pessoa.getEndereco().getRua());
			ps.setString(2, pessoa.getEndereco().getBairro());
			ps.setString(3, pessoa.getEndereco().getCidade());
			ps.setInt(4, pessoa.getEndereco().getCep());
			ps.setInt(5, pessoa.getEndereco().getNumero());
			ps.setInt(6, getIdEndereco(pessoa.getCpf()));
			ps.execute();

			ps = conn.prepareStatement(queryUpdatePessoa);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getSobrenome());
			ps.setDate(3, Date.valueOf(pessoa.getDataAniversario()));
			ps.setString(4, pessoa.getEmail());
			ps.setString(5, pessoa.getCpf());
			ps.setString(6, pessoa.getCpf());
			ps.execute();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Realiza a exclusão de uma pessoa do banco de dados da tabela de Pessoa e
	 * sua referencia a tabela Endereco
	 * 
	 * @author Jhonata Santos
	 */
	public boolean excluirPessoa(Pessoa pessoa) {
		Connection conn = new ConnectionFactory().getConnection();
		String query = "DELETE pessoas.*, enderecos.* FROM pessoas "
				+ "INNER JOIN enderecos ON pessoas.id_endereco = enderecos.id "
				+ "WHERE pessoas.cpf = " + "'" + pessoa.getCpf() + "'";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

			ps.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Realiza uma consulta na base de dados para verificar se o CPF já está
	 * cadastrado
	 * 
	 * @param cpf
	 * @return true se já existir um CPF
	 * @author Jhonata Santos
	 */
	public boolean consultarCpf(String cpf) {
		Connection conn = new ConnectionFactory().getConnection();
		String query = "SELECT agendaapp.pessoas.cpf FROM pessoas WHERE cpf = '" + cpf + "'";
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				String value = result.getString("cpf");
				if(value.equals(cpf)){
					return false;
				}
			}
	
			result.close();
			ps.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param cpf
	 * @return
	 */
	public int getIdEndereco(String cpf) {
		Connection conn = new ConnectionFactory().getConnection();
		String query = "SELECT id_endereco FROM pessoas WHERE cpf = '" + cpf + "'";
		PreparedStatement ps;
		int idEndereco = 0;

		try {
			ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				idEndereco = Integer.parseInt(result.getString("id_endereco"));
				return idEndereco;
			}
			
			result.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return idEndereco;
	}
}
