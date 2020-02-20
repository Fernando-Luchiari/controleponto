package br.com.controleponto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleponto.models.Aluno;



public  interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
