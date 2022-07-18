package com.hortifruti.api.request;

import java.io.Serializable;

public class EstabelecimentoUpdateRequest implements Serializable {

	private static final long serialVersionUID = 3511882243694497313L;

	private String id;
	private String userId;
	private String nome;
	private String logo;
	private boolean bloqueado;
	private int online;

	public EstabelecimentoUpdateRequest() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

}
