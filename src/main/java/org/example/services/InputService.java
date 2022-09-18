package org.example.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


/**
 * The type Input service.
 */
public class InputService {

    private static final Logger LOGGER = LogManager.getLogger(InputService.class);

    /**
     * Read file by path and return list of rows.
     *
     * @param path the path to file
     * @return the list of rows
     */
    public static List<String> readFile(String path){

        try {

            byte[] encoded = Files.readAllBytes(Paths.get(path));
            String fileContent = new String(encoded, StandardCharsets.UTF_8);

            return Arrays.asList(fileContent.split("\n"));
            
        } catch (IOException e) {
            LOGGER.error("Error occured while reading file - ", e);
            throw new IOError(e);
        }
    }
}
