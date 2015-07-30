package br.com.cit.logistica.service;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;

import br.com.cit.logistica.dao.MapaDao;
import br.com.cit.logistica.model.Mapa;
import br.com.cit.logistica.model.dto.MapaDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class MapaService implements Serializable {

	private static final long serialVersionUID = -1L;

	@Inject
	private MapaDao mapaDao;

	public void salvar(MapaDTO mapa) {
		mapaDao.create(fromDTO(mapa));
	}

	public List<MapaDTO> buscarTodos() {
		return mapaDao.listAll().stream().map(mapa -> toDTO(mapa))
				.collect(Collectors.toList());
	}

	public MapaDTO buscarMapaPorNome(String nome) {
		Optional<Mapa> findByName = mapaDao.findByName(nome);

		return findByName.isPresent() ? toDTO(findByName.get()) : null;
	}

	private Mapa fromDTO(MapaDTO dto) {
		return new Mapa(dto.getNome(), dto.getPontoOrigem(),
				dto.getPontoDestino(), dto.getDistanciaPontos());
	}

	private MapaDTO toDTO(Mapa mapa) {
		return new MapaDTO(mapa.getNome(), mapa.getPontoOrigem(),
				mapa.getPontoDestino(), mapa.getDistanciaPontos());
	}
}