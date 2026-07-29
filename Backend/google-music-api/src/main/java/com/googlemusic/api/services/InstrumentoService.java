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
		
		@Autowired
		private InstrumentoService InstrumentoService; 

		public List<Instrumento> listarTodos() {
			return repository.findAll();
		}

		public Optional <Instrumento> buscarPorId(Long id) {
			return repository.findById(id);
		}

		public Instrumento salvar(Instrumento instrumento) {
			Instrumento InstrumentoNovo = new Instrumento();
			
			InstrumentoNovo.setAnoFabricacao(InstrumentoNovo.getAnoFabricacao());
			InstrumentoNovo.setDescricao(InstrumentoNovo.getDescricao());
			InstrumentoNovo.setNomeModelo(InstrumentoNovo.getNomeModelo());
			InstrumentoNovo.setNumeroSerie(InstrumentoNovo.getNumeroSerie());
			
			InstrumentoService.salvar(InstrumentoNovo);
			return repository.save(instrumento);
}
		
		public Instrumento atualizar(Long id, Instrumento instrumento) {
		    Instrumento instrumentoExistente = repository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Instrumento não encontrado"));

		    instrumentoExistente.setAnoFabricacao(instrumento.getAnoFabricacao());
		    instrumentoExistente.setDescricao(instrumento.getDescricao());
		    instrumentoExistente.setNomeModelo(instrumento.getNomeModelo());
		    instrumentoExistente.setNumeroSerie(instrumento.getNumeroSerie());

		    return repository.save(instrumentoExistente);
		}

		public void deletar(Long id) {
			repository.deleteById(id);
		}
	}

	

