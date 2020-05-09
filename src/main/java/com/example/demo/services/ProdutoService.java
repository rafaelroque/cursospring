package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	
	@Autowired
	private ProdutoRepository repo;

   
}
