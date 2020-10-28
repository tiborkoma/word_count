package wordcount;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

class TextAnalysisService {

    private final Function<String, Collection<String>> wordSplitter;
    private final UnaryOperator<Collection<String>> wordFilter;

    public TextAnalysisService(Function<String, Collection<String>> wordSplitter, Set<String> stopWords) {
        this.wordSplitter = wordSplitter;
        this.wordFilter = words -> words.stream().filter(word -> !stopWords.contains(word)).collect(Collectors.toList());
    }

    public TextAnalysis analyze(InputReader inputReader) {
        var inputTextWords = wordSplitter.andThen(wordFilter)
                .apply(inputReader.readInput());

        return new TextAnalysis(inputTextWords.size());
    }
}
