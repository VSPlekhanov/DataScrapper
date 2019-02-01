package com.epam.demo.commands;

/**
 * Fabric to get the command instance by name
 */
public interface CommandFabric {
    /**
     *
     * @param command - command name
     * @return - command instance which is implements Runnable
     * @throws com.epam.demo.exceptions.CommandFabricException
     * if there are no words with '-w' command or if command if unknown
     */
    Runnable getCommand(String command);

}
