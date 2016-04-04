package net.wickedshell.hellogradle.cli;

import net.wickedshell.hellogradle.cli.command.Command;

import java.io.*;

/**
 * Created by stefan on 01/04/16.
 */
public class HelloGradleCLI {

    private final BufferedReader reader;
    private final PrintStream writer;

    private HelloGradleCLI(BufferedReader reader, PrintStream writer){
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream writer = System.out;
        HelloGradleCLI cli = new HelloGradleCLI(reader, writer);

        while(true){
            // read and execute
            cli.readAndExecute();
        }
    }

    private void readAndExecute() throws IOException {
        writer.print(">");
        String line = reader.readLine();
        if(line != null){
            try{
                Command.valueOf(line.toUpperCase()).execute();
            }
            catch(IllegalArgumentException exception){
                writer.printf("unknown command [%s]%n", line);
                Command.HELP.execute();
            }
            catch(Throwable throwable){
                writer.printf("failed to execute command [%s]:[%s]%n", line, throwable.getMessage());
            }
        }
        else {
            Command.EXIT.execute();
        }
    }
}