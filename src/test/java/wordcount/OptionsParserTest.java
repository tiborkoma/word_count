package wordcount;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class OptionsParserTest {

    OptionsParser optionsParser = new OptionsParser();

    @Test
    public void shouldParseIndexOption() {
        var result = optionsParser.parse(new String[] {"-index"});
        assertEquals(new Options(true, Optional.empty(), Optional.empty()), result);
    }

    @Test
    public void shouldParseFileOption() {
        var result = optionsParser.parse(new String[] {"filename"});
        assertEquals(new Options(false, Optional.of("filename"), Optional.empty()), result);
    }

    @Test
    public void shouldParseDictionaryOption() {
        var result = optionsParser.parse(new String[] {"-dictionary=dict.txt"});
        assertEquals(new Options(false, Optional.empty(), Optional.of("dict.txt")), result);
    }

    @Test
    public void shouldIgnoreEmptyDictionaryOption() {
        var result = optionsParser.parse(new String[] {"-dictionary="});
        assertEquals(new Options(false, Optional.empty(), Optional.empty()), result);
    }

    @Test
    public void shouldParseIndexAndFileOption() {
        var result = optionsParser.parse(new String[] {"filename", "-index"});
        assertEquals(new Options(true, Optional.of("filename"), Optional.empty()), result);
    }

    @Test
    public void shouldAcceptEmptyArgs() {
        var result = optionsParser.parse(new String[] {});
        assertEquals(new Options(false, Optional.empty(), Optional.empty()), result);
    }
}