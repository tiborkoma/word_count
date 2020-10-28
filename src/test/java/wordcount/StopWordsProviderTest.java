package wordcount;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static wordcount.Utils.listOf;
import static wordcount.Utils.setOf;

public class StopWordsProviderTest {

    private static final List<String> NONUNIQUE_SPLIT_RESULT = listOf("a", "a", "b");

    @Test
    public void shouldReturnSetOfTheSplittingResult() {
        var stopWordsProvider = new StopWordsProvider(() -> null, a -> NONUNIQUE_SPLIT_RESULT);
        assertEquals(setOf("a", "b"), stopWordsProvider.get());
    }
}