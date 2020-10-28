package wordcount;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    private Utils() {
    }

    @SafeVarargs
    public static <T> List<T> listOf(T... args) {
        return Stream.of(args).collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> Set<T> setOf(T... args) {
        return Stream.of(args).collect(Collectors.toSet());
    }
}
