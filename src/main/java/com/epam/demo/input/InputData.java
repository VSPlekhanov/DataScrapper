package com.epam.demo.input;

import com.epam.demo.model.Text;

import java.util.List;

/**
 * Class that parse the input data
 */
public interface InputData {

    List<Text> getTexts();

    List<String> getCommands();

    List<String> getWords();
}
