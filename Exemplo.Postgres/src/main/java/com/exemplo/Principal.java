package com.exemplo;


public class Principal {
public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		
		//Inserir um elemento na tabela
		Funcionario funcionario = new Funcionario(01, "Luis", "0804",'M', "Programador");
		if(dao.inserirFuncionario(funcionario) == true) {
			System.out.println("Inserido com sucesso " + funcionario.toString());
		}
		
		//Mostrar funcionarios do sexo masculino		
				System.out.println("==== Mostrar usuÃ¡rios do sexo masculino === ");
				Funcionario[] funcionarios = dao.getFuncionariosMasculinos();
				for(int i = 0; i < funcionarios.length; i++) {
					System.out.println(funcionarios[i].toString());
				}

		//Atualizar funcionario
		funcionario.setSenha("nova senha");
		dao.atualizarFuncionario(funcionario);

		//Mostrar funcionarios do sexo masculino
				System.out.println("Mostrar Funcionarios");
				funcionarios = dao.getFuncionarios();
				for(int i = 0; i < funcionarios.length; i++) {
					System.out.println(funcionarios[i].toString());
				}
		
		//Excluir funcionario
		dao.excluirFuncionario(funcionario.getid());
		
		//Mostrar funcionarios
		funcionarios = dao.getFuncionarios();
		System.out.println("Funcionarios:");		
		for(int i = 0; i < funcionarios.length; i++) {
			System.out.println(funcionarios[i].toString());
		}
		
		dao.close();
	}
}