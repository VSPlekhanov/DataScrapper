package com.epam.demo.input;

import com.epam.demo.model.Text;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandLineDataTest {

    @Test
    public void commandLineDataTest_validArguments_shouldPass() {
        String[] args =
                {"/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text1.txt,/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text2.txt",
                        "test,ab", "-w", "-c"};
        CommandLineData commandLineData = new CommandLineData(args);

        Text text1 = new Text("/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text1.txt", "abracadabra");
        Text text2 = new Text("/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text2.txt", "test test test test");
        List<Text> texts = Arrays.asList(text1, text2);
        Assert.assertEquals(texts, commandLineData.getTexts());

        List<String> words = Arrays.asList("test", "ab");
        Assert.assertEquals(words, commandLineData.getWords());

        List<String> commands = Arrays.asList("-w", "-c");
        Assert.assertEquals(commands, commandLineData.getCommands());
    }

    @Test
    public void commandLineDataTest_oneCommand_shouldPass() {
        String[] args =
                {"/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text1.txt,/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text2.txt",
                        "test,ab", "-w"};
        CommandLineData commandLineData = new CommandLineData(args);

        Text text1 = new Text("/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text1.txt", "abracadabra");
        Text text2 = new Text("/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text2.txt", "test test test test");
        List<Text> texts = Arrays.asList(text1, text2);
        Assert.assertEquals(texts, commandLineData.getTexts());

        List<String> words = Arrays.asList("test", "ab");
        Assert.assertEquals(words, commandLineData.getWords());

        List<String> commands = Collections.singletonList("-w");
        Assert.assertEquals(commands, commandLineData.getCommands());
    }

    @Test
    public void commandLineDataTest_withoutWords_shouldPass() {
        String[] args =
                {"/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text1.txt,/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text2.txt",
                        "-w", "-c"};
        CommandLineData commandLineData = new CommandLineData(args);

        Text text1 = new Text("/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text1.txt", "abracadabra");
        Text text2 = new Text("/home/viacheslav/IdeaProjects/DataScrapper/src/test/resources/text2.txt", "test test test test");
        List<Text> texts = Arrays.asList(text1, text2);
        Assert.assertEquals(texts, commandLineData.getTexts());

        List<String> words = Collections.emptyList();
        Assert.assertEquals(words, commandLineData.getWords());

        List<String> commands = Arrays.asList("-w", "-c");
        Assert.assertEquals(commands, commandLineData.getCommands());
    }

}