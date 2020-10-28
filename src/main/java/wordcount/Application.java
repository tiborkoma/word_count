package wordcount;

import java.net.URI;
import java.net.URISyntaxException;

public class Application {

    private final OptionsParser optionsParser;
    private final TextAnalysisViewRenderer textAnalysisViewRenderer;
    private final TextAnalysisService textAnalysisService;
    private final InputReaderFactory inputReaderFactory;

    public Application(
            OptionsParser optionsParser,
            TextAnalysisViewRenderer textAnalysisViewRenderer,
            TextAnalysisService textAnalysisService,
            InputReaderFactory inputReaderFactory
    ) {
        this.optionsParser = optionsParser;
        this.textAnalysisViewRenderer = textAnalysisViewRenderer;
        this.textAnalysisService = textAnalysisService;
        this.inputReaderFactory = inputReaderFactory;
    }

    public void run(String[] args) {
        var options = optionsParser.parse(args);
        var inputReader = inputReaderFactory.getInstance(options.inputFile().orElse(null));
        var textAnalysis = textAnalysisService.analyze(inputReader);
        textAnalysisViewRenderer.render(new TextAnalysisViewModel(textAnalysis, options.index()));
    }

    public static void main(String[] args) {
        new Application(
                CompositionRoot.getOptionsParser(),
                CompositionRoot.getTextAnalysisViewRenderer(),
                CompositionRoot.getTextAnalysisService(),
                CompositionRoot.getInputReaderFactory()
        ).run(args);
    }
}

class CompositionRoot {
    private CompositionRoot() {
    }

    private static final URI STOPWORDS_FILE_RESOURCE_URI = getStopwordsFileResourceUri();
    private static final WordSplitter WORD_SPLITTER = new WordSplitter();
    private static final StopWordsProvider STOP_WORDS_PROVIDER = new StopWordsProvider(
            new FileInputReader(STOPWORDS_FILE_RESOURCE_URI),
            WORD_SPLITTER
    );

    public static OptionsParser getOptionsParser() {
        return new OptionsParser();
    }

    public static TextAnalysisViewRenderer getTextAnalysisViewRenderer() {
        return new TextAnalysisViewRenderer();
    }

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
