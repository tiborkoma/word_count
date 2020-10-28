package wordcount;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OptionsParser {

    private static final String INDEX = "-index";

    Options parse(String[] args) {
        var argsList = Stream.of(args).collect(Collectors.toList());
        var index = argsList.contains(INDEX);
        var inputFile = argsList.stream().filter(a -> !a.equalsIgnoreCase(INDEX)).findFirst();
        return new Options(index, inputFile);
    }
}

record Options(
        boolean index,
        Optional<String> inputFile
) {}
