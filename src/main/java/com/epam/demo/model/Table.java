package com.epam.demo.model;

import java.util.List;
import java.util.Map;

/**
 * An interface that presents output DTO
 * To present table in human readable format used <code>toString()</code> method
 */
public interface Table {

    void write(String textName, String column, String value);

    void write(String textName, String column, List<String> value);

    void write(String textName, String column, int value);

    void write(String textName, String column, Map<String, Long> value);
}
