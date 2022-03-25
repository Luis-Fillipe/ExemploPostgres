package com.exemplo;

import java.sql.*;


public class DAO {
private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "empresa";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "080403";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conectado ao postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexao falhou o driver nao foi encontrado" + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexao falhou" + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirFuncionario(Funcionario funcionario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO funcionario (id, login, senha, sexo, cargo) "
					       + "VALUES ("+funcionario.getid()+ ", '" + funcionario.getLogin() + "', '"  
					       + funcionario.getSenha() + "', '" + funcionario.getSexo() + "', '" + funcionario.getCargo() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarFuncionario(Funcionario funcionario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE funcionario SET login = '" + funcionario.getLogin() + "', senha = '"  
				       + funcionario.getSenha() + "', sexo = '" + funcionario.getSexo() + "'" + "', cargo = '" + funcionario.getCargo() + "'"
					   + " WHERE id = " + funcionario.getid();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirFuncionario(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM funcionario WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Funcionario[] getFuncionarios() {
		Funcionario[] funcionarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM funcionario");		
	         if(rs.next()){
	             rs.last();
	             funcionarios = new Funcionario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 funcionarios[i] = new Funcionario(rs.getInt("id"), rs.getString("login"), 
	                		                  rs.getString("senha"), rs.getString("sexo").charAt(0), rs.getString("cargo"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return funcionarios;
	}
	
	public Funcionario[] getFuncionariosMasculinos() {
		Funcionario[] funcionarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM funcionario WHERE funcionario.sexo LIKE 'M'");		
	         if(rs.next()){
	             rs.last();
	             funcionarios = new Funcionario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 funcionarios[i] = new Funcionario(rs.getInt("id"), rs.getString("login"), 
                         		                  rs.getString("senha"), rs.getString("sexo").charAt(0), rs.getString("cargo"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return funcionarios;
	}

	
	
}