package com.cepedi.aeroporto.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cepedi.aeroporto.model.ModeloAeronave;

public interface ModeloAeronavelRepository extends JpaRepository<ModeloAeronave,Integer> {

	ArrayList<ModeloAeronave> findByFabricante(String fabricante);
	ArrayList<ModeloAeronave> findByNome(String nome);
	ArrayList<ModeloAeronave> findByNomeAndFabricante(String nome, String Fabricante);
}
