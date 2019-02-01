package com.epam.demo.model;

import java.util.Objects;

public class Text {
    private final String name;
    private final String value;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Text text = (Text) o;
        return Objects.equals(name, text.name) &&
               Objects.equals(value, text.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    public Text(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
