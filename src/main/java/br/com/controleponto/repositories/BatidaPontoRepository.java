package br.com.controleponto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleponto.models.Aluno;
import br.com.controleponto.models.BatidaPonto;

public interface BatidaPontoRepository extends JpaRepository<BatidaPonto, Long>{
	List<BatidaPonto> findAllByResponsavel(Aluno dadosAluno);
}
