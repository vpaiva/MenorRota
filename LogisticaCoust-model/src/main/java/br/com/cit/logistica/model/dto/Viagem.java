package br.com.cit.logistica.model.dto;

import java.io.Serializable;

public class Viagem implements Serializable {
	private String pontoOrigem;
	private String pontoDestino;
	private Integer autonomia;
	private Double valorLitro;

	public Viagem() {
	}

	public Viagem(String pontoOrigem, String pontoDestino, Integer autonomia,
			Double valorLitro) {
		this.pontoOrigem = pontoOrigem;
		this.pontoDestino = pontoDestino;
		this.autonomia = autonomia;
		this.valorLitro = valorLitro;
	}

	public String getPontoOrigem() {
		return pontoOrigem;
	}

	public void setPontoOrigem(String pontoOrigem) {
		this.pontoOrigem = pontoOrigem;
	}

	public String getPontoDestino() {
		return pontoDestino;
	}

	public void setPontoDestino(String pontoDestino) {
		this.pontoDestino = pontoDestino;
	}

	public Integer getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(Integer autonomia) {
		this.autonomia = autonomia;
	}

	public Double getValorLitro() {
		return valorLitro;
	}

	public void setValorLitro(Double valorLitro) {
		this.valorLitro = valorLitro;
	}

}
