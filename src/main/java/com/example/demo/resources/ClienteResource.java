package com.example.demo.resources;



import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import com.example.demo.domain.Cliente;
import com.example.demo.dto.CategoriaDTO;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ClienteNewDTO;
import com.example.demo.services.ClienteService;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id)   {
			return ResponseEntity.ok().body(service.findById(id));
		
	}

	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<?> update(@Valid @RequestBody ClienteDTO dto , @PathVariable Integer id){

		Cliente cliente = service.fromDto(dto);
		cliente.setId(id);
		service.update(cliente);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> delete( @PathVariable Integer id)   {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll(){

		List<Cliente> listaClientes = service.listarTodos();
		List<ClienteDTO> listaDto = listaClientes.stream().map(obj -> ClienteDTO.fromCliente(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDto);

	}

	@RequestMapping( value = "/page" ,method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(name = "page",defaultValue = "0") Integer page , 
			@RequestParam(name = "linesPerPage",defaultValue = "2")Integer linesPerPage , 
			@RequestParam(name = "orderBy",defaultValue = "nome")String orderBy , 
			@RequestParam(name = "direction",defaultValue = "ASC")String direction
			){

		Page<Cliente> pageCliente = service.findPage(page, linesPerPage, orderBy, direction);	
		Page<ClienteDTO> pageClienteDTO = pageCliente.map(obj-> ClienteDTO.fromCliente(obj));
		return ResponseEntity.ok().body(pageClienteDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteDto){

		Cliente cliente  = service.insert(service.fromDto(clienteDto));
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}



}
