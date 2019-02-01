package commands;

public interface CommandFabric {

    Runnable getCommand(String command);

}
