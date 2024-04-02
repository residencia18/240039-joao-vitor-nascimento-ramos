package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ConverterDados {

	public static <T> T obterDados(String json, Class<T> classe) {
		try {
			ObjectMapper MAPPER = new ObjectMapper();
			return MAPPER.readValue(json, classe);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
