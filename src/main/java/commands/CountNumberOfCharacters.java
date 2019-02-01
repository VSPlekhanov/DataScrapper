package commands;

import input.InputData;
import model.Table;
import model.Text;
import utils.Constants;

public class CountNumberOfCharacters implements Runnable {
    private final InputData inputData;
    private final Table table;

    public CountNumberOfCharacters(final InputData inputData, final Table table) {
        this.inputData = inputData;
        this.table = table;
    }

    @Override
    public void run() {
        for (final Text text : inputData.getTexts()) {
            table.write(text.getName(), Constants.NUMBER_OF_CHARACTERS, text.getValue().length());
        }
    }
}
