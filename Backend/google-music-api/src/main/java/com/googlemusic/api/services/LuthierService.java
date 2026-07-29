package com.googlemusic.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlemusic.api.entities.Luthier;
import com.googlemusic.api.repositories.LuthierRepository;

@Service
public class LuthierService {
	
	@Autowired
	private LuthierRepository repository;

	public List<Luthier> listarTodos() {
		return repository.findAll();
	}

	public Optional<Luthier> buscarPorId(Long id) {
		return repository.findById(id);
	}

	public Luthier salvar(Luthier luthier) {
		return repository.save(luthier);
	}

	public Luthier atualizar(Long id, Luthier luthierAtualizado) {
		Optional<Luthier> existente = repository.findById(id);

		if (existente.isPresent()) {
			Luthier luthier = existente.get();

			luthier.setNome(luthierAtualizado.getNome());
			luthier.setEspecialidade(luthierAtualizado.getEspecialidade());
			luthier.setCidade(luthierAtualizado.getCidade());
			luthier.setEmail(luthierAtualizado.getEmail());
			luthier.setTelefone(luthierAtualizado.getTelefone());

			return repository.save(luthier);
		}

		throw new RuntimeException("Luthier não encontrado para atualização com o ID: " + id);
	}

	public void deletar(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Luthier não encontrado com o ID: " + id);
		}
		repository.deleteById(id);
	}
	
}