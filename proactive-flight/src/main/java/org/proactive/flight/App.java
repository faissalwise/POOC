package org.proactive.flight;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.proactive.configuration.DefaultProfileUtil;
import org.proactive.configuration.ProActiveApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
public class App extends ProActiveApp
{
	public App(Environment env) {
		super(env);
	}

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String args[]) throws UnknownHostException {
		 SpringApplication app = new SpringApplication(App.class);
	        DefaultProfileUtil.addDefaultProfile(app);
	        Environment env = app.run(args).getEnvironment();
	        String protocol = "http";
	        if (env.getProperty("server.ssl.key-store") != null) {
	            protocol = "https";
	        }
	        
	        log.info("\n----------------------------------------------------------\n\t" +
	                "Application '{}' is running! Access URLs:\n\t" +
	                "Local: \t\t{}://localhost:{}\n\t" +
	                "External: \t{}://{}:{}\n\t" +
	                "Profile(s): \t{}\n----------------------------------------------------------",
	            env.getProperty("spring.application.name"),
	            protocol,
	            env.getProperty("server.port"),
	            protocol,
	            InetAddress.getLocalHost().getHostAddress(),
	            env.getProperty("server.port"),
	            env.getActiveProfiles());
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}
