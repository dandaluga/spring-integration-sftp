package com.daluga.integration.sftp.service;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class FTPService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FTPService.class);

    @Autowired
    private SftpRemoteFileTemplate remoteFileTemplate;

    public List<String> getFile() {

        List<String> lines = remoteFileTemplate.execute(session -> {
            // -------------------------------------------------------
            // List the names in the folder.
            // -------------------------------------------------------
            Stream<String> names = Arrays.stream(session.listNames("/root"));
            names.forEach(name -> LOGGER.info("Name = " + name));

            // -------------------------------------------------------
            // See if the file exists.
            // -------------------------------------------------------
            boolean doesTheFileExist = session.exists("/root/dan.html");
            if (doesTheFileExist) {
                LOGGER.info("I found the file");
            } else {
                LOGGER.info("Aw crap, I could not find the file.");
            }

            // -------------------------------------------------------
            // Get the contents of the file. Be careful here, a large file
            // would consume a large slice of the heap since the entire
            // contents of the file is being stored in memory.
            // -------------------------------------------------------
            InputStream is = session.readRaw("/root/dan.html");
            List<String> fileContents = getStringFromInputStream(is);

            return fileContents;
        });

        //lines.forEach(line -> LOGGER.info("File line = " + line));

        return lines;
    }

    private List<String> getStringFromInputStream(InputStream is) {
        List<String> lines = null;

        try {
            lines = IOUtils.readLines(is, Charset.defaultCharset());
        } catch (IOException e) {
            LOGGER.error("Error attempting to get file contents.", e);
        }

        return lines;
    }

}
