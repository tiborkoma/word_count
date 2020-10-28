package wordcount;

import java.util.Collection;
import java.util.function.Function;

public class TextAnalysisService {

    private final Function<String, Collection<String>> wordSplitter;

    public TextAnalysisService(Function<String, Collection<String>> wordSplitter) {
        this.wordSplitter = wordSplitter;
    }

    public TextAnalysis analyze(InputReader inputReader) {
        var inputText = inputReader.readInput();
        var inputTextWords = wordSplitter.apply(inputText);

        return new TextAnalysis(inputTextWords.size());
    }
}
