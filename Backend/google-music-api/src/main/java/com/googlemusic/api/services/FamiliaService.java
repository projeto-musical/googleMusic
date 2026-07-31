package com.googlemusic.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlemusic.api.entities.Familia;
import com.googlemusic.api.repositories.FamiliaRepository;

@Service
public class FamiliaService {

	@Autowired
	private FamiliaRepository repository;

	// Listar todas as famílias
	public List<Familia> listarTodas() {
		return repository.findAll();
	}

	// Buscar família por ID
	public Optional<Familia> buscarPorId(Long id) {
		return repository.findById(id);
	}

	// Salvar uma nova família
	public Familia salvar(Familia familia) {
		return repository.save(familia);
	}

	// Atualizar / Editar uma família existente
	public Familia atualizar(Long id, Familia familiaAtualizada) {
		Optional<Familia> existente = repository.findById(id);

		if (existente.isPresent()) {
			Familia familia = existente.get();
			familia.setNome(familiaAtualizada.getNome());
			return repository.save(familia);
		}

		throw new RuntimeException("Família não encontrada para atualização com o ID: " + id);
	}

	// Deletar / Excluir por ID
	public void deletar(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Família não encontrada com o ID: " + id);
		}
		repository.deleteById(id);
	}
}
