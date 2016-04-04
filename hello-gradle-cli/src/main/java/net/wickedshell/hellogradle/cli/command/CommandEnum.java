package net.wickedshell.hellogradle.cli.command;

import net.wickedshell.hellogradle.HelloGradle;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Created by stefan on 01/04/16.
 */
enum CommandEnum implements Command {

    GREETINGS((writer) -> {
        new HelloGradle().printToStream(writer);
        return true;
    }),
    HELP((writer) -> {
        writer.println("available commands:");
        Arrays.stream(values()).forEach((command) -> writer.println(" " + command.name().toLowerCase()));
        return true;
    }),
    EXIT((writer) -> {
        writer.println("bye!");
        return false;
    });

    private CommandEnum(PrintingCallable callable) {
        this.callable = callable;
    }

    private final PrintingCallable callable;

    @Override
    public boolean execute(PrintStream writer) throws Exception {
        return callable.call(writer);
    }

    private static interface PrintingCallable {

        public boolean call(PrintStream writer);
    }

}
