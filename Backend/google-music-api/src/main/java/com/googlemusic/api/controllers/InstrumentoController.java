package com.googlemusic.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googlemusic.api.entities.Instrumento;
import com.googlemusic.api.services.InstrumentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/instrumentos")
public class InstrumentoController {
  
	@Autowired
	private InstrumentoService service;

	@GetMapping
	public ResponseEntity<List<Instrumento>> listarTodos() {
		return ResponseEntity.ok(service.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Instrumento> buscarPorId(@PathVariable Long id) {
		Optional<Instrumento> instrumento = service.buscarPorId(id);
		return instrumento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Instrumento> salvar(@RequestBody @Valid Instrumento instrumento) {
		Instrumento novoInstrumento = service.salvar(instrumento);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoInstrumento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Instrumento> atualizar(@PathVariable Long id, @RequestBody @Valid Instrumento instrumento) {
		if (service.buscarPorId(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Instrumento instrumentoAtualizado = service.atualizar(id, instrumento);
		return ResponseEntity.ok(instrumentoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (service.buscarPorId(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}