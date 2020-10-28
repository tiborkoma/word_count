package wordcount;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TextAnalysisServiceTest {

    private static final List<String> SPLITTED_WORDS = Arrays.asList("a", "a", "b", "c", "c", "c", "d", "e");
    private static final Set<String> STOP_WORDS = new HashSet(Arrays.asList("a", "b"));

    TextAnalysisService textAnalysisService;

    @Test
    public void shouldReturnCorrectAnalysisForNoWords() {
        textAnalysisService = new TextAnalysisService(input -> Collections.emptyList(), STOP_WORDS);
        var result = textAnalysisService.analyze(() -> null);

        assertEquals(result, new TextAnalysis(0, 0));
    }

    @Test
    public void shouldReturnCorrectAnalysisForListOfWords() {
        textAnalysisService = new TextAnalysisService(input -> SPLITTED_WORDS, STOP_WORDS);
        var result = textAnalysisService.analyze(() -> null);

        assertEquals(result, new TextAnalysis(5, 3));
    }
}