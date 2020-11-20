package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class StatefulInterceptor {
	
	@AroundInvoke
	public Object interceptStateful(InvocationContext ctx) throws Exception{
		
		System.err.println("Called stateful bean!");
		return ctx.proceed();
	}

}
