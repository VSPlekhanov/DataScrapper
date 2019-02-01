package input;

import model.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineParser implements InputData {

    private final List<Text> texts;
    private final List<String> commands;
    private final List<String> words;


    public CommandLineParser(String[] args) throws IOException {
        if (args.length < 2)
            throw new RuntimeException();
        // TODO: 01.02.19 handle this situation
        String[] filePaths = args[0].split(",");
        texts = new ArrayList<>();
        for (final String filePath : filePaths) {
            String value = new String(Files.readAllBytes(Paths.get(filePath)));
            texts.add(new Text(filePath, value));
        }

        words = Arrays.asList(args[1].split(","));

        commands = new ArrayList<>();
        commands.addAll(Arrays.asList(args).subList(2, args.length));
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
