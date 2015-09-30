package br.com.bruno.config;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class JacksonJsonConverter implements ContextResolver<ObjectMapper>, JsonConverter {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    static {
      MAPPER.setSerializationInclusion(Include.NON_EMPTY);
      MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
      //MAPPER.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }
 
    public JacksonJsonConverter() {
        System.out.println("Instantiate JacksonJsonConverter");
    }
     
    @Override
    public ObjectMapper getContext(Class<?> type) {
        System.out.println("JacksonJsonConverter.getContext() called with type: "+type);
        return MAPPER;
    }
    
    public String toJson(Object object) {
    	try {
			return MAPPER.writeValueAsString(object);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
    }

	@Override
	public Object toObject(String json, Class<?> clazz) {
		try {
			return MAPPER.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}