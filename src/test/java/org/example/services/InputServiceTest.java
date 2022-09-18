package org.example.services;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InputServiceTest {

    @Test
    public void testReadFileSuccess() {

        List<String> lines = Arrays.asList("apply 1");
        String path = "src/main/resources/test.txt";
        Path temporaryFile = Paths.get(path);

        try {
            Files.write(temporaryFile, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            Assert.fail("Failure due file creating error");
            throw new RuntimeException(e);
        }

        ArrayList<String> instructions = new ArrayList<>(InputService.readFile(path));
        File deleteFile = new File(path);

        if (!deleteFile.delete()){
            Assert.fail("Failure due file creating error");
        }

        assertEquals(1, instructions.size());
    }

    @Test(expected = IOError.class)
    public void testReadFileFailure() {
       InputService.readFile("src/main/resources/test.txt");
    }
}
