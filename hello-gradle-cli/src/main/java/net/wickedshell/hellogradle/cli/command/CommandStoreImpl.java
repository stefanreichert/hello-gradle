package net.wickedshell.hellogradle.cli.command;

/**
 * Created by stefan on 04/04/16.
 */
public class CommandStoreImpl implements CommandStore {

    public Command lookup(String commandString) {
        return CommandEnum.valueOf(commandString.toUpperCase());
    }
}
