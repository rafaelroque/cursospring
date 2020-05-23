package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.example.demo.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("pagamentoComBoleto")
@Entity
public class PagamentoBoleto extends Pagamento {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dataPagamento;
	private Date dataVencimento;
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public PagamentoBoleto() {
		
	}
	public PagamentoBoleto(EstadoPagamento estadoPagamento, Pedido pedido , Date dataPagamento , Date dataVencimento) {
		super(estadoPagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
