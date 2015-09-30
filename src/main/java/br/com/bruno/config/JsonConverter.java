package br.com.bruno.config;

public interface JsonConverter {

	String toJson(Object object);

	Object toObject(String json, Class<?> clazz);

}
