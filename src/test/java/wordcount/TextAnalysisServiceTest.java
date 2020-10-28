package wordcount;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class TextAnalysisServiceTest {

    TextAnalysisService textAnalysisService;

    @Test
    public void shouldReturnCorrectAnalysisForNoWords() {
        textAnalysisService = new TextAnalysisService(input -> Collections.emptyList());
        var result = textAnalysisService.analyze(() -> null);

        assertEquals(result, new TextAnalysis(0));
    }

    @Test
    public void shouldReturnCorrectAnalysisForListOfWords() {
        var splitResult = Arrays.asList("a", "b", "c");
        textAnalysisService = new TextAnalysisService(input -> splitResult);
        var result = textAnalysisService.analyze(() -> null);

        assertEquals(result, new TextAnalysis(splitResult.size()));
    }
}