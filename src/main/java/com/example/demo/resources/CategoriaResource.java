package com.example.demo.resources;



import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Categoria;
import com.example.demo.dto.CategoriaDTO;
import com.example.demo.services.CategoriaService;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id)   {
		
		return ResponseEntity.ok().body(service.buscar(id));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Categoria categoria){
		categoria  = service.salvar(categoria);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Categoria categoria , @PathVariable Integer id)   {
	  categoria.setId(id);
		categoria = service.atualizar(categoria);
	   return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> delete( @PathVariable Integer id)   {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		
		List<Categoria> listaCategorias = service.listarTodos();
		List<CategoriaDTO> listaDto = listaCategorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaDto);
		
	}
	
	@RequestMapping( value = "/page" ,method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(name = "page",defaultValue = "0") Integer page , 
			@RequestParam(name = "linesPerPage",defaultValue = "2")Integer linesPerPage , 
			@RequestParam(name = "orderBy",defaultValue = "nome")String orderBy , 
			@RequestParam(name = "direction",defaultValue = "ASC")String direction
			){
		
	Page<Categoria> pageCategoria = service.findPage(page, linesPerPage, orderBy, direction);	
	Page<CategoriaDTO> pageCategoriaDTO = pageCategoria.map(obj-> new CategoriaDTO(obj));
	return ResponseEntity.ok().body(pageCategoriaDTO);
	}
	
	
	
	

}
