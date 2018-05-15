
package org.proactive.integration;

import org.proactive.configuration.ProActiveProperties;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * @author
 */
public class ExecutionsToMailTransformer {

	@Autowired
	@Lazy
	ProActiveProperties proActiveProperties;

	@Transformer
	public Message<String> transformExecutionsToMail(JobExecution jobExecution) {
		String result = "Execution has ended " + jobExecution.getStatus().toString();
		return MessageBuilder.withPayload(result).setHeader(MailHeaders.TO, proActiveProperties.getMail().getTo())
				.setHeader(MailHeaders.FROM, proActiveProperties.getMail().getFrom()).build();

	}

	public void setProActiveProperties(ProActiveProperties proActiveProperties) {
		this.proActiveProperties = proActiveProperties;
	}

}
