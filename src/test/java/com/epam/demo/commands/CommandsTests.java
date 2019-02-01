package com.epam.demo.commands;

import com.epam.demo.input.InputData;
import com.epam.demo.model.Table;
import com.epam.demo.model.Text;
import org.junit.Test;
import org.mockito.Mockito;
import com.epam.demo.utils.Constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class CommandsTests {

    @Test
    public void countNumberOfCharactersTest_usualText_shouldPass() {
        InputData inputData = Mockito.mock(InputData.class);
        Text text1 = new Text("1", "12345");
        Text text2 = new Text("2", "1234");
        Mockito.when(inputData.getTexts()).thenReturn(Arrays.asList(text1, text2));

        Table table = Mockito.mock(Table.class);
        CountNumberOfCharacters countNumberOfCharacters = new CountNumberOfCharacters(inputData, table);
        countNumberOfCharacters.run();
        Mockito.verify(table).write(text1.getName(), Constants.NUMBER_OF_CHARACTERS, text1.getValue().length());
        Mockito.verify(table).write(text2.getName(), Constants.NUMBER_OF_CHARACTERS, text2.getValue().length());

    }

    @Test
    public void countNumberOfCharactersTest_EmptyFields_shouldPass() {
        InputData inputData = Mockito.mock(InputData.class);
        Text text1 = new Text("1", "");
        Text text2 = new Text("", "1234");
        Mockito.when(inputData.getTexts()).thenReturn(Arrays.asList(text1, text2));

        Table table = Mockito.mock(Table.class);
        CountNumberOfCharacters countNumberOfCharacters = new CountNumberOfCharacters(inputData, table);
        countNumberOfCharacters.run();
        Mockito.verify(table).write(text1.getName(), Constants.NUMBER_OF_CHARACTERS, text1.getValue().length());
        Mockito.verify(table).write(text2.getName(), Constants.NUMBER_OF_CHARACTERS, text2.getValue().length());

    }

    @Test
    public void countNumberOfWordsOccurrence_usualText_shouldPass() {
        InputData inputData = Mockito.mock(InputData.class);
        Text text1 = new Text("1", "word as word.");
        Text text2 = new Text("2", "Word=AsWord");
        Mockito.when(inputData.getTexts()).thenReturn(Arrays.asList(text1, text2));
        Mockito.when(inputData.getWords()).thenReturn(Arrays.asList("word", "as"));

        Table table = Mockito.mock(Table.class);
        CountNumberOfWordsOccurrence countNumberOfCharacters = new CountNumberOfWordsOccurrence(inputData, table);
        countNumberOfCharacters.run();

        Map<String, Long> map1 = new HashMap<>();
        map1.put("word", 2L);
        map1.put("as", 1L);

        Map<String, Long> map2 = new HashMap<>();
        map2.put("word", 1L);
        map2.put("as", 0L);

        Mockito.verify(table).write(text1.getName(), Constants.NUMBER_OF_WORDS, map1);
        Mockito.verify(table).write(text2.getName(), Constants.NUMBER_OF_WORDS, map2);

    }

    @Test
    public void ExtractSentencesTest_usualData_shouldPass() {
        InputData inputData = Mockito.mock(InputData.class);
        Text text1 = new Text("1", "word as word. not this one");
        Text text2 = new Text("2", "as it is. world is word.nopeAs wordNope");
        Mockito.when(inputData.getTexts()).thenReturn(Arrays.asList(text1, text2));
        Mockito.when(inputData.getWords()).thenReturn(Arrays.asList("word", "as"));

        Table table = Mockito.mock(Table.class);
        ExtractSentences extractSentences = new ExtractSentences(inputData, table);
        extractSentences.run();

        Mockito.verify(table).write(text1.getName(), Constants.SENTENCES_WITH_WORDS,
                                    Collections.singletonList("word as word"));
        Mockito.verify(table).write(text2.getName(), Constants.SENTENCES_WITH_WORDS, Arrays.asList("as it is", " world is word"));

    }

}