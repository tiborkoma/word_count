package wordcount;

class OutputWriter {

    void printAnalysis(TextAnalysis textAnalysis) {
        System.out.printf("Number of words: %d, unique: %d; average word length: %.2f characters",
                textAnalysis.wordCount(),
                textAnalysis.uniqueWordCount(),
                textAnalysis.averageWordLength()
        );
    }
}
