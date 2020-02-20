package br.com.controle.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import br.com.controleponto.controller.AlunoController;
import br.com.controleponto.dto.AlunoDTO;
import br.com.controleponto.models.Aluno;
import br.com.controleponto.response.Response;
import br.com.controleponto.service.AlunoService;

@RunWith(MockitoJUnitRunner.class)
public class AlunoControllerTest {

	@InjectMocks
	AlunoController alunoController;

	@Mock
	AlunoService alunoService;

	@MockBean
	private BindingResult bindingResult;
	
	@Test
	public void createAlunoTest() {
		Aluno dadosAluno = new Aluno(1L,"Fernando",35207774875L,"teste@test.com",LocalDate.now());
		AlunoDTO alunoDto = new AlunoDTO("Fernando",35207774875L,"teste@test.com",LocalDate.now());
		
		bindingResult = Mockito.mock(BindingResult.class);
	    when(bindingResult.hasErrors()).thenReturn(false);
		when(alunoService.create(alunoDto)).thenReturn(dadosAluno);
		ResponseEntity<Response<String>> dadosRetorno = alunoController.createAluno(alunoDto, bindingResult);
		
		assertEquals(dadosRetorno.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void createAlunoBadRequestTest() {
		AlunoDTO alunoDto = new AlunoDTO("Fernando",35207774875L,"teste@test.com",LocalDate.now());
		
		bindingResult = Mockito.mock(BindingResult.class);
	    when(bindingResult.hasErrors()).thenReturn(true);
		ResponseEntity<Response<String>> dadosRetorno = alunoController.createAluno(alunoDto, bindingResult);
		
		assertEquals(dadosRetorno.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void getAlunoTest() {
		
		AlunoDTO alunoDto = new AlunoDTO("Fernando",35207774875L,"teste@test.com",LocalDate.now());
		
		when(alunoService.getAluno(1L)).thenReturn(alunoDto);
		AlunoDTO dadoRetorno = alunoController.getAluno(1L);
		
		assertEquals(dadoRetorno.getCpf(),alunoDto.getCpf());
	}
	
	@Test
	public void getAlunosTest() {
		List<AlunoDTO> dadosAlunoDTO = new ArrayList<AlunoDTO>();
		dadosAlunoDTO.add(new AlunoDTO("Fernando",35207774875L,"teste@test.com",LocalDate.now()));
		dadosAlunoDTO.add(new AlunoDTO("Fernando Luchiari",35207774847L,"teste@teste.com",LocalDate.now()));
		
		when(alunoService.listarAlunos()).thenReturn(dadosAlunoDTO);
		List<AlunoDTO> dadosRetorno = alunoController.getAlunos();
		
		assertNotNull(dadosRetorno.get(1).getCpf());
	}
	
	@Test
	public void updateAlunoTest() {
		AlunoDTO alunoDto = new AlunoDTO("Fernando",35207774875L,"teste@test.com",LocalDate.now());
		Aluno dadosAluno = new Aluno(1L,"Fernando",35207774875L,"teste@test.com",LocalDate.now());
		
		
		when(alunoService.update(1L,alunoDto)).thenReturn(dadosAluno);
		ResponseEntity<Response<String>> dadosRetorno = alunoController.updateAluno(1L, alunoDto);
		
		assertEquals(dadosRetorno.getStatusCode(),HttpStatus.OK);
		
	}

}
