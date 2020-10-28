package wordcount;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

class StopWordsProvider {

    private final InputReader inputReader;
    private final Function<String, Collection<String>> wordSplitter;

    public StopWordsProvider(InputReader inputReader, Function<String, Collection<String>> wordSplitter) {
        this.inputReader = inputReader;
        this.wordSplitter = wordSplitter;
    }

    public Set<String> get() {
        return wordSplitter
                .andThen(HashSet::new)
                .apply(inputReader.readInput());
    }
}
