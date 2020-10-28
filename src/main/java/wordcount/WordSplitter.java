package wordcount;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WordSplitter implements Function<String, Collection<String>> {

    public List<String> apply(String inputText) {
        if (Objects.isNull(inputText) || inputText.trim().isBlank()) {
            return Collections.emptyList();
        }

        return Stream.of(inputText.split("\\W")).filter(s -> !s.isBlank()).collect(Collectors.toList());
    }
}
