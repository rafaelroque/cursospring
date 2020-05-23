package com.example.demo.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ItemPedido;
import com.example.demo.domain.PagamentoBoleto;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.EstadoPagamento;
import com.example.demo.repositories.ItemPedidoRepository;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.ProdutoRepository;
import com.example.demo.services.exception.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido buscar(Integer id) {

		Optional<Pedido> pedido = repo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido nao encontrado"));

	}

	@Transactional
	public @Valid Pedido insert(@Valid Pedido obj) {
		obj.setId(null);
		obj.setData(new Date());
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagamentoBoleto = (PagamentoBoleto)obj.getPagamento();
			boletoService.preencherDataPagamento(pagamentoBoleto , obj.getData());
		}

		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.00);
			Optional<Produto> p = produtoRepository.findById(ip.getProduto().getId());
			ip.setPreco(p.get().getPreco());
			ip.setPedido(obj);
		}
		
		obj = repo.save(obj);
		
		itemPedidoRepository.saveAll(obj.getItens());

		return obj;
	}

}
