package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String toString() {
        if (table.isEmpty()) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        out.append("\t\t\t").append(String.join("\t", table.values().iterator().next().keySet()));
        for (final Map.Entry<String, Map<String, String>> stringMapEntry : table.entrySet()) {
            out.append(stringMapEntry.getKey()).append(" -  ").append(String.join(" ",
                                                                                  stringMapEntry.getValue().values()));
        }
        return out.toString();
    }
}
