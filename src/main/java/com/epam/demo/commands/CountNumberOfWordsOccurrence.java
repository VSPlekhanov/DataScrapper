package com.epam.demo.commands;

import com.epam.demo.input.InputData;
import com.epam.demo.model.Table;
import com.epam.demo.model.Text;
import com.epam.demo.utils.Constants;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountNumberOfWordsOccurrence implements Runnable {

    private InputData inputData;
    private Table table;

    public CountNumberOfWordsOccurrence(final InputData inputData, final Table table) {
        this.inputData = inputData;
        this.table = table;
    }

    @Override
    public void run() {
        for (final Text text : inputData.getTexts()) {
            Map<String, Long> wordsCount =
                    inputData.getWords().stream()
                             .collect(Collectors.toMap(Function.identity(),
                                                       word ->
                                                               Arrays.stream(text.getValue().split("\\W"))
                                                                     .filter(w -> w.equalsIgnoreCase(word))
                                                                     .count()));
            table.write(text.getName(), Constants.NUMBER_OF_WORDS, wordsCount);
        }
    }
}
