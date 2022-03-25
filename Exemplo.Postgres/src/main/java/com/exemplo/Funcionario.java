package com.exemplo;

public class Funcionario {

		private int id;
		private String login;
		private String senha;
		private char sexo;
	    private String cargo;
		
		public Funcionario() {
			this.id = -1;
			this.login = "";
			this.senha = "";
			this.sexo = '*';	
	        this.cargo = "";
		}
		
		public Funcionario(int id, String login, String senha, char sexo, String cargo) {
			this.id = id;
			this.login = login;
			this.senha = senha;
			this.sexo = sexo;
	        this.cargo = cargo;
		}

		public int getid() {
			return id;
		}

		public void setid(int id) {
			this.id = id;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public char getSexo() {
			return sexo;
		}

		public void setSexo(char sexo) {
			this.sexo = sexo;
		}

	    public void setCargo(String cargo) {
			this.cargo = cargo;
		}

		public String getCargo() {
			return cargo;
		}

		@Override
		public String toString() {
			return "Funcionario [id=" + id + ", login=" + login + ", senha=" + senha + ", sexo=" + sexo + ", cargo=" + cargo + "]";
		}	
	}

