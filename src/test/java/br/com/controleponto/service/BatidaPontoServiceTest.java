package br.com.controleponto.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.controleponto.dto.BatidaDetalhadaDTO;
import br.com.controleponto.dto.BatidaPontoDTO;
import br.com.controleponto.models.Aluno;
import br.com.controleponto.models.BatidaPonto;
import br.com.controleponto.repositories.AlunoRepository;
import br.com.controleponto.repositories.BatidaPontoRepository;

@RunWith(MockitoJUnitRunner.class)
public class BatidaPontoServiceTest {
	
	@InjectMocks
	BatidaPontoService batidaService;
	

	@Mock
	BatidaPontoRepository batidaPontoRepository;
	@Mock
	AlunoRepository alunoRepository;
	
	
	@Test
	public void testCreate() {
		Aluno dadosAluno = new Aluno(1L,"Fernando",35207774875L,"teste@test.com",LocalDate.now());
		
		BatidaPonto dadosBatida = new BatidaPonto(1L,dadosAluno,LocalDateTime.now(),1);
		
		BatidaPontoDTO dadosBatidaDto = new BatidaPontoDTO(1L,1);
		when(batidaPontoRepository.save(any(BatidaPonto.class))).thenReturn(dadosBatida);
		when(alunoRepository.findById(dadosBatidaDto.getIdUsuario())).thenReturn(Optional.of(dadosAluno));
		
		BatidaPonto batidaSalva = batidaService.create(dadosBatidaDto);		
		assertTrue(batidaSalva.getIdBatida().equals(dadosBatida.getIdBatida()));
	}
	
	
	@Test
	public void testCreateRetornoNulo() {
						
		BatidaPontoDTO dadosBatidaDto = new BatidaPontoDTO(1L,1);
		
		
		BatidaPonto batidaSalva = batidaService.create(dadosBatidaDto);		
		assertNull(batidaSalva);
	}
	
	
	
	public void getBatidasByAluno() {
		Aluno dadosAlunoEnviado = Mockito.mock(Aluno.class);;
		dadosAlunoEnviado.setId(1L);
		Aluno dadosAluno = new Aluno(1L,"Fernando",35207774875L,"teste@test.com",LocalDate.now());
		List<BatidaPonto> dadosBatidas = Mockito.mock(List.class);
		
		BatidaPonto dadosBatida1 = new BatidaPonto(1L,dadosAluno,LocalDateTime.now(),1);
		BatidaPonto dadosBatida2 = new BatidaPonto(2L,dadosAluno,LocalDateTime.now(),2);
		dadosBatidas.add(dadosBatida1);
		dadosBatidas.add(dadosBatida2);		
		
		when(batidaPontoRepository.findAllByResponsavel(dadosAlunoEnviado)).thenReturn(dadosBatidas);
		
		BatidaDetalhadaDTO dadosEncontrados = batidaService.getBatidasByAluno(dadosAluno.getId());	
		
		assertTrue(dadosAluno.getNomeCompleto().equalsIgnoreCase(dadosEncontrados.getNomeUsuario()));
	}
	
	@Test
	public void getBatidasByAlunoNull() {
		Aluno dadosAlunoEnviado = new Aluno();
		dadosAlunoEnviado.setId(1L);
		Aluno dadosAluno = new Aluno(1L,"Fernando",35207774875L,"teste@test.com",LocalDate.now());
		List<BatidaPonto> dadosBatidas = new ArrayList<BatidaPonto>();
		
		BatidaPonto dadosBatida1 = new BatidaPonto(1L,dadosAluno,LocalDateTime.now(),1);
		BatidaPonto dadosBatida2 = new BatidaPonto(2L,dadosAluno,LocalDateTime.now(),2);
		dadosBatidas.add(dadosBatida1);
		dadosBatidas.add(dadosBatida2);		
		
		
		
		BatidaDetalhadaDTO dadosEncontrados = batidaService.getBatidasByAluno(dadosAluno.getId());	
		
		assertNull(dadosEncontrados);
	}

}
