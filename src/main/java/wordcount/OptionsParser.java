package wordcount;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static wordcount.Utils.setOf;

class OptionsParser {

    private static final String INDEX = "-index";
    private static final String DICTIONARY = "-dictionary=";
    private static final Set<String> SWITCHES = setOf(INDEX, DICTIONARY);

    Options parse(String[] args) {
        var argsList = Stream.of(args).collect(Collectors.toList());
        var index = argsList.contains(INDEX);

        var inputFile = argsList.stream()
                .filter(option -> SWITCHES.stream().noneMatch(option::startsWith))
                .findFirst();

        var dictionary = argsList.stream()
                .filter(option -> option.startsWith(DICTIONARY))
                .map(dictionaryOption -> dictionaryOption.replace(DICTIONARY, ""))
                .filter(dictionaryFile -> !dictionaryFile.isBlank())
                .findFirst();

        return new Options(index, inputFile, dictionary);
    }
}

record Options(
        boolean index,
        Optional<String> inputFile,
        Optional<String> dictionary
) {}
