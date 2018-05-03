package br.com.wolfes.scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;

import br.com.wolfes.model.Concurso;
import br.com.wolfes.model.DetalheConcurso;
import br.com.wolfes.util.ParserUrlUtil;

@SuppressWarnings("unchecked")
public class ConcursoScraping extends Scraping{
	
	private static final String QUEBRA_LINHA = "\n";
	private static final String URL_SITE = "https://www.concursosnobrasil.com.br/concursos/";
	
	public static List<Concurso> listarConcursos(String sigla_estado){
		
		List<Concurso> concursos = new ArrayList<>();
		
		try {
			HtmlPage page = getWebClient().getPage(URL_SITE.concat(sigla_estado));
			
			List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//*[@id=\"conteudo\"]/table/tbody"); 
			
			for (DomNode domNode : items.get(0).getChildNodes()) {
				HtmlAnchor itemAnchor =  ((HtmlAnchor) domNode.getChildNodes().get(0).getChildNodes().get(0));

				String nomeConcurso = itemAnchor.asText();
				String url = itemAnchor.getHrefAttribute();
				String url_detalhe = ParserUrlUtil.parserUrl(url);
				String siglaEstado = ParserUrlUtil.parserSiglaEstado(url);
				String qntVagas = domNode.getChildNodes().get(1).getChildNodes().get(0).toString();

				Concurso concurso = new Concurso(nomeConcurso, qntVagas, url_detalhe, siglaEstado);
				concursos.add(concurso);
			}
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		
		
		return concursos;
	}

	
	public static DetalheConcurso detalharConcurso(String sigla_estado, String url_concurso) {
		DetalheConcurso detalhesConcurso = new DetalheConcurso();
		
		String url = URL_SITE.concat(sigla_estado).concat("/").concat(url_concurso);
		
		try {
			HtmlPage page = getWebClient().getPage(url);
			
			List<HtmlElement> item = (List<HtmlElement>) page.getByXPath("//*[@id=\"conteudo\"]/article/h1");

			String  titulo = item.get(0).getChildNodes().get(0).toString();

			item = (List<HtmlElement>) page.getByXPath("//*[@id=\"conteudo\"]/article/h2");

			String subtitulo = item.get(0).getChildNodes().get(0).toString();

			item = (List<HtmlElement>) page.getByXPath("//*[@id=\"conteudo\"]/article/div[3]");

			String conteudo = new String();
			for (int i = 0; i < item.get(0).getChildNodes().size(); i++) {
				DomNode domNode2 = item.get(0).getChildNodes().get(i);
				if(domNode2 instanceof HtmlParagraph) {
					conteudo = conteudo.concat(((HtmlParagraph) domNode2).asText());
				}
				else if(domNode2 instanceof HtmlHeading3) {
					conteudo = conteudo.concat(((HtmlHeading3) domNode2).asText());
				}
				conteudo = conteudo.concat(QUEBRA_LINHA);
			}
			
			detalhesConcurso.setSubtitulo(subtitulo);
			detalhesConcurso.setTitulo(titulo);
			detalhesConcurso.setConteudo(conteudo);
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		
		return detalhesConcurso;
	}

}
