package br.com.cit.logistica.model;

public class MapaChave {
	private final String pontoOrigem;
	private final String pontoDestino;
	private final Mapa mapa;

	public MapaChave(String pontoOrigem, String pontoDestino, Mapa mapa) {
		this.pontoOrigem = pontoOrigem;
		this.pontoDestino = pontoDestino;
		this.mapa = mapa;
	}

	public String getPontoOrigem() {
		return pontoOrigem;
	}

	public String getPontoDestino() {
		return pontoDestino;
	}

	public Mapa getMapa() {
		return mapa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((pontoDestino == null) ? 0 : pontoDestino.hashCode());
		result = prime * result
				+ ((pontoOrigem == null) ? 0 : pontoOrigem.hashCode());
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
		MapaChave other = (MapaChave) obj;
		if (pontoDestino == null) {
			if (other.pontoDestino != null)
				return false;
		} else if (!pontoDestino.equals(other.pontoDestino))
			return false;
		if (pontoOrigem == null) {
			if (other.pontoOrigem != null)
				return false;
		} else if (!pontoOrigem.equals(other.pontoOrigem))
			return false;
		return true;
	}

}
