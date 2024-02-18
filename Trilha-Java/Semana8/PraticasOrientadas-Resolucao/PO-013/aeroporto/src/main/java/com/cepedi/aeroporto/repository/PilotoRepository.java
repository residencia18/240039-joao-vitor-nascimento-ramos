package com.cepedi.aeroporto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cepedi.aeroporto.model.Piloto;

public interface PilotoRepository extends JpaRepository<Piloto,Integer> {

	List<Piloto> findByNome(String nome);


}
