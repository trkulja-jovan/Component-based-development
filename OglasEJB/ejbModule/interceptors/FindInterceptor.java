package interceptors;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import beans.ReviewNumberBeanRemote;

public class FindInterceptor {
	
	private @EJB ReviewNumberBeanRemote bean;
	
	@AroundInvoke
	public Object interceptFindAdv(InvocationContext ctx) throws Exception {
		
		var params = ctx.getParameters();
		var key = ((Integer)params[0]).intValue();
		var currValue = bean.getMapReviews().getOrDefault(key, 0);
		
		bean.updateMap(key, ++currValue);
		return ctx.proceed();
	}
}
