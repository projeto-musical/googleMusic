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

import com.googlemusic.api.entities.Familia;
import com.googlemusic.api.services.FamiliaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/familias")
@CrossOrigin("*")
public class FamiliaController {

	@Autowired
	private FamiliaService service;

	// Cadastrar uma nova família
	@PostMapping
	public ResponseEntity<Familia> cadastrar(@Valid @RequestBody Familia familia) {
		Familia familiaSalva = service.salvar(familia);
		return ResponseEntity.status(HttpStatus.CREATED).body(familiaSalva);
	}

	// Listar todas as famílias
	@GetMapping
	public ResponseEntity<List<Familia>> listarTodas() {
		return ResponseEntity.ok(service.listarTodas());
	}

	// Buscar família por ID
	@GetMapping("/{id}")
	public ResponseEntity<Familia> buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Atualizar uma família existente
	@PutMapping("/{id}")
	public ResponseEntity<Familia> atualizar(
			@PathVariable Long id,
			@Valid @RequestBody Familia familia) {

		try {
			Familia atualizada = service.atualizar(id, familia);
			return ResponseEntity.ok(atualizada);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Deletar uma família
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
