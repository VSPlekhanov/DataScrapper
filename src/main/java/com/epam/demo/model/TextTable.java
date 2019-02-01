package com.epam.demo.model;

import com.epam.demo.utils.Constants;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class presents text table as an output DTO
 * Sentences stores and presents out of the table due to huge size, that affects the view
 */
public class TextTable implements Table {
    private final Map<String, Map<String, String>> table = new HashMap<>();
    private final Map<String, String> sentences = new HashMap<>();

    @Override
    public void write(final String textName, final String column, final String value) {
        if (!table.containsKey(textName)) {
            table.put(textName, new HashMap<>());
        }
        table.get(textName).put(column, value);
    }

    @Override
    public void write(final String textName, final String column, final List<String> value) {
        sentences.put(textName, String.join("\n", value));
    }

    @Override
    public void write(final String textName, final String column, final int value) {
        write(textName, column, String.valueOf(value));
    }

    @Override
    public void write(final String textName, final String column, final Map<String, Long> value) {
        write(textName,
              column,
              value.entrySet()
                   .stream()
                   .map(stringLongEntry -> stringLongEntry.getKey() + " : " + stringLongEntry.getValue().toString())
                   .collect(
                           Collectors.joining(", ")));
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        if (!table.isEmpty()) {
            out.append(
                    "\n\n=======================================================================================================================================");

            int maxLength = table.keySet().stream().max(Comparator.comparingInt(String::length)).get().length();
            String prefix =
                    "\nFile name" + IntStream.range(1, maxLength / 4).mapToObj(i -> "\t").collect(Collectors.joining());
            String delimeter = "\t\t\t\t";

            out.append(prefix).append(String.join(delimeter, table.values().iterator().next().keySet())).append("\n");
            for (final Map.Entry<String, Map<String, String>> stringMapEntry : table.entrySet()) {
                out.append(stringMapEntry.getKey())
                   .append(" -  ")
                   .append(String.join(delimeter + delimeter,
                                       stringMapEntry.getValue().values()))
                   .append("\n");
            }
        }
        if (!sentences.isEmpty()) {
            out.append("\n").append(Constants.SENTENCES_WITH_WORDS).append(":");
            for (final Map.Entry<String, String> stringStringEntry : sentences.entrySet()) {
                out.append("\n\n").append(stringStringEntry.getKey()).append(":\n\n");
                out.append(stringStringEntry.getValue());
            }
        }
        return out.toString();
    }
}
