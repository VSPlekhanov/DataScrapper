package com.epam.demo.input;

import com.epam.demo.model.Text;

import java.util.List;

public interface InputData {

    List<Text> getTexts();

    List<String> getCommands();

    List<String> getWords();
}
