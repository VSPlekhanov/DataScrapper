package model;

public class Text {
    private final String name;
    private final String value;

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
