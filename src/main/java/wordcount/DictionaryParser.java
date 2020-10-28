package wordcount;

import java.util.Set;
import java.util.stream.Collectors;

class DictionaryParser {

    private final WordSplitter wordSplitter;

    public DictionaryParser(WordSplitter wordSplitter) {
        this.wordSplitter = wordSplitter;
    }

    public Set<String> parse(InputReader inputReader) {
        return wordSplitter.apply(inputReader.readInput()).stream().collect(Collectors.toSet());
    }
}
