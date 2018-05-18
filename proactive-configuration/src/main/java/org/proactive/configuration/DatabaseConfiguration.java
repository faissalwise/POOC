package org.proactive.configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("org.proactive.myapp.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {

	private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	private final Environment env;

	public DatabaseConfiguration(Environment env) {
		this.env = env;
	}

	/**
	 * Open the TCP port for the H2 database, so it is available remotely.
	 *
	 * @return the H2 database TCP server
	 * @throws SQLException
	 *             if the server failed to start
	 */
	@Bean(initMethod = "start", destroyMethod = "stop")
	@Profile(ProActiveConstants.PROFILE_DEVELOPMENT)
	public Object h2TCPServer() throws SQLException {
		try {
			// We don't want to include H2 when we are packaging for the "prod" profile and
			// won't
			// actually need it, so we have to load / invoke things at runtime through
			// reflection.
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Class<?> serverClass = Class.forName("org.h2.tools.Server", true, loader);
			Method createServer = serverClass.getMethod("createTcpServer", String[].class);
			return createServer.invoke(null, new Object[] { new String[] { "-tcp", "-tcpAllowOthers" } });

		} catch (ClassNotFoundException | LinkageError e) {
			throw new RuntimeException("Failed to load and initialize org.h2.tools.Server", e);

		} catch (SecurityException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to get method org.h2.tools.Server.createTcpServer()", e);

		} catch (IllegalAccessException | IllegalArgumentException e) {
			throw new RuntimeException("Failed to invoke org.h2.tools.Server.createTcpServer()", e);

		} catch (InvocationTargetException e) {
			Throwable t = e.getTargetException();
			if (t instanceof SQLException) {
				throw (SQLException) t;
			}
			throw new RuntimeException("Unchecked exception in org.h2.tools.Server.createTcpServer()", t);
		}
	}

}
