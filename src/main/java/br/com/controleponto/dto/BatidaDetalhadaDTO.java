package br.com.controleponto.dto;

import java.util.List;

import br.com.controleponto.models.BatidaDetalhada;

public class BatidaDetalhadaDTO {
	
	private String nomeUsuario;
	
	private List<BatidaDetalhada> batidas;
	
	private String horasTrabalhadas;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public List<BatidaDetalhada> getBatidas() {
		return batidas;
	}

	public void setBatidas(List<BatidaDetalhada> batidas) {
		this.batidas = batidas;
	}

	public String getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(String horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	

}
