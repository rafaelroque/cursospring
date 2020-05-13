package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import com.example.demo.domain.Cliente;

public class ClienteDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 5 , max=100 , message = "Tamanho do campo inválido")
	private String nome;
	
	@Email(message = "Email invalido")
	private String email;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	public ClienteDTO() {
		
	}
	public ClienteDTO(Integer id, String nome, String email ) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public static ClienteDTO fromCliente (Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
	}

}
