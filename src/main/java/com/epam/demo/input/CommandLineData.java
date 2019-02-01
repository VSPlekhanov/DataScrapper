package com.epam.demo.input;

import com.epam.demo.exceptions.CommandLineParseException;
import com.epam.demo.model.Text;
import com.epam.demo.utils.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandLineData implements InputData {
    private static final int WORDS_INDEX = 1;
    private static final int FILE_PATHS_INDEX = 0;
    private final Logger logger = LogManager.getLogger(CommandLineData.class);
    private final List<Text> texts;
    private final List<String> commands;
    private final List<String> words;

    /**
     * This method parse data from command line arguments, read files and store this data.
     * If it is impossible to read the file, whis method would skip this file
     * If second command line argument starts with '-', the program would perceive this argument and all subsequent
     * as commands
     * @param args - command line arguments
     * @throws CommandLineParseException if there is not enough arguments (less than 2)
     */
    public CommandLineData(String[] args) {
        if (args.length < 2) {
            throw new CommandLineParseException(Constants.NOT_ENOUGH_CLA);
        }

        String[] filePaths = args[FILE_PATHS_INDEX].split(",");
        texts = new ArrayList<>();
        for (final String filePath : filePaths) {
            String value;
            try {
                value = new String(Files.readAllBytes(Paths.get(filePath)));
                texts.add(new Text(filePath, value));
            } catch (IOException e) {
                logger.error(Constants.FAILED_TO_OPEN_FILE + filePath);
            }
        }

        int firstCommandIndex = 1;

        if (args.length > 2 && !args[1].startsWith("-")) {
            firstCommandIndex = 2;
            words = Arrays.asList(args[WORDS_INDEX].split(","));
        } else {
            words = Collections.emptyList();
        }

        commands = new ArrayList<>();
        commands.addAll(Arrays.asList(args).subList(firstCommandIndex, args.length));
    }

    @Override
    public List<Text> getTexts() {
        return texts;
    }

    @Override
    public List<String> getCommands() {
        return commands;
    }

    @Override
    public List<String> getWords() {
        return words;
    }
}
