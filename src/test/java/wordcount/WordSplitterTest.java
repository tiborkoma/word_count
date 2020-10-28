package wordcount;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static wordcount.Utils.listOf;

public class WordSplitterTest {

    WordSplitter wordSplitter = new WordSplitter();

    @Test
    public void shouldSplitStretchesOfLetters() {
        var expected = listOf("Mary", "had", "a", "little", "lamb");
        var actual = wordSplitter.apply("Mary had a little lamb");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSplitStretchesOfLettersWithHyphens() {
        var expected = listOf("Humpty", "Dumpty", "sat", "on", "a", "wall", "Humpty", "Dumpty", "had", "a", "great", "fall");
        var actual = wordSplitter.apply("Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyListForEmptyInput() {
        var actual = wordSplitter.apply("");
        assertTrue(actual.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListForBlankInput() {
        var actual = wordSplitter.apply(" \t");
        assertTrue(actual.isEmpty());
    }
}