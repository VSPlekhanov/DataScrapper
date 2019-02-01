package com.epam.demo.output;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConsoleOutput implements Output{
    private Logger logger = LogManager.getLogger(ConsoleOutput.class);

    @Override
    public void write(final String string) {
        logger.debug(string);
    }
}
