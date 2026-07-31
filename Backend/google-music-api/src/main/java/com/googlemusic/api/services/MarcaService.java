package com.googlemusic.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlemusic.api.repositories.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    // Criar / Salvar uma nova marca
    public Marca salvar(Marca marca) {
        return repository.save(marca);
    }

    // Também pode ser chamado de criar
    public Marca criar(Marca marca) {
        return repository.save(marca);
    }

    // Listar todas as marcas
    public List<Marca> listarTodas() {
        return repository.findAll();
    }

    // Também pode ser chamado de listarTodos
    public List<Marca> listarTodos() {
        return repository.findAll();
    }

    // Buscar marca por ID
    public Optional<Marca> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Buscar marca por ID e lançar erro caso não exista
    public Marca buscarPorIdObrigatorio(Long id) {
        Optional<Marca> marca = repository.findById(id);

        if (marca.isPresent()) {
            return marca.get();
        }

        throw new RuntimeException(
            "Marca não encontrada com o ID: " + id
        );
    }

    // Atualizar / Editar uma marca existente
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

    // Deletar / Excluir uma marca por ID
    public void deletar(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                "Marca não encontrada com o ID: " + id
            );
        }

        repository.deleteById(id);
    }

    // Verificar se uma marca existe
    public boolean existePorId(Long id) {
        return repository.existsById(id);
    }
}