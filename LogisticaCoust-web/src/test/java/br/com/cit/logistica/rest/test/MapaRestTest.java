package br.com.cit.logistica.rest.test;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.cit.logistica.model.dto.MapaDTO;
import br.com.cit.logistica.model.dto.MenorRota;
import br.com.cit.logistica.model.dto.Viagem;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapaRestTest extends Teste {
	
	private final static String CADASTRAR_MAPA_URL = "/mapa/cadastrarMapa";
	private final static String BUSCAR_MAPAS_URL = "/mapa/listarMapas";
	private final static String MENOR_ROTA_URL = "/mapa/buscarMenorRota";
	
	@Test
	public void aCadastrarMapas() {
		MapaDTO mapa1 = new MapaDTO("m1", "A", "B", 10);
		MapaDTO mapa2 = new MapaDTO("m2", "B", "D", 15);
		MapaDTO mapa3 = new MapaDTO("m3", "A", "C", 20);
		MapaDTO mapa4 = new MapaDTO("m4", "C", "D", 30);
		MapaDTO mapa5 = new MapaDTO("m5", "B", "E", 50);
		MapaDTO mapa6 = new MapaDTO("m6", "D", "E", 30);
		
		proccessPostTest(CADASTRAR_MAPA_URL, mapa1);
		proccessPostTest(CADASTRAR_MAPA_URL, mapa2);
		proccessPostTest(CADASTRAR_MAPA_URL, mapa3);
		proccessPostTest(CADASTRAR_MAPA_URL, mapa4);
		proccessPostTest(CADASTRAR_MAPA_URL, mapa5);
		proccessPostTest(CADASTRAR_MAPA_URL, mapa6);
		
		Response proccessGetTest = proccessGetTest(BUSCAR_MAPAS_URL);
		List<MapaDTO> listMapaDto = proccessGetTest.readEntity(new GenericType<List<MapaDTO>>(){});
		
		Assert.assertTrue(listMapaDto.contains(mapa1));
		Assert.assertTrue(listMapaDto.contains(mapa2));
		Assert.assertTrue(listMapaDto.contains(mapa3));
		Assert.assertTrue(listMapaDto.contains(mapa4));
		Assert.assertTrue(listMapaDto.contains(mapa5));
		Assert.assertTrue(listMapaDto.contains(mapa6));
	}
	
	@Test
	public void buscarMenorRota() {
		MenorRota menorRota = proccessPostTest(MENOR_ROTA_URL, new Viagem("A", "D", 10, 2.5), MenorRota.class);
		
		Assert.assertEquals(6.25D, menorRota.getCusto(), 0);
		
		Assert.assertEquals("[A, B, D]", menorRota.getRotas().toString());
	}
	
}
