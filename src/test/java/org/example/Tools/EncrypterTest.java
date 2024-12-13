package org.example.Tools;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Exceptions.InputFileNotFoundException;
import org.example.Exceptions.OutputFIleNotFoundException;
import org.junit.jupiter.api.Test;

class EncrypterTest {

    @Test
    void shouldProperlyCreateEncrypter() {
        Encrypter encrypter = new Encrypter("src/main/resources/dane.txt", "src/main/resources/encoded.txt");
        assertNotNull(encrypter);
    }

    @Test
    void shouldThrowExceptionWhenNoInputPath() {
        assertThrows(InputFileNotFoundException.class, () -> new Encrypter("", "src/main/resources/encoded.txt"));
    }

    @Test
    void shouldThrowExceptionWhenNoOutputPath() {
        assertThrows(OutputFIleNotFoundException.class, () -> new Encrypter("src/main/resources/dane.txt", ""));
    }

    @Test
    void shouldThrowExceptionWhenInputFileIsEmpty() {
        assertThrows(
                InputFileNotFoundException.class,
                () -> new Encrypter("src/test/java/utils/empty.txt", "src/main/resources/encoded.txt"));
    }
}
