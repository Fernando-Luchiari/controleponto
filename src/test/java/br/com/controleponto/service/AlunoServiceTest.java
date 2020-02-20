package br.com.controleponto.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.controleponto.dto.AlunoDTO;
import br.com.controleponto.models.Aluno;
import br.com.controleponto.repositories.AlunoRepository;

@RunWith(MockitoJUnitRunner.class)
public class AlunoServiceTest {
	@InjectMocks
	AlunoService alunoService;

	@Mock
	AlunoRepository alunoRepository;
	
	@Test
	public void testCreate() {
		Aluno dadosAluno = new Aluno(1L,"Fernando",35207774875L,"teste@test.com",LocalDate.now());
		AlunoDTO dadosAlunoDto = new AlunoDTO("Fernando",35207774875L,"teste@test.com",LocalDate.now());
		when(alunoRepository.save(any(Aluno.class))).thenReturn(dadosAluno);		
		Aluno alunoSalvo = alunoService.create(dadosAlunoDto);		
		assertTrue(dadosAluno.getNomeCompleto().equals(alunoSalvo.getNomeCompleto()));		
	}
	@Test
	public void testGetAlunos() {
		List<AlunoDTO> dadosAlunoDTO = new ArrayList<AlunoDTO>();
		dadosAlunoDTO.add(new AlunoDTO("Fernando",35207774875L,"teste@test.com",LocalDate.now()));
		dadosAlunoDTO.add(new AlunoDTO("Fernando Luchiari",35207774847L,"teste@teste.com",LocalDate.now()));
		
		List<Aluno> dadosAluno = new ArrayList<Aluno>();
		dadosAluno.add(new Aluno(1L,"Fernando",35207774875L,"teste@test.com",LocalDate.now()));
		dadosAluno.add(new Aluno(2L,"Fernando Luchiari",35207774847L,"teste@teste.com",LocalDate.now()));
		
		when(alunoRepository.findAll()).thenReturn(dadosAluno);
		
		List<AlunoDTO> dadosAlunoDTOListados = alunoService.listarAlunos();
		
		assertTrue(dadosAlunoDTO.get(1).getCpf().equals(dadosAlunoDTOListados.get(1).getCpf()));		
	}
	
	@Test
	public void testGetAluno() {
		Aluno dadosAluno = new Aluno(1L,"Fernando",35207774875L,"teste@test.com",LocalDate.now());
		AlunoDTO dadosAlunoDto = new AlunoDTO("Fernando",35207774875L,"teste@test.com",LocalDate.now());
		
		when(alunoRepository.findById(1L)).thenReturn(Optional.of(dadosAluno));
		AlunoDTO alunoEncontrado = alunoService.getAluno(1L);
		
		assertTrue(dadosAlunoDto.getNomeCompleto().equals(alunoEncontrado.getNomeCompleto()));
		
	}
	
	@Test
	public void testUpdateAluno() {
		Aluno dadosAluno = new Aluno(1L,"Fernando",35207774875L,"teste@test.com",LocalDate.now());
		AlunoDTO dadosAlunoDto = new AlunoDTO("Fernando Luchiari",35207774875L,"teste@test.com",LocalDate.now());
		Aluno dadosAlunoAlterado = new Aluno(1L,"Fernando Luchiari",35207774875L,"teste@test.com",LocalDate.now());
		
		when(alunoRepository.findById(1L)).thenReturn(Optional.of(dadosAluno));
		when(alunoRepository.save(any(Aluno.class))).thenReturn(dadosAlunoAlterado);
		Aluno alunoEncontrado = alunoService.update(1L,dadosAlunoDto);
		
		assertTrue(alunoEncontrado.getNomeCompleto().equals(dadosAlunoAlterado.getNomeCompleto()));
		
	}
	
}
