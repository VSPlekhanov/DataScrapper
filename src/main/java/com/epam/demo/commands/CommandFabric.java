package com.epam.demo.commands;

public interface CommandFabric {

    Runnable getCommand(String command);

}
