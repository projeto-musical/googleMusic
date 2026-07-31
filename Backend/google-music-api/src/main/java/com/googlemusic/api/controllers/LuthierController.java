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

import com.googlemusic.api.entities.Luthier;
import com.googlemusic.api.services.LuthierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/luthiers")
@CrossOrigin("*")
public class LuthierController {
    
    @Autowired
    private LuthierService service;

    @PostMapping
    public ResponseEntity<Luthier> cadastrar(@Valid @RequestBody Luthier luthier) {
        Luthier luthierSalvo = service.salvar(luthier);
        return ResponseEntity.status(HttpStatus.CREATED).body(luthierSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Luthier>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Luthier> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Luthier> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Luthier luthier) {

        try {
            Luthier atualizado = service.atualizar(id, luthier);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

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