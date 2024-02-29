package com.cepedi.leilao.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cepedi.leilao.model.Lance;
import com.cepedi.leilao.model.Leilao;

public interface LanceRepository extends JpaRepository<Lance,Long>{
	
	ArrayList<Lance> findByLeilaoId(Long id);
	ArrayList<Lance> findByConcorrenteId(Long id);
	Lance findTopByLeilaoOrderByValorDesc(Leilao leilao);
	

}
