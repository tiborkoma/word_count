package wordcount;

import java.util.Collection;
import java.util.function.Function;

public class Application {

    private final InputReader consoleInputReader = new ConsoleInputReader();
    private final Function<String, Collection<String>> wordSplitter = new WordSplitter();
    private final TextAnalysisService textAnalysisService = new TextAnalysisService(wordSplitter);

    public void run() {
        System.out.print("Enter text: ");

        var textAnalysis = textAnalysisService.analyze(consoleInputReader);

        System.out.println("Number of words: " + textAnalysis.wordCount());
    }

    public static void main(String[] args) {
        new Application().run();
    }
}
