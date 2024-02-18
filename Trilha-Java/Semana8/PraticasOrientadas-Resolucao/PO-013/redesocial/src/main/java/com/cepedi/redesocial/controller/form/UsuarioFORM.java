package com.cepedi.redesocial.controller.form;

import com.cepedi.redesocial.model.Usuario;

public class UsuarioFORM {
	
	private String nome;
	private String email;
	private String senha;
	

	public UsuarioFORM(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setName(nome);
		usuario.setEmail(email);
		usuario.setPassword(senha);
		return usuario;
	}
	
}
