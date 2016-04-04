package net.wickedshell.hellogradle.cli.command;

/**
 * Created by stefan on 04/04/16.
 */
public interface CommandStore {

    public Command lookup(String commandString);
}
