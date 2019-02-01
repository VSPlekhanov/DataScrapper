package com.epam.demo.commands;

import com.epam.demo.exceptions.CommandFabricException;
import com.epam.demo.input.InputData;
import com.epam.demo.model.Table;
import com.epam.demo.utils.Constants;

public class ConsoleCommandFabric implements CommandFabric {
    private final InputData inputData;
    private final Table table;

    public ConsoleCommandFabric(final InputData inputData, final Table table) {
        this.inputData = inputData;
        this.table = table;
    }

    @Override
    public Runnable getCommand(final String command) {
        switch (command) {
            case "-w":
                if (inputData.getWords().isEmpty()) {
                    throw new CommandFabricException(Constants.NO_WORDS_PROVIDED);
                }
                return new CountNumberOfWordsOccurrence(inputData, table);
            case "-c":
                return new CountNumberOfCharacters(inputData, table);
            default:
                throw new CommandFabricException(Constants.UNKNOWN_COMMAND + command);
        }
    }
}
