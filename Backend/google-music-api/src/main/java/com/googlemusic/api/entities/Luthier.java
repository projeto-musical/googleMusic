package com.googlemusic.api.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Luthier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLuthier;
	
	protected String nome;

	protected String especialidade;

	protected String cidade;

	protected String email;

	protected String telefone;

	public Luthier() {

	}

	public Luthier(String nome, String especialidade, String cidade, String email, String telefone) {

		this.nome = nome;
		this.especialidade = especialidade;
		this.cidade = cidade;
		this.email = email;
		this.telefone = telefone;
	}

	public Long getIdLuthier() {
		return idLuthier;
	}

	public void setIdLuthier(Long idLuthier) {
		this.idLuthier = idLuthier;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
