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
import com.example.demo.domain.Pagamento;
import com.example.demo.domain.PagamentoCartao;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.EstadoPagamento;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EstadoRepository;
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
	

	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	   Categoria cat1 = new Categoria(null , "Informatica");
	   Categoria cat2 = new Categoria(null , "Escritorio");
	   
	   Produto p1 = new Produto("Computador", 2000.00);
	   Produto p2 = new Produto("Impressora", 800.00);
	   Produto p3 = new Produto("Mouse", 80.00);
	   cat1.setProdutos(Arrays.asList(p1 , p2, p3));
	   cat2.setProdutos(Arrays.asList(p2));  
	   
	   p1.setCategorias(Arrays.asList(cat1));
	   p2.setCategorias(Arrays.asList(cat1, cat2));
	   p3.setCategorias(Arrays.asList(cat1));
	   
	   Estado e1 =new Estado("Ceará");
	   Cidade c1 = new Cidade("Fortaleza");
	   Cidade c2 = new Cidade("Maracanau");
	   
	   e1.setCidades(Arrays.asList(c1 , c2));
	   
	   Cliente cliente1= new Cliente("Rafael", "rafa834@gmail.com", TipoCliente.PESSOA_FISICA, "98665278320");
	   Endereco end1  = new Endereco("Rua x", c1);
	   cliente1.setEnderecos(Arrays.asList(end1));
	   cliente1.setTelefones(Stream.of("1","2","3").collect(Collectors.toSet()));
	 
		
	   categoriaRepository.saveAll(Arrays.asList(cat1 , cat2));
	   produtoRepository.saveAll(Arrays.asList(p1 , p2 , p3));
	   estadoRepository.save(e1);
	   
	   clienteRepository.save(cliente1);
	   
	   Pedido ped1 = new Pedido(new Date(),  cliente1, end1);
	   Pagamento pagto1 = new PagamentoCartao(EstadoPagamento.QUITADO , ped1 , 6);
	   
	   ped1.setPagamento(pagto1);
	   pedidoRepository.save(ped1);
	}

}
 