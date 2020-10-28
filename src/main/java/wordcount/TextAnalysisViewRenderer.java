package wordcount;

import java.util.List;

class TextAnalysisViewRenderer {

    void render(TextAnalysisViewModel textAnalysisView) {
        System.out.printf("Number of words: %d, unique: %d; average word length: %.2f characters",
                textAnalysisView.wordCount(),
                textAnalysisView.uniqueWordCount(),
                textAnalysisView.averageWordLength()
        );

        if (textAnalysisView.printIndex()) {
            System.out.println("Index:");
            textAnalysisView.countedWordsIndex().forEach(System.out::println);
        }
    }
}

record TextAnalysisViewModel(
        int wordCount,
        int uniqueWordCount,
        double averageWordLength,
        List<String> countedWordsIndex,
        boolean printIndex
) {
    public TextAnalysisViewModel(TextAnalysis textAnalysis, boolean printIndex) {
        this(
                textAnalysis.wordCount(),
                textAnalysis.uniqueWordCount(),
                textAnalysis.averageWordLength(),
                textAnalysis.countedWordsIndex(),
                printIndex
        );
    }
}
