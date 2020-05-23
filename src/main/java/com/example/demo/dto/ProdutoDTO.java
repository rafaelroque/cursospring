package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Produto;

public class ProdutoDTO implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private Integer id;
	
	private String nome;
	
	private Double preco;

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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	 public ProdutoDTO() {
		// TODO Auto-generated constructor stub
	 
		
	}
	 
	 
	 

	 public ProdutoDTO(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public static ProdutoDTO fromProduto(Produto produto) {
		 
	  return new ProdutoDTO(produto.getId(), produto.getNome() , produto.getPreco()); 
		 
	 }
	 	
}
