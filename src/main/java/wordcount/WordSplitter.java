package wordcount;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class WordSplitter {

    public List<String> split(String inputText) {
        if (Objects.isNull(inputText) || inputText.trim().isBlank()) {
            return Collections.emptyList();
        }

        return Arrays.asList(inputText.split("\\W"));
    }
}
