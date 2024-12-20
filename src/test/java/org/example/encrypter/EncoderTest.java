package org.example.encrypter;

import static org.junit.jupiter.api.Assertions.*;

import org.example.exceptions.InputFileNotFoundException;
import org.junit.jupiter.api.Test;

class EncoderTest {

    @Test
    void shouldProperlyCreateEncoder() {
        Encoder encoder = new Encoder("src/main/resources/dane.txt", "src/main/resources/encoded.txt", 1);
        assertNotNull(encoder);
    }

    @Test
    void shouldThrowExceptionWhenNoInputPath() {
        assertThrows(InputFileNotFoundException.class, () -> new Encoder("", "src/main/resources/encoded.txt", 1));
    }

    // todo
    //    @Test
    //    void shouldThrowExceptionWhenNoOutputPath() {
    //        assertThrows(OutputFIleNotFoundException.class, () -> new Encoder("src/main/resources/dane.txt", "", 1));
    //    }

    //    @Test
    //    void shouldThrowExceptionSequencesCantBeLessThanZero() {
    //        assertThrows(SequencesCantBeLessThanZeroException.class, () -> new
    // Encrypter("src/main/resources/dane.txt", "src/main/resources/encoded.txt",1));
    //    }

    //    @Test
    //    void shouldThrowExceptionWhenInputFileIsEmpty() {
    //        assertThrows(
    //                InputFileNotFoundException.class,
    //                () -> new Encrypter("src/test/java/utils/empty.txt", "src/main/resources/encoded.txt"));
    //    }
}
