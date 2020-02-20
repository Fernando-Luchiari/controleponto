package br.com.controleponto.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AlunoDTO {
	
	private String nomeCompleto;
	
	private Long cpf;
	
	private String email;
	
	private LocalDate dataCadastro;
	
	public AlunoDTO() {}
	
	public AlunoDTO(String nomeCompleto, Long cpf, String email, LocalDate dataCadastro) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.email = email;
		this.dataCadastro = dataCadastro;
	}

	@NotNull(message = "nomeCompleto é obrigatório")
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	@NotNull(message = "cpf é obrigatório")
	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	@NotNull(message = "email é obrigatório")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotNull(message = "dataCadastro é obrigatória")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	
	
}
