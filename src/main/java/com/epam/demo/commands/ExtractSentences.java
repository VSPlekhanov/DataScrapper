package com.epam.demo.commands;

import com.epam.demo.input.InputData;
import com.epam.demo.model.Table;
import com.epam.demo.model.Text;
import com.epam.demo.utils.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExtractSentences implements Runnable {
    private final InputData inputData;
    private final Table table;

    public ExtractSentences(final InputData inputData, final Table table) {
        this.inputData = inputData;
        this.table = table;
    }

    private boolean containsWord(String sentence, String word) {
        return sentence.toLowerCase().matches("(.*\\W+)*(" + word + ")(\\W+.*)*");
    }

    @Override
    public void run() {
        for (final Text text : inputData.getTexts()) {
            List<String> sentences = Arrays.stream(text.getValue().split("\\."))
                                           .filter(sentence -> inputData.getWords().stream()
                                                                        .anyMatch(word -> containsWord(sentence, word)))
                                           .collect(Collectors.toList());
            table.write(text.getName(), Constants.SENTENCES_WITH_WORDS, sentences);
        }
    }
}
