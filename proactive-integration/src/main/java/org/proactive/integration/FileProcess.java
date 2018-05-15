package org.proactive.integration;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FileProcess {public static void main(String[] args) {
    new ClassPathXmlApplicationContext("integration.xml","batch-config.xml");
    
    for (;;) {
        try {
            Thread.sleep(6000);
        }
        catch (Exception ex) {
        }
    }
}}
