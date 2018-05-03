package br.com.wolfes.model;

public class Concurso {
	
	private String titulo;
	private String vagas;
	private String url_detalhe;
	private String sigla_estado;
	
	public Concurso(String titulo, String vagas, String url_detalhe, String sigla_estado) {
		super();
		this.titulo = titulo;
		this.vagas = vagas;
		this.url_detalhe = url_detalhe;
		this.setSigla_estado(sigla_estado);
	}
	public Concurso() {
		super();
	}
	public Concurso(String titulo, String vagas) {
		super();
		this.titulo = titulo;
		this.vagas = vagas;
	}
	public String getVagas() {
		return vagas;
	}
	public void setVagas(String vagas) {
		this.vagas = vagas;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getUrl_detalhe() {
		return url_detalhe;
	}
	public void setUrl_detalhe(String url_detalhe) {
		this.url_detalhe = url_detalhe;
	}
	public String getSigla_estado() {
		return sigla_estado;
	}
	public void setSigla_estado(String sigla_estado) {
		this.sigla_estado = sigla_estado;
	}

}
