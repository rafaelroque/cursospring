package com.example.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Estado;
import com.example.demo.domain.ItemPedido;
import com.example.demo.domain.Pagamento;
import com.example.demo.domain.PagamentoBoleto;
import com.example.demo.domain.PagamentoCartao;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.EstadoPagamento;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EstadoRepository;
import com.example.demo.repositories.ItemPedidoRepository;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;


	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null , "Informatica");
		Categoria cat2 = new Categoria(null , "Escritorio");
		Categoria cat3 = new Categoria(null , "Jardinagem");
		Categoria cat4 = new Categoria(null , "Decoração");
		Categoria cat5 = new Categoria(null , "Perfumaria");
		Categoria cat6 = new Categoria(null , "Cama mesa banho");
		Categoria cat7 = new Categoria(null , "Eletronicos");

		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto( "Impressora", 800.00);
		Produto p3 = new Produto( "Mouse", 80.00);
		Produto p4 = new Produto( "Mesa de escritório", 300.00);
		Produto p5 = new Produto( "Toalha", 50.00);
		Produto p6 = new Produto( "Colcha", 200.00);
		Produto p7 = new Produto( "TV true color", 1200.00);
		Produto p8 = new Produto( "Roçadeira", 800.00);
		Produto p9 = new Produto( "Abajour", 100.00);
		Produto p10 = new Produto( "Pendente", 180.00);
		Produto p11 = new Produto( "Shampoo", 90.00);

		cat1.setProdutos(Arrays.asList(p1 , p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));   

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7)); 

		Estado e1 =new Estado("Ceará");
		Cidade c1 = new Cidade("Fortaleza");
		Cidade c2 = new Cidade("Maracanau");

		e1.setCidades(Arrays.asList(c1 , c2));

		Cliente cliente1= new Cliente("Rafael", "rafa834@gmail.com", "98665278320",TipoCliente.PESSOA_FISICA);
		Endereco end1  = new Endereco("Rua x", "10", "bl a", "teste", "60000", cliente1, c1);
		cliente1.setEnderecos(Arrays.asList(end1));
		cliente1.setTelefones(Stream.of("1","2","3").collect(Collectors.toSet()));


		categoriaRepository.saveAll(Arrays.asList(cat1 , cat2 , cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		estadoRepository.save(e1);

		clienteRepository.save(cliente1);

		Pedido ped1 = new Pedido(new Date(),  cliente1, end1);
		Pedido ped2 = new Pedido(new Date(),  cliente1, end1);
		Pagamento pagto1 = new PagamentoCartao(EstadoPagamento.QUITADO , ped1 , 6);
		Pagamento pagto2 = new PagamentoBoleto(EstadoPagamento.QUITADO, ped2 , new Date() , new Date());

		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		pedidoRepository.saveAll(Arrays.asList(ped1 , ped2));

		ItemPedido itemPedido1 = new ItemPedido(ped1, p1, 50.00, 5, 200.00);
		ItemPedido itemPedido2 = new ItemPedido(ped1, p2, 0.00, 5, 200.00);
		ItemPedido itemPedido3 = new ItemPedido(ped2, p1, 0.00, 3, 100.00);
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1 , itemPedido2 ,itemPedido3));

	}

}
