package wordcount;

import java.util.*;
import java.util.function.Function;

import static wordcount.Utils.listOf;

class WordSplitter implements Function<String, Collection<String>> {

    public List<String> apply(String inputText) {
        if (Objects.isNull(inputText) || inputText.trim().isBlank()) {
            return Collections.emptyList();
        }

        return listOf(inputText.split("\\W"));
    }
}
