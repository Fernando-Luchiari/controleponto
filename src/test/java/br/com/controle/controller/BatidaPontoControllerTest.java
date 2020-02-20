package br.com.controle.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import br.com.controleponto.controller.BatidaPontoController;
import br.com.controleponto.dto.BatidaDetalhadaDTO;
import br.com.controleponto.dto.BatidaPontoDTO;
import br.com.controleponto.models.Aluno;
import br.com.controleponto.models.BatidaDetalhada;
import br.com.controleponto.models.BatidaPonto;
import br.com.controleponto.response.Response;
import br.com.controleponto.service.BatidaPontoService;

@RunWith(MockitoJUnitRunner.class)
public class BatidaPontoControllerTest {

	@InjectMocks
	BatidaPontoController batidaPontoController;

	@Mock
	BatidaPontoService batidaPontoService;

	@MockBean
	private BindingResult bindingResult;

	@Test
	public void createTest() {
		Aluno dadosAluno = new Aluno(1L, "Fernando", 35207774875L, "teste@test.com", LocalDate.now());
		BatidaPonto dadosBatida = new BatidaPonto(1L, dadosAluno, LocalDateTime.now(), 1);
		BatidaPontoDTO dadosBatidaDto = new BatidaPontoDTO(1L, 1);

		bindingResult = Mockito.mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);
		when(batidaPontoService.create(dadosBatidaDto)).thenReturn(dadosBatida);
		ResponseEntity<Response<String>> dadosRetorno = batidaPontoController.create(dadosBatidaDto, bindingResult);

		assertEquals(dadosRetorno.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void createTestError() {
		BatidaPontoDTO dadosBatidaDto = new BatidaPontoDTO(1L, 1);

		bindingResult = Mockito.mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);

		ResponseEntity<Response<String>> dadosRetorno = batidaPontoController.create(dadosBatidaDto, bindingResult);

		assertEquals(dadosRetorno.getStatusCode(), HttpStatus.BAD_REQUEST);

	}
	
	@Test
	public void listaBatidaTest() {
		List<BatidaDetalhada> dadosBatidas = new ArrayList<BatidaDetalhada>();		
		BatidaDetalhada dadosBatida1 = new BatidaDetalhada("entrada",LocalDateTime.now());
		BatidaDetalhada dadosBatida2 = new BatidaDetalhada("saída",LocalDateTime.now());
		dadosBatidas.add(dadosBatida1);
		dadosBatidas.add(dadosBatida2);
		BatidaDetalhadaDTO dadosDetalhado = new BatidaDetalhadaDTO();
		dadosDetalhado.setNomeUsuario("Fernando Coutinho");
		dadosDetalhado.setBatidas(dadosBatidas);
		dadosDetalhado.setHorasTrabalhadas("00:00:05 horas");
		
		when(batidaPontoService.getBatidasByAluno(1L)).thenReturn(dadosDetalhado);
		BatidaDetalhadaDTO dadosRetorno = batidaPontoController.listaBatida(1L);

		assertEquals(dadosRetorno.getNomeUsuario(), dadosDetalhado.getNomeUsuario());
	}

}
