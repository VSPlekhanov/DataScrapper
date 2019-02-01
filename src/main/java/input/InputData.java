package input;

import model.Text;

import java.util.List;

public interface InputData {

    List<Text> getTexts();

    List<String> getCommands();

    List<String> getWords();
}
