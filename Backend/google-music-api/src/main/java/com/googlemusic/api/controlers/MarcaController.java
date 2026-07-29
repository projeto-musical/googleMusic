package com.googlemusic.api.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googlemusic.api.entities.Marca;
import com.googlemusic.api.services.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {

	@Autowired
	private MarcaService service;
	
	@PostMapping
	public Marca  cadastrar(@RequestBody Marca marca) {
		return service.salvar(marca);
		
	}
	
	@GetMapping
	public List<Marca> listar() {
		return service.listarTodos();
}
	
	@GetMapping("/{id}")
	public Marca atualizar (@PathVariable long id, @RequestBody Marca marca) {
		return service.atualizar(id, marca);
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) {
		return service.deletar(id);
	}
}
