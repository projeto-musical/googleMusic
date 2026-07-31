package com.googlemusic.api.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Familia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFamilia;

	private String nome;
	
	@OneToMany(mappedBy = "familia")
	@JsonIgnore 
	private List<Instrumento> instrumentos = new ArrayList<>();

	public Familia() {
	}

	public Familia(String nome) {
		this.nome = nome;
	}

	// Getters e Setters
	public Long getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(Long idFamilia) {
		this.idFamilia = idFamilia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
