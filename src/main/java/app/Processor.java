package app;

import commands.CommandFabric;
import commands.ConsoleCommandFabric;
import input.CommandLineParser;
import input.InputData;
import model.Table;
import model.TextTable;
import output.ConsoleOutput;
import output.Output;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Processor {

    public static void main(String[] args) throws IOException {
        // TODO: 01.02.19 handle exception
        InputData inputData = new CommandLineParser(args);
        Table table = new TextTable();
        CommandFabric commandFabric = new ConsoleCommandFabric(inputData, table);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (final String command : inputData.getCommands()) {
            executorService.submit(commandFabric.getCommand(command));
        }


        executorService.shutdown();
        Output output = new ConsoleOutput();
        output.write(table.toString());


    }
}
