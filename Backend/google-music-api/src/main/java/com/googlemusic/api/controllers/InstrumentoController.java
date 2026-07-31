package com.googlemusic.api.controllers;

import java.util.List;
import java.util.Optional;

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

import com.googlemusic.api.entities.Instrumento;
import com.googlemusic.api.services.InstrumentoService;

@RestController
@RequestMapping("/api/instrumentos")
@CrossOrigin("*")
public class InstrumentoController {

    @Autowired
    private InstrumentoService service;

    @GetMapping
    public List<Instrumento> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Instrumento>> buscarPorId(@PathVariable Long id) {
        // Alterado de buscarPorId para buscarPorIdObrigatorio para retornar o objeto correto
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Instrumento> salvar(@RequestBody Instrumento instrumento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(instrumento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instrumento> atualizar(@PathVariable Long id, @RequestBody Instrumento instrumento) {
        return ResponseEntity.ok(service.atualizar(id, instrumento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}