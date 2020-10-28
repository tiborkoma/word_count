package wordcount;

public class Application {

    private final InputReader consoleInputReader = new ConsoleInputReader();
    private final WordSplitter wordSplitter = new WordSplitter();
    private final StopWordsProvider stopWordsProvider = new StopWordsProvider(
            new ResourceFileReader("/stopwords.txt"),
            wordSplitter
    );

    private final TextAnalysisService textAnalysisService = new TextAnalysisService(
            wordSplitter,
            stopWordsProvider.get()
    );

    public void run() {
        System.out.print("Enter text: ");

        var textAnalysis = textAnalysisService.analyze(consoleInputReader);

        System.out.println("Number of words: " + textAnalysis.wordCount());
    }

    public static void main(String[] args) {
        new Application().run();
    }
}
