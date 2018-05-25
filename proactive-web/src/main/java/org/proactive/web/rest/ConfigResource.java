package org.proactive.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigResource {
  
  private final Logger log = LoggerFactory.getLogger(ConfigResource.class);

}
