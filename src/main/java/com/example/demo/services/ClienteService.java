package com.example.demo.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cliente;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.services.exception.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente findById(Integer id) {

		Optional<Cliente>		obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException("Id nao encontrado:"+id));
	}

	public Cliente update(Cliente cliente) {
		Cliente newCliente = repo.findById(cliente.getId()).get();
		updateData(newCliente, cliente);
		return repo.save(newCliente);
		
	}
	
	public void excluir(Integer id) {
		repo.deleteById(id);
	}
	
	public List<Cliente> listarTodos(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page , Integer linesPerPage , String orderBy , String direction){
		return repo.findAll(PageRequest
				.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
	}
	
	public Cliente fromDto(ClienteDTO dto) {
		Cliente cliente = new Cliente(dto.getNome() , dto.getEmail() , null, null);
		return cliente;
	}
	
	private void updateData(Cliente newObj, Cliente oldObj) {
		newObj.setNome(oldObj.getNome());
		newObj.setEmail(oldObj.getEmail());
	}

}
