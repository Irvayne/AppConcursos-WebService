package br.com.wolfes.model;

public class DetalheConcurso {

	private String titulo;
	private String subtitulo;
	private String conteudo;

	public DetalheConcurso() {
		super();
	}
	public DetalheConcurso(String titulo, String subtitulo, String conteudo) {
		super();
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.conteudo = conteudo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}


}
