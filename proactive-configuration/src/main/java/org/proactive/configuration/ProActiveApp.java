package org.proactive.configuration;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class,ProActiveProperties.class})
public class ProActiveApp {
	
	private static final Logger log = LoggerFactory.getLogger(ProActiveApp.class);

    protected final Environment env;

    public ProActiveApp(Environment env) {
        this.env = env;
    }
	
	/**
    * Initializes ProActiveApp.
    * <p>
    * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
    */
   @PostConstruct
   public void initApplication() {
       Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
       if (activeProfiles.contains(ProActiveConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(ProActiveConstants.SPRING_PROFILE_PRODUCTION)) {
           log.error("You have misconfigured your application! It should not run " +
               "with both the 'dev' and 'prod' profiles at the same time.");
       }
   }

}
