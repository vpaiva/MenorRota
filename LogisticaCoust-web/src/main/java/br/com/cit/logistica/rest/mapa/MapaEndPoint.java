package br.com.cit.logistica.rest.mapa;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cit.logistica.model.dto.MapaDTO;
import br.com.cit.logistica.model.dto.MenorRota;
import br.com.cit.logistica.model.dto.Viagem;
import br.com.cit.logistica.service.MapaService;
import br.com.cit.logistica.service.MapasSingletonService;

@Path("/mapa")
public class MapaEndPoint {
	
	@Inject
	private MapaService mapaService;
	
	@Inject
	private MapasSingletonService mapasSingletonService;
	
	/**
	 * Serviço Restfull para inserir um novo mapa
	 * 
	 * @param mapaDTO
	 * @return
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrarMapa")
	@POST
	public Response cadastrarMapa(MapaDTO mapaDTO) {
		mapaService.salvar(mapaDTO);
	
		return Response.ok().build();
	}
	
	/**
	 * Serviço Restfull para carregar mapas ja cadastrados
	 * 
	 * @return
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/listarMapas")
	public List<MapaDTO> buscarMapa() {
		List<MapaDTO> buscarTodos = mapaService.buscarTodos();
		
		return buscarTodos;
	}
	
	/**
	 * Serviço Restfull para pesquisar a rota de menor custo
	 * 
	 * @param viagem
	 * @return
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/buscarMenorRota")
	public MenorRota buscarViagem(Viagem viagem) {
		return mapasSingletonService.buscarMenorRota(viagem);
	}
}
