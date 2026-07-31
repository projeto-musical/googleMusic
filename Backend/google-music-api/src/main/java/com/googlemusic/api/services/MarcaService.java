package com.googlemusic.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlemusic.api.entities.Marca;
import com.googlemusic.api.repositories.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public Marca salvar(Marca marca) {
        return repository.save(marca);
    }

    public Marca criar(Marca marca) {
        return repository.save(marca);
    }

    public List<Marca> listarTodas() {
        return repository.findAll();
    }

    public List<Marca> listarTodos() {
        return repository.findAll();
    }

    public Optional<Marca> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Marca buscarPorIdObrigatorio(Long id) {
        Optional<Marca> marca = repository.findById(id);

        if (marca.isPresent()) {
            return marca.get();
        }

        throw new RuntimeException(
            "Marca não encontrada com o ID: " + id
        );
    }

    public Marca atualizar(Long id, Marca marcaAtualizada) {

        Optional<Marca> existente = repository.findById(id);

        if (existente.isPresent()) {

            Marca marca = existente.get();

            marca.setNome(marcaAtualizada.getNome());
            marca.setPaisOrigem(marcaAtualizada.getPaisOrigem());

            return repository.save(marca);
        }

        throw new RuntimeException(
            "Marca não encontrada para atualização com o ID: " + id
        );
    }

    public void deletar(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                "Marca não encontrada com o ID: " + id
            );
        }

        repository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return repository.existsById(id);
    }
}