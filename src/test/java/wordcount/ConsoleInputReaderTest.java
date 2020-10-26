package wordcount;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ConsoleInputReaderTest {

    ConsoleInputReader consoleInputReader;

    @Before
    public void setUp() throws Exception {
        consoleInputReader = new ConsoleInputReader();
    }

    @Test
    public void shouldReadDataFromSystemIn() {
        provideInput("some input");
        var consoleInput = consoleInputReader.readInput();
        assertEquals("some input", consoleInput);
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }
}