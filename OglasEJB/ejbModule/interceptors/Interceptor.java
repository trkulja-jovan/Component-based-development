package interceptors;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import beans.ReviewNumberBeanRemote;

public class Interceptor {
	
	private @EJB ReviewNumberBeanRemote number;
	
	@AroundInvoke
	public Object interceptResponde(InvocationContext ctx) throws Exception {
			
		var params = ctx.getParameters();
		var key = ((Integer)params[0]).intValue();
		var currValue = number.getMapReviews().getOrDefault(key, 0);
		
		number.updateMap(key, ++currValue);
		return ctx.proceed();
	}
}
