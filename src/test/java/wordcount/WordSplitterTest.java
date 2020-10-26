package wordcount;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordSplitterTest {

    WordSplitter wordSplitter = new WordSplitter();

    @Test
    public void shouldSplitStretchesOfLetters() {
        var expected = Arrays.asList("Mary", "had", "a", "little", "lamb");
        var actual = wordSplitter.split("Mary had a little lamb");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyListForEmptyInput() {
        var actual = wordSplitter.split("");
        assertTrue(actual.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListForBlankInput() {
        var actual = wordSplitter.split(" \t");
        assertTrue(actual.isEmpty());
    }
}