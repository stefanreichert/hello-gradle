package net.wickedshell.hellogradle.cli;

import net.wickedshell.hellogradle.cli.command.Command;
import net.wickedshell.hellogradle.cli.command.CommandStore;
import net.wickedshell.hellogradle.cli.command.CommandStoreImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by stefan on 04/04/16.
 */
public class TestHelloGradleCLI {

    private HelloGradleCLI cli;
    private BufferedReader reader;
    private ByteArrayOutputStream stream;
    private CommandStore commandStore;

    private String[] getLinesReturnedByCLI() {
        return stream.toString().split("[\\r\\n]+");
    }

    @Before
    public void setUp(){
        reader = mock(BufferedReader.class);
        stream = new ByteArrayOutputStream();
        commandStore = spy(CommandStoreImpl.class);
        cli = new HelloGradleCLI(reader, new PrintStream(stream), commandStore);
    }

    @Test
    public void shouldExitFromReadAndDispatchOnNull() throws IOException {
        // given
        when(reader.readLine()).thenReturn(null);

        // when
        boolean doContinue = cli.readAndExecute();
        String[] lines = getLinesReturnedByCLI();

        // then
        assertThat(doContinue, is(equalTo(false)));
        assertThat(lines.length, is(equalTo(1)));
        assertThat(lines[0], endsWith("unexpectedly terminated"));
    }

    @Test
    public void shouldExitCLI() throws IOException {
        // given
        when(reader.readLine()).thenReturn("exit");

        // when
        boolean doContinue = cli.readAndExecute();
        String[] lines = getLinesReturnedByCLI();

        // then
        assertThat(doContinue, is(equalTo(false)));
        assertThat(lines.length, is(equalTo(1)));
        assertThat(lines[0], endsWith("bye!"));
    }

    @Test
    public void shouldPrintHelpContent() throws IOException {
        // given
        when(reader.readLine()).thenReturn("help");

        // when
        boolean doContinue = cli.readAndExecute();
        String[] lines = getLinesReturnedByCLI();

        // then
        assertThat(doContinue, is(equalTo(true)));
        assertThat(lines.length, is(equalTo(4)));
        assertThat(lines[0], containsString("available commands"));
        assertThat(lines[1], endsWith("greetings"));
        assertThat(lines[2], endsWith("help"));
        assertThat(lines[3], endsWith("exit"));
    }

    @Test
    public void shouldPrintGreetings() throws IOException {
        // given
        when(reader.readLine()).thenReturn("greetings");

        // when
        boolean doContinue = cli.readAndExecute();
        String[] lines = getLinesReturnedByCLI();

        // then
        assertThat(doContinue, is(equalTo(true)));
        assertThat(lines.length, is(equalTo(2)));
        assertThat(lines[0], endsWith("Hello Gradle!"));
        assertThat(lines[1], endsWith("Goodbye Maven!"));
    }

    @Test
    public void shouldHandleUnknownCommand() throws IOException {
        // given
        when(reader.readLine()).thenReturn("not a command");

        // when
        boolean doContinue = cli.readAndExecute();
        String[] lines = getLinesReturnedByCLI();

        // then
        assertThat(doContinue, is(equalTo(true)));
        assertThat(lines.length, is(equalTo(1)));
        assertThat(lines[0], containsString("unknown command"));
    }

    @Test
    public void shouldHandleExceptionOnCommandExecution() throws Exception {
        // given
        Command command = mock(Command.class);
        when(command.execute(any())).thenThrow(RuntimeException.class);

        // override help command
        when(commandStore.lookup("help")).thenReturn(command);
        when(reader.readLine()).thenReturn("help");

        // when
        boolean doContinue = cli.readAndExecute();
        String[] lines = getLinesReturnedByCLI();

        // then
        assertThat(doContinue, is(equalTo(true)));
        assertThat(lines.length, is(equalTo(1)));
        assertThat(lines[0], containsString("failed to execute command"));
    }
}
