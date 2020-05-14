package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Categoria;
import com.example.demo.dto.CategoriaDTO;
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
	
	public Categoria update(Categoria categoria) {
		Categoria newCategoria = repo.findById(categoria.getId()).get();
		updateData(newCategoria, categoria);
		return repo.save(newCategoria);
		
	}
	
	public void excluir(Integer id) {
		repo.deleteById(id);
	}
	
	public List<Categoria> listarTodos(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page , Integer linesPerPage , String orderBy , String direction){
		return repo.findAll(PageRequest
				.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
	}
	
	public Categoria fromDto(CategoriaDTO dto) {
		return new Categoria(null, dto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria oldObj) {
		newObj.setNome(oldObj.getNome());
	}


}
