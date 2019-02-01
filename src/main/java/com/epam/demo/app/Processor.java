package com.epam.demo.app;

import com.epam.demo.commands.CommandFabric;
import com.epam.demo.commands.ConsoleCommandFabric;
import com.epam.demo.exceptions.CommandFabricException;
import com.epam.demo.exceptions.CommandLineParseException;
import com.epam.demo.input.CommandLineData;
import com.epam.demo.input.InputData;
import com.epam.demo.model.Table;
import com.epam.demo.model.TextTable;
import com.epam.demo.output.ConsoleOutput;
import com.epam.demo.output.Output;
import com.epam.demo.utils.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Processor {
    private static Logger logger = LogManager.getLogger(Processor.class);

    public static void main(String[] args) {
        logger.debug("init");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            InputData inputData = new CommandLineData(args);

            Table table = new TextTable();
            CommandFabric commandFabric = new ConsoleCommandFabric(inputData, table);
            List<Runnable> commands = new ArrayList<>();
            for (final String command : inputData.getCommands()) {
                try {
                    commands.add(commandFabric.getCommand(command));
                } catch (CommandFabricException e) {
                    logger.error(e.getMessage());
                }
            }
            commands.forEach(executorService::submit);


            executorService.shutdown();

            executorService.awaitTermination(10, TimeUnit.SECONDS);

            Output output = new ConsoleOutput();
            output.write(table.toString());
        } catch (CommandLineParseException e) {
            logger.fatal(Constants.COMMAND_LINE_ERROR + " : " + e.getMessage());
        } catch (InterruptedException e) {
            logger.fatal(Constants.WAITING_LIMIT_EXCEEDED);
        }
    }
}
