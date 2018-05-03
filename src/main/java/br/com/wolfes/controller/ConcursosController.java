package br.com.wolfes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.wolfes.model.Concurso;
import br.com.wolfes.model.DetalheConcurso;
import br.com.wolfes.model.Estado;
import br.com.wolfes.scraping.ConcursoScraping;
import br.com.wolfes.util.EstadosUtil;

@Controller
public class ConcursosController {

	private static final String IP_SERVIDOR = "50.30.43.161:8080/ConcursosWS";
	@Inject private Result result;
	
	
	@Get("/")
	public void endPoitsDisponiveis() {
		List<String> points = new ArrayList<String>();
		points.add(IP_SERVIDOR.concat("/concursos/{sigla_estado}/listar"));
		points.add(IP_SERVIDOR.concat("/concursos/{sigla_estado}/detalhar/{url_concurso}"));
		points.add(IP_SERVIDOR.concat("/concursos/estados"));
		result.use(Results.json()).withoutRoot().from(points).recursive().serialize();
	}
	
	@Get("/concursos/{sigla_estado:[a-z][a-z]}/listar")
	public void listarConcursos(String sigla_estado){
		List<Concurso> listarConcursos = ConcursoScraping.listarConcursos(sigla_estado);
		result.use(Results.json()).withoutRoot().from(listarConcursos).recursive().serialize();
	}
	
	
	@Get("/concursos/{sigla_estado:[a-z][a-z]}/detalhar/{url_concurso}")
	public void detalharConcurso(String sigla_estado, String url_concurso) {
		DetalheConcurso detalhesDoConcurso = ConcursoScraping.detalharConcurso(sigla_estado, url_concurso);
		result.use(Results.json()).withoutRoot().from(detalhesDoConcurso).recursive().serialize();
	}

	@Get("/concursos/estados")
	public void listarTodosEstados() {
		//FIXME Implementar estados no aplicativo
		List<Estado> estados = 	EstadosUtil.listaEstados();
		result.use(Results.json()).withoutRoot().from(estados).recursive().serialize();
	}
}
