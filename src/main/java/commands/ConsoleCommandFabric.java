package commands;

import input.InputData;
import model.Table;

public class ConsoleCommandFabric implements CommandFabric{
    private final InputData inputData;
    private final Table table;

    public ConsoleCommandFabric(final InputData inputData, final Table table) {
        this.inputData = inputData;
        this.table = table;
    }

    @Override
    public Runnable getCommand(final String command) {
        switch (command){
            case "-w": return new CountNumberOfWordsOccurrence(inputData, table);
            case "-c": return new CountNumberOfCharacters(inputData, table);
            // TODO: 01.02.19 add own exception
            default: throw new RuntimeException("unsupported command!");
        }
    }
}
