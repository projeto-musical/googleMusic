package com.googlemusic.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlemusic.api.entities.Instrumento;
import com.googlemusic.api.repositories.InstrumentoRepository;

@Service
public class InstrumentoService {

	@Autowired
	private InstrumentoRepository repository;

	// Listar todos os instrumentos
	public List<Instrumento> listarTodos() {
		return repository.findAll();
	}

	// Buscar por ID
	public Optional<Instrumento> buscarPorId(Long id) {
		return repository.findById(id);
	}

	// Salvar um novo instrumento
	public Instrumento salvar(Instrumento instrumento) {
		return repository.save(instrumento);
	}

	// Atualizar instrumento existente
	public Instrumento atualizar(Long id, Instrumento instrumento) {
		Instrumento instrumentoExistente = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Instrumento não encontrado com o ID: " + id));

		instrumentoExistente.setAnoFabricacao(instrumento.getAnoFabricacao());
		instrumentoExistente.setDescricao(instrumento.getDescricao());
		instrumentoExistente.setNomeModelo(instrumento.getNomeModelo());

		// Atualizando os relacionamentos
		instrumentoExistente.setFamilia(instrumento.getFamilia());
		instrumentoExistente.setMarca(instrumento.getMarca());
		instrumentoExistente.setLuthier(instrumento.getLuthier());

		return repository.save(instrumentoExistente);
	}

	// Deletar por ID
	public void deletar(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Instrumento não encontrado com o ID: " + id);
		}
		repository.deleteById(id);
	}
}