package wordcount;

import java.util.List;

record TextAnalysis(
        int wordCount,
        double averageWordLength,
        List<String> countedWordsIndex
) {
}
