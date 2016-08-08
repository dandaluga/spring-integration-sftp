package com.daluga.integration.sftp;

import com.daluga.integration.sftp.service.FTPService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@ImportResource("classpath:sftp-config.xml")
public class SpringIntegrationSftpApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringIntegrationSftpApplication.class);

	@Autowired
	private FTPService ftpService;

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(SpringIntegrationSftpApplication.class);
		application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
		SpringApplication.run(SpringIntegrationSftpApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		LOGGER.info("SFTP example has started...");

		List<String> firstLines = ftpService.getFile();
		firstLines.forEach(line -> LOGGER.info("File line = " + line));

		List<String> secondLines = ftpService.getFile();
		secondLines.forEach(line -> LOGGER.info("File line = " + line));

		LOGGER.info("SFTP example has ended...");
	}

}
