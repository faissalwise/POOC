package org.proactive.configuration;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Properties specific to ProActive.
 *
 */
@ConfigurationProperties(prefix = "proactive", ignoreUnknownFields = true)
public class ProActiveProperties {
	
	private final Logger log = LoggerFactory.getLogger(ProActiveProperties.class);
	
	private static final String SQL_FIND_CONFIG_PARAM = "SELECT * from CONFIG_PARAM";

	private final Async async = new Async();

	private final Http http = new Http();

	private final Cache cache = new Cache();

	private final Mail mail = new Mail();

	private final Security security = new Security();

	private final Swagger swagger = new Swagger();

	private final Metrics metrics = new Metrics();

	private final Logging logging = new Logging();

	private final Ftp ftp = new Ftp();

	private final CorsConfiguration cors = new CorsConfiguration();

	public Async getAsync() {
		return async;
	}

	public Http getHttp() {
		return http;
	}

	public Cache getCache() {
		return cache;
	}

	public Mail getMail() {
		return mail;
	}

	public Security getSecurity() {
		return security;
	}

	public Swagger getSwagger() {
		return swagger;
	}

	public Metrics getMetrics() {
		return metrics;
	}

	public Logging getLogging() {
		return logging;
	}

	public CorsConfiguration getCors() {
		return cors;
	}

	public Ftp getFtp() {
		return ftp;
	}

	public static class Async {

		private int corePoolSize = ProActiveDefault.Async.corePoolSize;

		private int maxPoolSize = ProActiveDefault.Async.maxPoolSize;

		private int queueCapacity = ProActiveDefault.Async.queueCapacity;

		public int getCorePoolSize() {
			return corePoolSize;
		}

		public void setCorePoolSize(int corePoolSize) {
			this.corePoolSize = corePoolSize;
		}

		public int getMaxPoolSize() {
			return maxPoolSize;
		}

		public void setMaxPoolSize(int maxPoolSize) {
			this.maxPoolSize = maxPoolSize;
		}

		public int getQueueCapacity() {
			return queueCapacity;
		}

		public void setQueueCapacity(int queueCapacity) {
			this.queueCapacity = queueCapacity;
		}
	}

	public static class Http {

		public enum Version {
			V_1_1, V_2_0
		}

		private final Cache cache = new Cache();

		/**
		 * HTTP version, must be "V_1_1" (for HTTP/1.1) or V_2_0 (for (HTTP/2)
		 */
		public Version version = ProActiveDefault.Http.version;

		public Cache getCache() {
			return cache;
		}

		public Version getVersion() {
			return version;
		}

		public void setVersion(Version version) {
			this.version = version;
		}

		public static class Cache {

			private int timeToLiveInDays = ProActiveDefault.Http.Cache.timeToLiveInDays;

			public int getTimeToLiveInDays() {
				return timeToLiveInDays;
			}

			public void setTimeToLiveInDays(int timeToLiveInDays) {
				this.timeToLiveInDays = timeToLiveInDays;
			}
		}
	}

	public static class Cache {

		private final Hazelcast hazelcast = new Hazelcast();

		private final Ehcache ehcache = new Ehcache();

		public Hazelcast getHazelcast() {
			return hazelcast;
		}

		public Ehcache getEhcache() {
			return ehcache;
		}

		public static class Hazelcast {

			private int timeToLiveSeconds = ProActiveDefault.Cache.Hazelcast.timeToLiveSeconds;

			private int backupCount = ProActiveDefault.Cache.Hazelcast.backupCount;

			private final ManagementCenter managementCenter = new ManagementCenter();

			public ManagementCenter getManagementCenter() {
				return managementCenter;
			}

			public static class ManagementCenter {

				private boolean enabled = ProActiveDefault.Cache.Hazelcast.ManagementCenter.enabled;

				private int updateInterval = ProActiveDefault.Cache.Hazelcast.ManagementCenter.updateInterval;

				private String url = ProActiveDefault.Cache.Hazelcast.ManagementCenter.url;

				public boolean isEnabled() {
					return enabled;
				}

				public void setEnabled(boolean enabled) {
					this.enabled = enabled;
				}

				public int getUpdateInterval() {
					return updateInterval;
				}

				public void setUpdateInterval(int updateInterval) {
					this.updateInterval = updateInterval;
				}

				public String getUrl() {
					return url;
				}

				public void setUrl(String url) {
					this.url = url;
				}

			}

			public int getTimeToLiveSeconds() {
				return timeToLiveSeconds;
			}

			public void setTimeToLiveSeconds(int timeToLiveSeconds) {
				this.timeToLiveSeconds = timeToLiveSeconds;
			}

			public int getBackupCount() {
				return backupCount;
			}

			public void setBackupCount(int backupCount) {
				this.backupCount = backupCount;
			}
		}

		public static class Ehcache {

			private int timeToLiveSeconds = ProActiveDefault.Cache.Ehcache.timeToLiveSeconds;

			private long maxEntries = ProActiveDefault.Cache.Ehcache.maxEntries;

			public int getTimeToLiveSeconds() {
				return timeToLiveSeconds;
			}

			public void setTimeToLiveSeconds(int timeToLiveSeconds) {
				this.timeToLiveSeconds = timeToLiveSeconds;
			}

			public long getMaxEntries() {
				return maxEntries;
			}

			public void setMaxEntries(long maxEntries) {
				this.maxEntries = maxEntries;
			}
		}

	}

	public static class Mail {

		private String from = ProActiveDefault.Mail.from;

		private String baseUrl = ProActiveDefault.Mail.baseUrl;

		private String to = ProActiveDefault.Mail.to;

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getBaseUrl() {
			return baseUrl;
		}

		public void setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

	}

	public static class Ftp {

		private String host = ProActiveDefault.Ftp.host;

		private int port = ProActiveDefault.Ftp.port;

		private String username = ProActiveDefault.Ftp.username;

		private String password = ProActiveDefault.Ftp.password;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}

	public static class Security {

		private final ClientAuthorization clientAuthorization = new ClientAuthorization();

		private final Authentication authentication = new Authentication();

		private final RememberMe rememberMe = new RememberMe();

		public ClientAuthorization getClientAuthorization() {
			return clientAuthorization;
		}

		public Authentication getAuthentication() {
			return authentication;
		}

		public RememberMe getRememberMe() {
			return rememberMe;
		}

		public static class ClientAuthorization {

			private String accessTokenUri = ProActiveDefault.Security.ClientAuthorization.accessTokenUri;

			private String tokenServiceId = ProActiveDefault.Security.ClientAuthorization.tokenServiceId;

			private String clientId = ProActiveDefault.Security.ClientAuthorization.clientId;

			private String clientSecret = ProActiveDefault.Security.ClientAuthorization.clientSecret;

			public String getAccessTokenUri() {
				return accessTokenUri;
			}

			public void setAccessTokenUri(String accessTokenUri) {
				this.accessTokenUri = accessTokenUri;
			}

			public String getTokenServiceId() {
				return tokenServiceId;
			}

			public void setTokenServiceId(String tokenServiceId) {
				this.tokenServiceId = tokenServiceId;
			}

			public String getClientId() {
				return clientId;
			}

			public void setClientId(String clientId) {
				this.clientId = clientId;
			}

			public String getClientSecret() {
				return clientSecret;
			}

			public void setClientSecret(String clientSecret) {
				this.clientSecret = clientSecret;
			}
		}

		public static class Authentication {

			private final Jwt jwt = new Jwt();

			public Jwt getJwt() {
				return jwt;
			}

			public static class Jwt {

				private String secret = ProActiveDefault.Security.Authentication.Jwt.secret;

				private long tokenValidityInSeconds = ProActiveDefault.Security.Authentication.Jwt.tokenValidityInSeconds;

				private long tokenValidityInSecondsForRememberMe = ProActiveDefault.Security.Authentication.Jwt.tokenValidityInSecondsForRememberMe;

				public String getSecret() {
					return secret;
				}

				public void setSecret(String secret) {
					this.secret = secret;
				}

				public long getTokenValidityInSeconds() {
					return tokenValidityInSeconds;
				}

				public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
					this.tokenValidityInSeconds = tokenValidityInSeconds;
				}

				public long getTokenValidityInSecondsForRememberMe() {
					return tokenValidityInSecondsForRememberMe;
				}

				public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
					this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
				}
			}
		}

		public static class RememberMe {

			@NotNull
			private String key = ProActiveDefault.Security.RememberMe.key;

			public String getKey() {
				return key;
			}

			public void setKey(String key) {
				this.key = key;
			}
		}
	}

	public static class Swagger {

		private String title = ProActiveDefault.Swagger.title;

		private String description = ProActiveDefault.Swagger.description;

		private String version = ProActiveDefault.Swagger.version;

		private String termsOfServiceUrl = ProActiveDefault.Swagger.termsOfServiceUrl;

		private String contactName = ProActiveDefault.Swagger.contactName;

		private String contactUrl = ProActiveDefault.Swagger.contactUrl;

		private String contactEmail = ProActiveDefault.Swagger.contactEmail;

		private String license = ProActiveDefault.Swagger.license;

		private String licenseUrl = ProActiveDefault.Swagger.licenseUrl;

		private String defaultIncludePattern = ProActiveDefault.Swagger.defaultIncludePattern;

		private String host = ProActiveDefault.Swagger.host;

		private String[] protocols = ProActiveDefault.Swagger.protocols;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getTermsOfServiceUrl() {
			return termsOfServiceUrl;
		}

		public void setTermsOfServiceUrl(String termsOfServiceUrl) {
			this.termsOfServiceUrl = termsOfServiceUrl;
		}

		public String getContactName() {
			return contactName;
		}

		public void setContactName(String contactName) {
			this.contactName = contactName;
		}

		public String getContactUrl() {
			return contactUrl;
		}

		public void setContactUrl(String contactUrl) {
			this.contactUrl = contactUrl;
		}

		public String getContactEmail() {
			return contactEmail;
		}

		public void setContactEmail(String contactEmail) {
			this.contactEmail = contactEmail;
		}

		public String getLicense() {
			return license;
		}

		public void setLicense(String license) {
			this.license = license;
		}

		public String getLicenseUrl() {
			return licenseUrl;
		}

		public void setLicenseUrl(String licenseUrl) {
			this.licenseUrl = licenseUrl;
		}

		public String getDefaultIncludePattern() {
			return defaultIncludePattern;
		}

		public void setDefaultIncludePattern(String defaultIncludePattern) {
			this.defaultIncludePattern = defaultIncludePattern;
		}

		public String getHost() {
			return host;
		}

		public void setHost(final String host) {
			this.host = host;
		}

		public String[] getProtocols() {
			return protocols;
		}

		public void setProtocols(final String[] protocols) {
			this.protocols = protocols;
		}
	}

	public static class Metrics {

		private final Jmx jmx = new Jmx();

		private final Graphite graphite = new Graphite();

		private final Prometheus prometheus = new Prometheus();

		private final Logs logs = new Logs();

		public Jmx getJmx() {
			return jmx;
		}

		public Graphite getGraphite() {
			return graphite;
		}

		public Prometheus getPrometheus() {
			return prometheus;
		}

		public Logs getLogs() {
			return logs;
		}

		public static class Jmx {

			private boolean enabled = ProActiveDefault.Metrics.Jmx.enabled;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}
		}

		public static class Graphite {

			private boolean enabled = ProActiveDefault.Metrics.Graphite.enabled;

			private String host = ProActiveDefault.Metrics.Graphite.host;

			private int port = ProActiveDefault.Metrics.Graphite.port;

			private String prefix = ProActiveDefault.Metrics.Graphite.prefix;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public String getHost() {
				return host;
			}

			public void setHost(String host) {
				this.host = host;
			}

			public int getPort() {
				return port;
			}

			public void setPort(int port) {
				this.port = port;
			}

			public String getPrefix() {
				return prefix;
			}

			public void setPrefix(String prefix) {
				this.prefix = prefix;
			}
		}

		public static class Prometheus {

			private boolean enabled = ProActiveDefault.Metrics.Prometheus.enabled;

			private String endpoint = ProActiveDefault.Metrics.Prometheus.endpoint;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public String getEndpoint() {
				return endpoint;
			}

			public void setEndpoint(String endpoint) {
				this.endpoint = endpoint;
			}
		}

		public static class Logs {

			private boolean enabled = ProActiveDefault.Metrics.Logs.enabled;

			private long reportFrequency = ProActiveDefault.Metrics.Logs.reportFrequency;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public long getReportFrequency() {
				return reportFrequency;
			}

			public void setReportFrequency(long reportFrequency) {
				this.reportFrequency = reportFrequency;
			}
		}
	}

	public static class Logging {

		private final Logstash logstash = new Logstash();

		public Logstash getLogstash() {
			return logstash;
		}

		public static class Logstash {

			private boolean enabled = ProActiveDefault.Logging.Logstash.enabled;

			private String host = ProActiveDefault.Logging.Logstash.host;

			private int port = ProActiveDefault.Logging.Logstash.port;

			private int queueSize = ProActiveDefault.Logging.Logstash.queueSize;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public String getHost() {
				return host;
			}

			public void setHost(String host) {
				this.host = host;
			}

			public int getPort() {
				return port;
			}

			public void setPort(int port) {
				this.port = port;
			}

			public int getQueueSize() {
				return queueSize;
			}

			public void setQueueSize(int queueSize) {
				this.queueSize = queueSize;
			}
		}

		private final SpectatorMetrics spectatorMetrics = new SpectatorMetrics();

		public SpectatorMetrics getSpectatorMetrics() {
			return spectatorMetrics;
		}

		public static class SpectatorMetrics {

			private boolean enabled = ProActiveDefault.Logging.SpectatorMetrics.enabled;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}
		}
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@PostConstruct
	public void initConfigPropertiesFromDatabase(){
	    log.debug("Get properties from DB using JDBC");
//	    List<Map<String, Object>> rows = getJdbcTemplate().queryForList(SQL_FIND_CONFIG_PARAM);
//	    Map it manually
	    
	}
	
}
