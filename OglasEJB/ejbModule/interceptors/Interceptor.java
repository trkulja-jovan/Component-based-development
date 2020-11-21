package interceptors;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import beans.ReviewNumberBeanRemote;

public class Interceptor {
	
	private @EJB ReviewNumberBeanRemote number;
	
	@AroundInvoke
	public Object interceptResponde(InvocationContext ctx) throws Exception {
			
		number.setRespond();
		return ctx.proceed();
	}
}
