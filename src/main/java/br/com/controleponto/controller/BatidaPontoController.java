package br.com.controleponto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleponto.dto.BatidaDetalhadaDTO;
import br.com.controleponto.dto.BatidaPontoDTO;
import br.com.controleponto.response.Response;
import br.com.controleponto.service.BatidaPontoService;

@RestController
@RequestMapping("/api/v1")
public class BatidaPontoController {

	@Autowired
	private BatidaPontoService batidaPontoService;

	@PostMapping("/batida")
	@ResponseBody
	public ResponseEntity<Response<String>> create(@RequestBody @Valid BatidaPontoDTO dadosBatida,
			BindingResult result) {
		Response<String> response = new Response<String>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		} else if (batidaPontoService.create(dadosBatida) != null) {
			response.setData("OK");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.setData("Erro");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/batida/{idAluno}")
	@ResponseBody
	public BatidaDetalhadaDTO listaBatida(@PathVariable(value = "idAluno") long idAluno) {		
		return batidaPontoService.getBatidasByAluno(idAluno);
	}

}
