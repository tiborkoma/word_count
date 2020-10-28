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
        var countedWords = wordSplitter.andThen(wordFilter)
                .apply(inputReader.readInput());

        var countedWordsIndex = countedWords.stream().distinct()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());

        var averageWordLength = countedWords.stream().mapToInt(String::length).average().orElse(0);

        return new TextAnalysis(countedWords.size(), averageWordLength, countedWordsIndex);
    }
}
