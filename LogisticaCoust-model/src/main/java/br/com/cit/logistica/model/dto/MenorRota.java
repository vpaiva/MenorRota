package br.com.cit.logistica.model.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MenorRota {
	private List<String> rotas;
	private Double custo;

	public MenorRota() {
	}
	
	public MenorRota(List<String> rotas, Double custo) {
		super();
		this.rotas = rotas;
		this.custo = custo;
	}

	public List<String> getRotas() {
		return rotas;
	}

	public Double getCusto() {
		return custo;
	}

}
