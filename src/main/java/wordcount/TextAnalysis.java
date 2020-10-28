package wordcount;

import java.util.List;

record TextAnalysis(
        int wordCount,
        int uniqueWordCount,
        double averageWordLength,
        List<String> countedWordsIndex
) {
}
