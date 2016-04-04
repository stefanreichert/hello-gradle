package net.wickedshell.hellogradle.cli.command;

import net.wickedshell.hellogradle.HelloGradle;

import java.util.Arrays;

/**
 * Created by stefan on 01/04/16.
 */
public enum Command {

    GREETINGS(() -> new HelloGradle().printToStream(System.out)),
    HELP(() -> {
        System.out.println("available commands:");
        Arrays.stream(Command.values()).forEach((command) -> System.out.println(" " + command.name().toLowerCase()));
    }),
    EXIT(() -> {
        System.out.println("bye!");
        System.exit(0);
    });

    private Command(Runnable runnable) {
        this.runnable = runnable;
    }

    private Runnable runnable;

    public void execute() {
        runnable.run();
    }
}
