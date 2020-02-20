package br.com.controleponto.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author ferna
 *
 */
@Entity
@Component
@Table(name = "batidaPonto")
public class BatidaPonto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBatida;
	
	@ManyToOne
	@JoinColumn(name = "idResponsavel")
	private Aluno responsavel;
	
	private LocalDateTime dataHoraBatida;
	
	private Integer tipoBatida;
	
	public BatidaPonto() {};
	
	
	public BatidaPonto(Long idBatida, Aluno responsavel, LocalDateTime dataHoraBatida, Integer tipoBatida) {
		super();
		this.idBatida = idBatida;
		this.responsavel = responsavel;
		this.dataHoraBatida = dataHoraBatida;
		this.tipoBatida = tipoBatida;
	}

	public Long getIdBatida() {
		return idBatida;
	}

	public void setIdBatida(Long idBatida) {
		this.idBatida = idBatida;
	}

	public Aluno getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Aluno responsavel) {
		this.responsavel = responsavel;
	}

	public LocalDateTime getDataHoraBatida() {
		return dataHoraBatida;
	}

	public void setDataHoraBatida(LocalDateTime dataHoraBatida) {
		this.dataHoraBatida = dataHoraBatida;
	}

	public Integer getTipoBatida() {
		return tipoBatida;
	}

	public void setTipoBatida(Integer tipoBatida) {
		this.tipoBatida = tipoBatida;
	}
	
	

}
