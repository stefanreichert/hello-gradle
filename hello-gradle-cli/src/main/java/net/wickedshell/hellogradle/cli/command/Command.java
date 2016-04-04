package net.wickedshell.hellogradle.cli.command;

import java.io.PrintStream;

/**
 * Created by stefan on 04/04/16.
 */
public interface Command {

    String name();

    boolean execute(PrintStream writer) throws Exception;
}
