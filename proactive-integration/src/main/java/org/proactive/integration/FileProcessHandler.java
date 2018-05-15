package org.proactive.integration;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileProcessHandler {
	private static Logger log = LoggerFactory.getLogger(FileProcessHandler.class);

	public File handler(File input) {
		log.info("Processed input file: {}, size: {}", input.getAbsolutePath(), input.length());

		return input;
	}

}
