package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.example.demo.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String nome;
	
	private String email;
	
	private Integer tipo;
	
	private String cpfCnpj;
	
	@OneToMany(mappedBy = "cliente" , cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name  ="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	
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
	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}
	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo();
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		for(Endereco e : enderecos) {
			e.setCliente(this);
		}
		this.enderecos = enderecos;
	}
	public Set<String> getTelefones() {
		return telefones;
	}
	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public Cliente() {
		
	}
	
	public Cliente(String nome, String email, String cpfCnpj,TipoCliente tipoCliente ) {
		super();
		this.nome = nome;
		this.email = email;
		this.tipo = (tipoCliente==null) ? null :  tipoCliente.getCodigo();
		this.cpfCnpj = cpfCnpj;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}
 