package br.com.controleponto.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleponto.dto.BatidaDetalhadaDTO;
import br.com.controleponto.dto.BatidaPontoDTO;
import br.com.controleponto.models.Aluno;
import br.com.controleponto.models.BatidaDetalhada;
import br.com.controleponto.models.BatidaPonto;
import br.com.controleponto.repositories.AlunoRepository;
import br.com.controleponto.repositories.BatidaPontoRepository;

@Service
public class BatidaPontoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private BatidaPontoRepository batidaRepository;

	public BatidaPonto create(BatidaPontoDTO dadosBatidaDto) {

		Optional<Aluno> opAluno = alunoRepository.findById(dadosBatidaDto.getIdUsuario());
		if (opAluno.isEmpty()) {
			return null;
		}
		BatidaPonto dadosBatida = new BatidaPonto();
		dadosBatida.setTipoBatida(dadosBatidaDto.getTipoBatida());
		dadosBatida.setResponsavel(opAluno.get());
		dadosBatida.setDataHoraBatida(LocalDateTime.now());

		return batidaRepository.save(dadosBatida);
	}

	public BatidaDetalhadaDTO getBatidasByAluno(Long idAluno) {
		Aluno dadosAluno = new Aluno();
		dadosAluno.setId(idAluno);
		List<BatidaDetalhada> listaDetalhes = new ArrayList<BatidaDetalhada>();
		List<BatidaPonto> dadosBatidas = batidaRepository.findAllByResponsavel(dadosAluno);
		if(dadosBatidas.isEmpty()) {
			return null;
		}
		dadosBatidas.forEach(batida -> listaDetalhes.add(
				new BatidaDetalhada(batida.getTipoBatida() == 1 ? "Entrada" : "Saída", batida.getDataHoraBatida())));

		BatidaDetalhadaDTO dadosBatidaDetalhada = new BatidaDetalhadaDTO();
		dadosBatidaDetalhada.setNomeUsuario(dadosBatidas.get(1).getResponsavel().getNomeCompleto());
		dadosBatidaDetalhada.setBatidas(listaDetalhes);
		dadosBatidaDetalhada.setHorasTrabalhadas(getHorasTrabalhadas(listaDetalhes));
		return dadosBatidaDetalhada;
	}

	public String getHorasTrabalhadas(List<BatidaDetalhada> listaDetalhes) {

		long millis = 0;
		for (int i = 0; i < listaDetalhes.size(); i++) {
			if (listaDetalhes.get(i).getTipoBatida().equalsIgnoreCase("entrada")) {
				if (listaDetalhes.get(i + 1).getTipoBatida().equalsIgnoreCase("saída")) {
					Duration dur = Duration.between(listaDetalhes.get(i).dataHoraBatidaLocalDateTime(),
							listaDetalhes.get(i + 1).dataHoraBatidaLocalDateTime());
					millis = millis + dur.toMillis();
				}
			}
		}
		return String.format("%02d:%02d:%02d horas", TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
				TimeUnit.MILLISECONDS.toSeconds(millis)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
	}

}
