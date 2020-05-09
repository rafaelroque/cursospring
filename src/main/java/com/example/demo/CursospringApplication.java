package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Estado;
import com.example.demo.domain.Produto;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.EstadoRepository;
import com.example.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	

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
	 
		
	   categoriaRepository.saveAll(Arrays.asList(cat1 , cat2));
	   produtoRepository.saveAll(Arrays.asList(p1 , p2 , p3));
	   estadoRepository.save(e1);
	   
	}

}
 