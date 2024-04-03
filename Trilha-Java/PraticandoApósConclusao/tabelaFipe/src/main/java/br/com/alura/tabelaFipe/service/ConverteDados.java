package br.com.alura.tabelaFipe.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public abstract class ConverteDados {

	private static ObjectMapper MAPPER = new ObjectMapper();

	public static <T> T obterDados(String json, Class<T> classe) throws JsonMappingException, JsonProcessingException {
		return MAPPER.readValue(json, classe);
	}

	public static <T> List<T> obterLista(String json, Class<T> classe) throws JsonMappingException, JsonProcessingException {
		CollectionType lista = MAPPER.getTypeFactory().constructCollectionType(List.class, classe);
		return MAPPER.readValue(json, lista);
	}
	
	

}
