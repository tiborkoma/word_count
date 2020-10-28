package wordcount;

class OutputWriter {

    void printAnalysis(TextAnalysis textAnalysis) {
        System.out.printf("Number of words: %d, unique: %d%n",
                textAnalysis.wordCount(),
                textAnalysis.uniqueWordCount()
        );
    }
}
