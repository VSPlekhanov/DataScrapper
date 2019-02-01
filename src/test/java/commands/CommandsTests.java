package commands;

import input.InputData;
import model.Table;
import model.Text;
import org.junit.Test;
import org.mockito.Mockito;
import utils.Constants;

import java.util.Arrays;


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
//
//    @Test
//    public void countNumberOfCharactersTest_usualText_shouldPass() {
//        InputData inputData = Mockito.mock(InputData.class);
//        Text text1 = new Text("1", "12345");
//        Text text2 = new Text("2", "1234");
//        Mockito.when(inputData.getTexts()).thenReturn(Arrays.asList(text1, text2));
//
//        Table table = Mockito.mock(Table.class);
//        CountNumberOfCharacters countNumberOfCharacters = new CountNumberOfCharacters(inputData, table);
//        countNumberOfCharacters.run();
//        Mockito.verify(table).write(text1.getName(), Constants.NUMBER_OF_CHARACTERS, text1.getValue().length());
//        Mockito.verify(table).write(text2.getName(), Constants.NUMBER_OF_CHARACTERS, text2.getValue().length());
//
//    }

}