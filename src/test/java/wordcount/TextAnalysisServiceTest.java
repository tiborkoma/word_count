package wordcount;

import org.junit.Test;

import java.util.*;

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

        assertEquals(result, new TextAnalysis(0, 0, 0d));
    }

    @Test
    public void shouldReturnCorrectAnalysisForListOfWords() {
        textAnalysisService = new TextAnalysisService(input -> SPLITTED_WORDS, STOP_WORDS);
        var result = textAnalysisService.analyze(() -> null);

        assertEquals(result, new TextAnalysis(5, 3, 1d));
    }

    @Test
    public void shouldReturnCorrectAnalysisForListOfWordsSimpleAverage() {
        textAnalysisService = new TextAnalysisService(input -> listOf("1", "12", "123", "1234"), STOP_WORDS);
        var result = textAnalysisService.analyze(() -> null);

        assertEquals(result, new TextAnalysis(4, 4, 2.5d));
    }
}