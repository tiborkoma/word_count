package wordcount;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputReaderFactoryTest {

    @Test
    public void shouldReturnConsoleInputReaderWhenPathIsNull() {
        var inputReader = new InputReaderFactory().getInstance(null);
        assertTrue(inputReader instanceof ConsoleInputReader);
    }

    @Test
    public void shouldReturnConsoleInputReaderWhenPathIsNotNull() {
        var inputReader = new InputReaderFactory().getInstance("file");
        assertTrue(inputReader instanceof FileInputReader);
    }
}