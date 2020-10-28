package wordcount;

import java.net.URI;
import java.net.URISyntaxException;

public class Application {

    private final OutputWriter outputWriter;
    private final TextAnalysisService textAnalysisService;
    private final InputReaderFactory inputReaderFactory;

    public Application(OutputWriter outputWriter, TextAnalysisService textAnalysisService, InputReaderFactory inputReaderFactory) {
        this.outputWriter = outputWriter;
        this.textAnalysisService = textAnalysisService;
        this.inputReaderFactory = inputReaderFactory;
    }

    public void run(String[] args) {
        var inputReader = inputReaderFactory.getInstance(args.length > 0 ? args[0] : null);
        var textAnalysis = textAnalysisService.analyze(inputReader);
        outputWriter.printAnalysis(textAnalysis);
    }

    public static void main(String[] args) {
        new Application(
                CompositionRoot.OUTPUT_WRITER,
                CompositionRoot.getTextAnalysisService(),
                CompositionRoot.getInputReaderFactory()
        ).run(args);
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

    public static final OutputWriter OUTPUT_WRITER = new OutputWriter();

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
