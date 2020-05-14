package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ClienteNewDTO;
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
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return repo.save(cliente);
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

	public Cliente fromDto(@Valid ClienteNewDTO clienteDto) {
		Cliente cliente = new Cliente(clienteDto.getNome() , clienteDto.getEmail() , clienteDto.getCpfCnpj() , TipoCliente.toEnum(clienteDto.getTipo()));
		Cidade cidade = new Cidade();
		cidade.setId(clienteDto.getCidadeId());
		Endereco end = new Endereco(clienteDto.getLogradouro(), clienteDto.getNumero(), clienteDto.getComplemento(), clienteDto.getBairro(), clienteDto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(end);
		
		cliente.getTelefones().add(clienteDto.getTelefone1());
		if(null != clienteDto.getTelefone2() ) {
			cliente.getTelefones().add(clienteDto.getTelefone2());
		}
		
		if(null != clienteDto.getTelefone3() ) {
			cliente.getTelefones().add(clienteDto.getTelefone3());
		}
		
		return cliente;

}
	
}
