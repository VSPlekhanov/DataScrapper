package output;

public class ConsoleOutput implements Output{

    @Override
    public void write(final String string) {
        System.out.println(string);
    }
}
