package br.com.controleponto.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleponto.dto.AlunoDTO;
import br.com.controleponto.models.Aluno;
import br.com.controleponto.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public Aluno create(AlunoDTO alunoDto) {
		Aluno dadosAluno = new Aluno();
		dadosAluno.setNomeCompleto(alunoDto.getNomeCompleto());
		dadosAluno.setCpf(alunoDto.getCpf());
		dadosAluno.setEmail(alunoDto.getEmail());
		dadosAluno.setDataDeCadastro(LocalDate.now());
		return alunoRepository.save(dadosAluno);

	}

	public List<AlunoDTO> listarAlunos() {
		List<AlunoDTO> alunosDto = new ArrayList<AlunoDTO>();

		alunoRepository.findAll().forEach(aluno -> alunosDto.add(
				new AlunoDTO(aluno.getNomeCompleto(), aluno.getCpf(), aluno.getEmail(), aluno.getDataDeCadastro())));
		;

		return alunosDto;
	}
	
	public AlunoDTO getAluno(long idAluno) {
		Optional<Aluno> dadosPesquisa = alunoRepository.findById(idAluno);
		if(dadosPesquisa.isEmpty()) {
			return null;
		}
		return new AlunoDTO(dadosPesquisa.get().getNomeCompleto(), dadosPesquisa.get().getCpf(), dadosPesquisa.get().getEmail(), dadosPesquisa.get().getDataDeCadastro());
	}
	
	
	public Aluno update(long idAluno, AlunoDTO alunoDto) {
		Optional<Aluno> dadosPesquisa = alunoRepository.findById(idAluno);
		if(dadosPesquisa.isEmpty()) {
			return null;
		}else {
			Aluno dadosAluno = dadosPesquisa.get();
			dadosAluno.setNomeCompleto(alunoDto.getNomeCompleto());
			dadosAluno.setCpf(alunoDto.getCpf());
			dadosAluno.setEmail(alunoDto.getEmail());
			return alunoRepository.save(dadosAluno);			
		}
	}
}
