package wordcount;

import java.util.Set;

class TextAnalysisViewRendererFactory {

    public TextAnalysisViewRenderer getInstance(Set<String> dictionary) {
        return dictionary == null
                ? new TextAnalysisViewRenderer(TextAnalysisViewRenderer.DEFAULT_INDEX_RENDERER)
                : new TextAnalysisViewRenderer(new DictionaryIndexRenderer(dictionary));

    }
}
