package com.googlemusic.api.entities;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

	@NotNull
	private LocalDate anoFabricacao;

	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_familia")
	private Familia familia;

	@ManyToOne
	@JoinColumn(name = "id_marca")
	private Marca marca;

	@ManyToOne
	@JoinColumn(name = "id_luthier")
	private Luthier luthier;

	public Instrumento() {
	}

	public Instrumento(Long id, String nomeModelo, String numeroSerie, LocalDate anoFabricacao, String descricao,
			Familia familia, Marca marca, Luthier luthier) {
		this.id = id;
		this.nomeModelo = nomeModelo;
		this.numeroSerie = numeroSerie;
		this.anoFabricacao = anoFabricacao;
		this.descricao = descricao;
		this.familia = familia;
		this.marca = marca;
		this.luthier = luthier;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Luthier getLuthier() {
		return luthier;
	}

	public void setLuthier(Luthier luthier) {
		this.luthier = luthier;
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
