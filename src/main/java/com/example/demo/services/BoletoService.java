package com.example.demo.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.domain.PagamentoBoleto;

@Service
public class BoletoService {
	
	public void preencherDataPagamento(PagamentoBoleto pagamento , Date data) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DAY_OF_MONTH, 7);
		
		pagamento.setDataVencimento(c.getTime());
		
		
	}

}
