package org.proactive.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.proactive.configuration.DefaultProfileUtil;
import org.proactive.configuration.ProActiveApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

public class WebApplication extends ProActiveApp {

  private static final Logger log = LoggerFactory.getLogger(WebApplication.class);

  public WebApplication(Environment env) {
    super(env);
  }

  /**
   * Main method, used to run the application.
   *
   * @param args
   *          the command line arguments
   * @throws UnknownHostException
   *           if the local host name could not be resolved into an address
   */
  public static void main(String[] args) throws UnknownHostException {
    SpringApplication app = new SpringApplication(WebApplication.class);
    DefaultProfileUtil.addDefaultProfile(app);
    Environment env = app.run(args).getEnvironment();
    String protocol = "http";
    if (env.getProperty("server.ssl.key-store") != null) {
      protocol = "https";
    }

    log.info("\n----------------------------------------------------------\n\t"
        + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t"
        + "External: \t{}://{}:{}\n\t" + "Profile(s): \t{}\n----------------------------------------------------------",
        env.getProperty("spring.application.name"), protocol, env.getProperty("server.port"), protocol,
        InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"), env.getActiveProfiles());
  }

}
