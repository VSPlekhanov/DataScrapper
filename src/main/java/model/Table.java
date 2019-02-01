package model;

import java.util.List;

public interface Table {

    void write(String textName, String column, String value);

    void write(String textName, String column, List<String> value);

    void write(String textName, String column, int value);
}
