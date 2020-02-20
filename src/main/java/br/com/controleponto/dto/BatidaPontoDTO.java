package br.com.controleponto.dto;

import javax.validation.constraints.NotNull;

public class BatidaPontoDTO {

	@NotNull(message = "idUsuario é obrigatório")
	private Long idUsuario;
	@NotNull(message = "tipoBatida é obrigatório")
	private Integer tipoBatida;

	public BatidaPontoDTO() {
	};

	public BatidaPontoDTO(Long idUsuario, Integer tipoBatida) {
		super();
		this.idUsuario = idUsuario;
		this.tipoBatida = tipoBatida;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getTipoBatida() {
		return tipoBatida;
	}

	public void setTipoBatida(Integer tipoBatida) {
		this.tipoBatida = tipoBatida;
	}

}
