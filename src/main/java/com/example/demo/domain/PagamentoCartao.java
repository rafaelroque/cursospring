package com.example.demo.domain;

import javax.persistence.Entity;

import com.example.demo.domain.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public PagamentoCartao() {
	}

	public PagamentoCartao(EstadoPagamento estadoPagamento, Pedido pedido , Integer numeroParcelas ) {
		super(estadoPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	

}
