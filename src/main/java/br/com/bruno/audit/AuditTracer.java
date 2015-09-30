package br.com.bruno.audit;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bruno.config.JsonConverter;

public class AuditTracer {

	@Inject
	private JsonConverter jsonConverter;
	
	@AroundInvoke
	public Object audit(InvocationContext context) throws Exception {
		
		System.out.println("------------- audit; getMethod=" + context.getMethod());
		System.out.println("getParameters=" + jsonConverter.toJson(context.getParameters()));
		System.out.println("getTarget=" + jsonConverter.toJson(context.getTarget()));
		System.out.println("getContextData=" + jsonConverter.toJson(context.getContextData()));
		
		Object result = context.proceed();
		
		//System.out.println("result=" + result);
		
		return result;
	}

}
