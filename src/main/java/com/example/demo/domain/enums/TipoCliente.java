package com.example.demo.domain.enums;

public enum TipoCliente {
	
	PESSOA_FISICA(1 , "Pessoa Fisica"),
	PESSOA_JURIDICA(2 , "Pessoa Juridica");
	
	private Integer codigo;
	private String descricao;
	public Integer getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	private TipoCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		
		for(TipoCliente t : TipoCliente.values()) {
			if(codigo.equals(t.getCodigo())) {
				return t;
			}
			
		}
		throw new IllegalArgumentException("Id inválido:"+codigo);
		
		
	}
	

}
