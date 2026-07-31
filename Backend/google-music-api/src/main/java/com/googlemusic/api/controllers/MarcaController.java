package com.googlemusic.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googlemusic.api.entities.Marca;
import com.googlemusic.api.services.MarcaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/marcas")
@CrossOrigin("*")
public class MarcaController {

	@Autowired
	private MarcaService service;

	// Cadastrar uma nova marca
	@PostMapping
	public ResponseEntity<Marca> cadastrar(@Valid @RequestBody Marca marca) {
		Marca marcaSalva = service.salvar(marca);
		return ResponseEntity.status(HttpStatus.CREATED).body(marcaSalva);
	}

	// Listar todas as marcas
	@GetMapping
	public ResponseEntity<List<Marca>> listarTodas() {
		return ResponseEntity.ok(service.listarTodas());
	}

	// Buscar marca por ID
	@GetMapping("/{id}")
	public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Atualizar uma marca existente
	@PutMapping("/{id}")
	public ResponseEntity<Marca> atualizar(
			@PathVariable Long id,
			@Valid @RequestBody Marca marca) {

		try {
			Marca atualizada = service.atualizar(id, marca);
			return ResponseEntity.ok(atualizada);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Deletar uma marca
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		try {
			service.deletar(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
