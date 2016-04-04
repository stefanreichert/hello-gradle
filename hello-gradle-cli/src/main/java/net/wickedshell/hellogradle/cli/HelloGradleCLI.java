package net.wickedshell.hellogradle.cli;

import net.wickedshell.hellogradle.cli.command.Command;
import net.wickedshell.hellogradle.cli.command.CommandStore;
import net.wickedshell.hellogradle.cli.command.CommandStoreImpl;

import java.io.*;

/**
 * Created by stefan on 01/04/16.
 */
public class HelloGradleCLI {

    private final BufferedReader reader;
    private final PrintStream writer;
    private final CommandStore commandStore;

    public HelloGradleCLI(BufferedReader reader, PrintStream writer, CommandStore commandStore) {
        this.reader = reader;
        this.writer = writer;
        this.commandStore = commandStore;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); PrintStream writer = System.out) {
            HelloGradleCLI cli = new HelloGradleCLI(reader, writer, new CommandStoreImpl());
            while (cli.readAndExecute()) {
                // read and execute
            }
        }
    }

    public boolean readAndExecute() throws IOException {
        boolean doContinue = false;
        writer.print(">");
        String line = reader.readLine();
        if (line != null) {
            try {
                Command command = commandStore.lookup(line);
                doContinue = command.execute(writer);
            } catch (IllegalArgumentException exception) {
                writer.printf("unknown command [%s]%n", line);
                doContinue = true;
            } catch (Throwable throwable) {
                writer.printf("failed to execute command [%s]:[%s]%n", line, throwable.getMessage());
                doContinue = true;
            }
        } else {
            writer.println("unexpectedly terminated");
        }
        writer.flush();
        return doContinue;
    }
}