package wordcount;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Set;

public class Application {

    private final OptionsParser optionsParser;
    private final DictionaryParser dictionaryParser;
    private final TextAnalysisViewRendererFactory textAnalysisViewRendererFactory;
    private final TextAnalysisService textAnalysisService;
    private final InputReaderFactory inputReaderFactory;

    public Application(
            OptionsParser optionsParser,
            DictionaryParser dictionaryParser,
            TextAnalysisViewRendererFactory textAnalysisViewRendererFactory,
            TextAnalysisService textAnalysisService,
            InputReaderFactory inputReaderFactory
    ) {
        this.optionsParser = optionsParser;
        this.dictionaryParser = dictionaryParser;
        this.textAnalysisViewRendererFactory = textAnalysisViewRendererFactory;
        this.textAnalysisService = textAnalysisService;
        this.inputReaderFactory = inputReaderFactory;
    }

    public void run(String[] args) {
        var options = optionsParser.parse(args);
        var inputReader = inputReaderFactory.getInstance(options.inputFile().orElse(null));
        var textAnalysis = textAnalysisService.analyze(inputReader);
        var dictionary = options.dictionary().map(this::makeDictionary).orElse(null);
        var textAnalysisRenderer = textAnalysisViewRendererFactory.getInstance(dictionary);
        textAnalysisRenderer.render(new TextAnalysisViewModel(textAnalysis, options.index()));
    }

    private Set<String> makeDictionary(String dictionaryPath) {
        var dictionaryInputReader = new FileInputReader(Paths.get(dictionaryPath));
        return dictionaryParser.parse(dictionaryInputReader);
    }

    public static void main(String[] args) {
        new Application(
                CompositionRoot.getOptionsParser(),
                CompositionRoot.getDictionaryProvider(),
                CompositionRoot.getTextAnalysisViewRendererFactory(),
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

    public static TextAnalysisViewRendererFactory getTextAnalysisViewRendererFactory() {
        return new TextAnalysisViewRendererFactory();
    }

    public static TextAnalysisService getTextAnalysisService() {
        return new TextAnalysisService(WORD_SPLITTER, STOP_WORDS_PROVIDER.get());
    }

    public static InputReaderFactory getInputReaderFactory() {
        return new InputReaderFactory();
    }

    public static DictionaryParser getDictionaryProvider() {
        return new DictionaryParser(WORD_SPLITTER);
    }

    private static URI getStopwordsFileResourceUri() {
        try {
            return FileInputReader.class.getResource("/stopwords.txt").toURI();
        } catch (URISyntaxException e) {
            throw new WordCountException(e);
        }
    }
}
