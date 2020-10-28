package wordcount;

import java.util.List;
import java.util.Set;

interface TextAnalysisRenderer {
    void render(TextAnalysisViewModel textAnalysisViewModel);
}

class TextAnalysisViewRenderer implements TextAnalysisRenderer {

    public static final TextAnalysisRenderer DEFAULT_INDEX_RENDERER = textAnalysisViewModel -> {
        System.out.println("Index:");
        textAnalysisViewModel.countedWordsIndex().forEach(System.out::println);
    };

    private final TextAnalysisRenderer indexRenderer;

    public TextAnalysisViewRenderer(TextAnalysisRenderer indexRenderer) {
        this.indexRenderer = indexRenderer;
    }

    @Override
    public void render(TextAnalysisViewModel textAnalysisViewModel) {
        System.out.printf("Number of words: %d, unique: %d; average word length: %.2f characters\n",
                textAnalysisViewModel.wordCount(),
                textAnalysisViewModel.countedWordsIndex().size(),
                textAnalysisViewModel.averageWordLength()
        );

        if (textAnalysisViewModel.printIndex()) {
            indexRenderer.render(textAnalysisViewModel);
        }
    }
}

class DictionaryIndexRenderer implements TextAnalysisRenderer {

    private final Set<String> dictionary;

    public DictionaryIndexRenderer(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void render(TextAnalysisViewModel textAnalysisViewModel) {
        long unknown = textAnalysisViewModel.countedWordsIndex().stream()
                .filter(word -> !dictionary.contains(word))
                .count();

        System.out.printf("Index (unknown: %d):\n", unknown);
        textAnalysisViewModel.countedWordsIndex().stream()
                .map(word -> dictionary.contains(word) ? word : word + "*")
                .forEach(System.out::println);
    }
}

record TextAnalysisViewModel(
        int wordCount,
        double averageWordLength,
        List<String> countedWordsIndex,
        boolean printIndex
) {
    public TextAnalysisViewModel(TextAnalysis textAnalysis, boolean printIndex) {
        this(
                textAnalysis.wordCount(),
                textAnalysis.averageWordLength(),
                textAnalysis.countedWordsIndex(),
                printIndex
        );
    }
}
