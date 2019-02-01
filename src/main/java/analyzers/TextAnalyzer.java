package analyzers;

import java.util.List;

public interface TextAnalyzer {

    void countWords(List<String> words);

    void countChars();

    void extractSentences(List<String> words);

    void setVerbose();

    void finish();
}
