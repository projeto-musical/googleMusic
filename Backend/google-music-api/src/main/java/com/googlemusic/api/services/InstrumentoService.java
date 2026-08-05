package com.googlemusic.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlemusic.api.entities.Instrumento;
import com.googlemusic.api.entities.Luthier;
import com.googlemusic.api.entities.Marca;
import com.googlemusic.api.repositories.InstrumentoRepository;
import com.googlemusic.api.repositories.LuthierRepository;
import com.googlemusic.api.repositories.MarcaRepository;

@Service
public class InstrumentoService {

    @Autowired
    private InstrumentoRepository repository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private LuthierRepository luthierRepository;

    public List<Instrumento> listarTodos() {
        return repository.findAll();
    }

    public Optional<Instrumento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Instrumento salvar(Instrumento instrumento) {
        // Valida e carrega Marca
        if (instrumento.getMarca() != null && instrumento.getMarca().getIdMarca() != null) {
            Marca marcaBD = marcaRepository.findById(instrumento.getMarca().getIdMarca())
                    .orElseThrow(() -> new RuntimeException("Marca não encontrada com o ID: " + instrumento.getMarca().getIdMarca()));
            instrumento.setMarca(marcaBD);
        } else {
            instrumento.setMarca(null); // Garante que não fique um objeto transiente sem ID
        }

        // Valida e carrega Luthier
        if (instrumento.getLuthier() != null && instrumento.getLuthier().getId() != null) {
            Luthier luthierBD = luthierRepository.findById(instrumento.getLuthier().getId())
                    .orElseThrow(() -> new RuntimeException("Luthier não encontrado com o ID: " + instrumento.getLuthier().getId()));
            instrumento.setLuthier(luthierBD);
        } else {
            instrumento.setLuthier(null); // Garante que não fique um objeto transiente sem ID
        }

        return repository.save(instrumento);
    }

    public Instrumento atualizar(Long id, Instrumento instrumento) {
        Instrumento instrumentoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instrumento não encontrado com o ID: " + id));

        instrumentoExistente.setAnoFabricacao(instrumento.getAnoFabricacao());
        instrumentoExistente.setDescricao(instrumento.getDescricao());
        instrumentoExistente.setNomeModelo(instrumento.getNomeModelo());
        instrumentoExistente.setFamilia(instrumento.getFamilia());

        if (instrumento.getMarca() != null && instrumento.getMarca().getIdMarca() != null) {
            Marca marcaBD = marcaRepository.findById(instrumento.getMarca().getIdMarca())
                    .orElseThrow(() -> new RuntimeException("Marca não encontrada com o ID: " + instrumento.getMarca().getIdMarca()));
            instrumentoExistente.setMarca(marcaBD);
        } else {
            instrumentoExistente.setMarca(null);
        }

        if (instrumento.getLuthier() != null && instrumento.getLuthier().getId() != null) {
            Luthier luthierBD = luthierRepository.findById(instrumento.getLuthier().getId())
                    .orElseThrow(() -> new RuntimeException("Luthier não encontrado com o ID: " + instrumento.getLuthier().getId()));
            instrumentoExistente.setLuthier(luthierBD);
        } else {
            instrumentoExistente.setLuthier(null);
        }

        return repository.save(instrumentoExistente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Instrumento não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
}