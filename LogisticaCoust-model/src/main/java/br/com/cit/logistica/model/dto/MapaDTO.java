package br.com.cit.logistica.model.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MapaDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9042514319328773387L;

	private String nome;

	private String pontoOrigem;

	private String pontoDestino;

	private Integer distanciaPontos;
	
	public MapaDTO() {
	}

	public MapaDTO(String nome, String pontoOrigem, String pontoDestino,
			Integer distanciaPontos) {
		super();
		this.nome = nome;
		this.pontoOrigem = pontoOrigem;
		this.pontoDestino = pontoDestino;
		this.distanciaPontos = distanciaPontos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Integer getDistanciaPontos() {
		return distanciaPontos;
	}

	public void setDistanciaPontos(Integer distanciaPontos) {
		this.distanciaPontos = distanciaPontos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapaDTO other = (MapaDTO) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
}
