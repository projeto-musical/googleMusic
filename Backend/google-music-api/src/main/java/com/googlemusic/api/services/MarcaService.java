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

    public Marca criar(Marca marca) {
        return repository.save(marca);
    }


    public List<Marca> listarTodos() {
        return repository.findAll();
    }

 
    public Marca buscarPorId(Long id) {
        Optional<Marca> marca = repository.findById(id);

        if (marca.isPresent()) {
            return marca.get();
        }

        throw new RuntimeException("Marca não encontrada com o ID: " + id);
    }

    public Marca atualizar(Long id, Marca marcaAtualizada) {
        Optional<Marca> existente = repository.findById(id);

        if (existente.isPresent()) {
            Marca marca = existente.get();

            marca.setNomeMarca(marcaAtualizada.getNomeMarca());
            marca.setDescricaoMarca(marcaAtualizada.getDescricaoMarca());

            return repository.save(marca);
        }

        throw new RuntimeException("Marca não encontrada para atualização com o ID: " + id);
    }

    public void deletar(Long id) {
        Optional<Marca> existente = repository.findById(id);

        if (existente.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Marca não encontrada para exclusão com o ID: " + id);
        }
    }

    public boolean existePorId(Long id) {
        return repository.existsById(id);
    }


	public Marca salvar(Marca marca) {
		return null;
	}
}