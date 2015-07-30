package br.com.cit.logistica.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import es.usc.citius.hipster.algorithm.Algorithm;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.model.impl.WeightedNode;
import es.usc.citius.hipster.model.problem.SearchProblem;
import es.usc.citius.hipster.util.graph.GraphBuilder;
import es.usc.citius.hipster.util.graph.GraphBuilder.Assistant.Vertex1;
import es.usc.citius.hipster.util.graph.GraphSearchProblem;
import es.usc.citius.hipster.util.graph.HipsterDirectedGraph;
import br.com.cit.logistica.dao.MapaDao;
import br.com.cit.logistica.model.Mapa;
import br.com.cit.logistica.model.MapaChave;
import br.com.cit.logistica.model.dto.MenorRota;
import br.com.cit.logistica.model.dto.Viagem;

@Singleton
public class MapasSingletonService {

	@Inject
	private MapaDao mapaDao;

	private HipsterDirectedGraph<String, Integer> graphsMapa;

	private Set<Mapa> listAllMaps;
	
	private Set<MapaChave> mapaList;
	
	@PostConstruct
	private void initialize() {
		listAllMaps = new HashSet<>(mapaDao.listAll());
		
		buildGraph(listAllMaps);
	}

	private void buildGraph(Set<Mapa> listAllMaps) {
		mapaList = new HashSet<>();
		
		for(Mapa mapa : listAllMaps) {
			mapaList.add(new MapaChave(mapa.getPontoOrigem(), mapa.getPontoDestino(), mapa));
		}
		
		if (!listAllMaps.isEmpty()) {
			Vertex1<String>.Vertex2.Builder<String, Integer> vertex = null;

			Iterator<Mapa> iterator = listAllMaps.iterator();

			if (iterator.hasNext()) {
				Mapa mapa = iterator.next();
				
				vertex = GraphBuilder.create().connect(mapa.getPontoOrigem())
						.to(mapa.getPontoDestino())
						.withEdge(mapa.getDistanciaPontos());
			}

			while (iterator.hasNext()) {
				Mapa mapa = iterator.next();
				vertex = vertex.connect(mapa.getPontoOrigem())
						.to(mapa.getPontoDestino())
						.withEdge(mapa.getDistanciaPontos());
			}

			if (vertex != null) {
				graphsMapa = vertex.buildDirectedGraph();
			}
		}
	}
	
	public MenorRota buscarMenorRota(Viagem viagem) {
		final SearchProblem<Integer, String, WeightedNode<Integer, String, Double>> problem = GraphSearchProblem.startingFrom(viagem.getPontoOrigem()).in(graphsMapa)
                .takeCostsFromEdges().build();

		Algorithm<Integer, String, WeightedNode<Integer, String, Double>>.SearchResult searchAlgoritm = Hipster.createDijkstra(problem).search(viagem.getPontoDestino());
	
		List<List<String>> optimalPaths = searchAlgoritm.getOptimalPaths();
		
		WeightedNode<Integer, String, Double> goalNode = searchAlgoritm.getGoalNode();
		
		goalNode.getScore();
		
		return processarCusto(viagem, optimalPaths, goalNode);
	}
	
	private MenorRota processarCusto(Viagem viagem, List<List<String>> optimalPaths, WeightedNode<Integer, String, Double> goalNode) {
		Double distancia = goalNode.getCost();
		Integer autonomia = viagem.getAutonomia();
		
		Double litros = distancia / autonomia;
		
		Double custo = litros * viagem.getValorLitro();
		
		List<String> rotas = new ArrayList<>();
		
		for (List<String> listRotas : optimalPaths) {
			rotas = listRotas;
		}
		
		MenorRota menorRota = new MenorRota(rotas, custo);
		
		return menorRota;
	}

	public void addMap(@Observes Mapa mapaCadastrado) {
		persistirMapa(mapaCadastrado);
		
		
		
		buildGraph(listAllMaps);
	}

	private void persistirMapa(Mapa mapaCadastrado) {
		Predicate<Mapa> predicateNome = mapa -> mapaCadastrado.getNome().equals(mapa.getNome());
		
		Optional<Mapa> mapaJaSalvo = listAllMaps.stream().parallel().filter(predicateNome).findFirst();
		
		if(mapaJaSalvo.isPresent()) {
			mapaCadastrado.setId(mapaJaSalvo.get().getId());
			mapaDao.update(mapaCadastrado);
			
			listAllMaps.remove(mapaJaSalvo);
			listAllMaps.add(mapaCadastrado);
			
		} else {
			mapaDao.save(mapaCadastrado);
			
			listAllMaps.add(mapaCadastrado);
		}
	}
	
}
