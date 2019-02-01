package com.epam.demo.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextTable implements Table {
    private final Map<String, Map<String, String>> table = new HashMap<>();

    @Override
    public void write(final String textName, final String column, final String value) {
        if (!table.containsKey(textName)) {
            table.put(textName, new HashMap<>());
        }
        table.get(textName).put(column, value);
    }

    @Override
    public void write(final String textName, final String column, final List<String> value) {
        write(textName, column, String.join("\n", value));
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
        if (table.isEmpty()) {
            return "";
        }
        StringBuilder out = new StringBuilder();

        int maxLength = table.keySet().stream().max(Comparator.comparingInt(String::length)).get().length();
        String prefix = "\nFile name" + IntStream.range(1, maxLength / 4).mapToObj(i -> "\t").collect(Collectors.joining());
        String delimeter = "\t\t\t\t";

        out.append(prefix).append(String.join(delimeter, table.values().iterator().next().keySet())).append("\n");
        for (final Map.Entry<String, Map<String, String>> stringMapEntry : table.entrySet()) {
            out.append(stringMapEntry.getKey())
               .append(" -  ")
               .append(String.join(delimeter + delimeter,
                                   stringMapEntry.getValue().values()))
               .append("\n");
        }
        return out.toString();
    }
}
