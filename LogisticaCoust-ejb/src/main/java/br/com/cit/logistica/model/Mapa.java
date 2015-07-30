package br.com.cit.logistica.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import java.lang.Override;

@Entity
public class Mapa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "nome", nullable = false, unique=true)
	@NotEmpty
	private String nome;

	@Column(name = "pontoOrigem", nullable = false)
	@NotEmpty
	private String pontoOrigem;

	@Column(name = "pontoDestino", nullable = false)
	@NotEmpty
	private String pontoDestino;

	@Column(name = "distanciaPontos", nullable = false)
	@Min(1)
	@NotNull
	private Integer distanciaPontos;
	
	public Mapa() {
	}

	public Mapa(String nome, String pontoOrigem, String pontoDestino,
			Integer distanciaPontos) {
		super();
		this.nome = nome;
		this.pontoOrigem = pontoOrigem;
		this.pontoDestino = pontoDestino;
		this.distanciaPontos = distanciaPontos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
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
		Mapa other = (Mapa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
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
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nome != null && !nome.trim().isEmpty())
			result += "nome: " + nome;
		if (pontoOrigem != null && !pontoOrigem.trim().isEmpty())
			result += ", pontoOrigem: " + pontoOrigem;
		if (pontoDestino != null && !pontoDestino.trim().isEmpty())
			result += ", pontoDestino: " + pontoDestino;
		if (distanciaPontos != null)
			result += ", distanciaPontos: " + distanciaPontos;
		return result;
	}
}