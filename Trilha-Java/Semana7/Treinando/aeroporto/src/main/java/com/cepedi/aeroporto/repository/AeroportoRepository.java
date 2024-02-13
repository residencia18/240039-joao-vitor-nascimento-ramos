package com.cepedi.aeroporto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cepedi.aeroporto.model.Aeroporto;


public interface AeroportoRepository extends JpaRepository<Aeroporto,Integer> {

}
