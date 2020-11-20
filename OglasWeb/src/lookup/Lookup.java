package lookup;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Lookup<T> {

	private static Context initialContext;

	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

	private static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			var properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			initialContext = new InitialContext(properties);
		}
		return initialContext;
	}

	private static <T,E> String getLookupName(Class<E> classType, Class<T> interfaceType) {
		
		final var appName = "OglasEAR";
		
		final var moduleName = "OglasEJB";
		final var distinctName = "";
		
		final var beanName = classType.getSimpleName();
		
		final var interfaceName = interfaceType.getName();
		
		var name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName + "?stateful";
		return name;
	}

	@SuppressWarnings("unchecked")
	public static <T, E> T doLookup(Class<E> classType, Class<T> interfaceType) {
		Context context = null;
		T bean = null;
		try {
			context = getInitialContext();
			var lookupName = getLookupName(classType, interfaceType);
			System.out.println("JNDI ime:   " + lookupName);

			bean = (T) context.lookup(lookupName);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
}