package com.googlemusic.api.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_instrumento")
public class Instrumento {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

		@NotBlank
		private String nomeModelo;
		
		@NotBlank 
		private String numeroSerie;
		
		@NotBlank
		private LocalDate anoFabricacao;
		
		private String descricao;
		
		public Instrumento() {}
		public Instrumento(Long id, String nomeModelo, String numeroSerie, LocalDate anoFabricacao, String descricao  ) {
			this.id = id;
			this.nomeModelo = nomeModelo;
			this.numeroSerie = numeroSerie;
			this.anoFabricacao = anoFabricacao;
			this.descricao = descricao;
			
		}
		
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getNomeModelo() {
			return nomeModelo;
		}
		
		public void setNomeModelo(String nomeModelo) {
			this.nomeModelo = nomeModelo;
		}
		
		public String getNumeroSerie() {
			return numeroSerie;
		}
		
		public void setNumeroSerie(String numeroSerie) {
			this.numeroSerie = numeroSerie;
		}
		
		public LocalDate getAnoFabricacao() {
			return anoFabricacao;
		}
		
		public void setAnoFabricacao(LocalDate anoFabricacao) {
			this.anoFabricacao = anoFabricacao;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
		public void setDescricao(String descricao) {
			this.descricao = descricao;
			
		}
}
