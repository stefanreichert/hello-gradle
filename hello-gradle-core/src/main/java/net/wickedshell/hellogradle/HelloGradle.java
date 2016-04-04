package net.wickedshell.hellogradle;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stefan on 01/04/16.
 */
public class HelloGradle {

    List<String> listGreetings(){
        return Arrays.asList("Hello Gradle!", "Goodbye Maven!");
    }

    public void printToStream(PrintStream stream){
        listGreetings().forEach(greeting -> stream.println(greeting));
    }
}
