package br.com.controleponto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleponto.dto.AlunoDTO;
import br.com.controleponto.response.Response;
import br.com.controleponto.service.AlunoService;
@RestController
@RequestMapping("/api/v1")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;

	@GetMapping("/status")
	@ResponseBody
	public ResponseEntity<String> getStatus() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body("Service is up");
	}
	
	@PostMapping("/aluno")
	@ResponseBody
	public ResponseEntity<Response<String>> createAluno( @RequestBody @Valid AlunoDTO dadosAluno, BindingResult result) {
		Response<String> response = new Response<String>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}else if(alunoService.create(dadosAluno)!= null) {
			response.setData("OK");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			response.setData("Erro");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
		
	}
	
	
	@GetMapping("/aluno/{idAluno}")
	@ResponseBody
	public AlunoDTO getAluno(@PathVariable(value = "idAluno") long idAluno) {
		return alunoService.getAluno(idAluno);		
	}
	@GetMapping("/alunos")
	@ResponseBody
	public List<AlunoDTO> getAlunos() {
		return alunoService.listarAlunos();
	}
	@PutMapping("/aluno/{idAluno}")
	@ResponseBody
	public ResponseEntity<Response<String>> updateAluno(@PathVariable(value = "idAluno") long idAluno,@RequestBody @Valid AlunoDTO dadosAluno){
		Response<String> response = new Response<String>();
		if(alunoService.update(idAluno,dadosAluno) == null) {
			response.setData("Erro");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}else {
			response.setData("OK");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
	}

}
