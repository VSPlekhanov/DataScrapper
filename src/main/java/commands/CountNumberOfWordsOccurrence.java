package commands;

import input.InputData;
import model.Table;

public class CountNumberOfWordsOccurrence implements Runnable {

    private InputData inputData;
    private Table table;

    public CountNumberOfWordsOccurrence(final InputData inputData, final Table table) {
        this.inputData = inputData;
        this.table = table;
    }

    @Override
    public void run() {
    }
}
