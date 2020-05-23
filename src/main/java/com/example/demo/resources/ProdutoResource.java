package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Produto;
import com.example.demo.dto.ProdutoDTO;
import com.example.demo.resources.utils.URL;
import com.example.demo.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
//	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
//	public ResponseEntity<?> buscar(@PathVariable Integer id)   {
//		
//		return ResponseEntity.ok().body(service.buscar(id));
//	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(name = "nome",defaultValue = "") String nome ,
			@RequestParam(name = "categorias",defaultValue = "") String categorias ,
			@RequestParam(name = "page",defaultValue = "0") Integer page , 
			@RequestParam(name = "linesPerPage",defaultValue = "2")Integer linesPerPage , 
			@RequestParam(name = "orderBy",defaultValue = "nome")String orderBy , 
			@RequestParam(name = "direction",defaultValue = "ASC")String direction
			){

		List<Integer> ids =URL.decodeIntList(categorias);
		Page<Produto> pageProduto = service.search(URL.decodeParam(nome), ids, page, linesPerPage, orderBy, direction);	
		Page<ProdutoDTO> pageProdutoDTO = pageProduto.map(obj-> ProdutoDTO.fromProduto(obj));
		return ResponseEntity.ok().body(pageProdutoDTO);
	}
	

}

