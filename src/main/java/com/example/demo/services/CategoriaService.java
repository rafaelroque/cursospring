package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {

		Optional<Categoria>		obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException("Id nao encontrado:"+id));
	}
	
	public Categoria salvar(Categoria categoria) {
		return repo.save(categoria);
	}
	
	public Categoria atualizar(Categoria categoria) {
		return repo.save(categoria);
	}

}
