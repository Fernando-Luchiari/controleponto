package br.com.controleponto.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BatidaDetalhada {
	
	private String tipoBatida;
	
	private LocalDateTime dataHoraBatida;
	
	public BatidaDetalhada() {};

	public BatidaDetalhada(String tipoBatida, LocalDateTime dataHoraBatida) {
		super();
		this.tipoBatida = tipoBatida;
		this.dataHoraBatida = dataHoraBatida;
	}

	public String getTipoBatida() {
		return tipoBatida;
	}

	public void setTipoBatida(String tipoBatida) {
		this.tipoBatida = tipoBatida;
	}

	public String getDataHoraBatida() {
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	     String formatDateTime = dataHoraBatida.format(formatter);
		
		return formatDateTime;
	}
	
	public LocalDateTime dataHoraBatidaLocalDateTime() {
		return dataHoraBatida;
	}

	public void setDataHoraBatida(LocalDateTime dataHoraBatida) {
		this.dataHoraBatida = dataHoraBatida;
	}
	
	

}
