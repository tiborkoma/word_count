package wordcount;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

class WordSplitter implements Function<String, Collection<String>> {

    private static final Pattern WORD_PATTERN = Pattern.compile("[\\w\\d-]+", CASE_INSENSITIVE);

    public List<String> apply(String inputText) {
        if (Objects.isNull(inputText) || inputText.trim().isBlank()) {
            return Collections.emptyList();
        }

        var result = new ArrayList<String>();
        var matcher = WORD_PATTERN.matcher(inputText);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }
}
