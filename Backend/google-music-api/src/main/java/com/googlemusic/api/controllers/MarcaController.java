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

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin("*")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping
    public List<Marca> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorIdObrigatorio(id));
    }

    @PostMapping
    public ResponseEntity<Marca> salvar(@RequestBody Marca marca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(marca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Long id, @RequestBody Marca marca) {
        return ResponseEntity.ok(service.atualizar(id, marca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}