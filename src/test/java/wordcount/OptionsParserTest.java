package wordcount;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class OptionsParserTest {

    OptionsParser optionsParser = new OptionsParser();

    @Test
    public void shouldParseIndexOption() {
        var result = optionsParser.parse(new String[] {"-index"});
        assertEquals(new Options(true, Optional.empty()), result);
    }

    @Test
    public void shouldParseFileOption() {
        var result = optionsParser.parse(new String[] {"filename"});
        assertEquals(new Options(false, Optional.of("filename")), result);
    }

    @Test
    public void shouldParseIndexAndFileOption() {
        var result = optionsParser.parse(new String[] {"filename", "-index"});
        assertEquals(new Options(true, Optional.of("filename")), result);
    }

    @Test
    public void shouldAcceptEmptyArgs() {
        var result = optionsParser.parse(new String[] {});
        assertEquals(new Options(false, Optional.empty()), result);
    }
}