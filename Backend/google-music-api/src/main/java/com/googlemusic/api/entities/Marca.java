package com.googlemusic.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_marca")
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	
	@Column(name = "nome_marca", nullable = false, length = 120) private String nomeMarca;
	
	@Column(name = "descricao_marca", length = 200) private String descricaoMarca;
	
	public Marca() {}
	
	public Marca(String nomeMarca, String descricaoMarca) {
		
		this.nomeMarca = nomeMarca;
		this.descricaoMarca = descricaoMarca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}

	public String getDescricaoMarca() {
		return descricaoMarca;
	}

	public void setDescricaoMarca(String descricaoMarca) {
		this.descricaoMarca = descricaoMarca;
	}
	
	

}




