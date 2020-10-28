package wordcount;

import java.net.URI;
import java.net.URISyntaxException;

public class Application {

    private final TextAnalysisService textAnalysisService = CompositionRoot.getTextAnalysisService();
    private final InputReaderFactory inputReaderFactory = CompositionRoot.getInputReaderFactory();

    public void run(String[] args) {
        System.out.print("Enter text: ");

        var inputReader = inputReaderFactory.getInstance(args.length > 0 ? args[0] : null);
        var textAnalysis = textAnalysisService.analyze(inputReader);

        System.out.printf("Number of words: %d, unique: %d%n",
                textAnalysis.wordCount(),
                textAnalysis.uniqueWordCount()
        );
    }

    public static void main(String[] args) {
        new Application().run(args);
    }
}

class CompositionRoot {
    private CompositionRoot() {
    }

    public static final URI STOPWORDS_FILE_RESOURCE_URI = getStopwordsFileResourceUri();
    public static final WordSplitter WORD_SPLITTER = new WordSplitter();
    public static final StopWordsProvider STOP_WORDS_PROVIDER = new StopWordsProvider(
            new FileInputReader(STOPWORDS_FILE_RESOURCE_URI),
            WORD_SPLITTER
    );

    public static TextAnalysisService getTextAnalysisService() {
        return new TextAnalysisService(WORD_SPLITTER, STOP_WORDS_PROVIDER.get());
    }

    public static InputReaderFactory getInputReaderFactory() {
        return new InputReaderFactory();
    }

    private static URI getStopwordsFileResourceUri() {
        try {
            return FileInputReader.class.getResource("/stopwords.txt").toURI();
        } catch (URISyntaxException e) {
            throw new WordCountException(e);
        }
    }
}
