package org.proactive.integration;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

public class FileMessageToJobRequest {

	private static Logger log = LoggerFactory.getLogger(FileMessageToJobRequest.class);

	private Job job;

	private String fileParameterName;

	public void setFileParameterName(String fileParameterName) {
		this.fileParameterName = fileParameterName;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Transformer
	public JobLaunchRequest toRequest(Message<File> message) {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

		jobParametersBuilder.addString(fileParameterName, message.getPayload().getAbsolutePath());

		log.info("Request Job  input file: fileParameterName {}, getAbsolutePath: {}", fileParameterName,
				message.getPayload().getAbsolutePath());
		return new JobLaunchRequest(job, jobParametersBuilder.toJobParameters());
	}
}
