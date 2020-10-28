package wordcount;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static wordcount.Utils.listOf;
import static wordcount.Utils.setOf;

public class TextAnalysisServiceTest {

    private static final List<String> SPLITTED_WORDS = listOf("a", "a", "b", "c", "c", "c", "d", "e");
    private static final Set<String> STOP_WORDS = setOf("a", "b");

    TextAnalysisService textAnalysisService;

    @Test
    public void shouldReturnCorrectAnalysisForNoWords() {
        textAnalysisService = new TextAnalysisService(input -> Collections.emptyList(), STOP_WORDS);
        var result = textAnalysisService.analyze(() -> null);

        assertEquals(new TextAnalysis(0, 0, 0d, listOf()), result);
    }

    @Test
    public void shouldReturnCorrectAnalysisForListOfWords() {
        textAnalysisService = new TextAnalysisService(input -> SPLITTED_WORDS, STOP_WORDS);
        var result = textAnalysisService.analyze(() -> null);

        assertEquals(new TextAnalysis(5, 3, 1d, listOf("c", "d", "e")), result);
    }

    @Test
    public void shouldReturnCorrectAnalysisForListOfWordsSimpleAverage() {
        textAnalysisService = new TextAnalysisService(input -> listOf("Mary", "had", "a", "little", "lamb"), STOP_WORDS);
        var result = textAnalysisService.analyze(() -> null);

        assertEquals(4, result.wordCount());
        assertEquals(4, result.uniqueWordCount());
        assertEquals(4.25d, 0d, result.averageWordLength());
        assertEquals(listOf("had", "lamb", "little", "Mary"), result.countedWordsIndex());
    }
}