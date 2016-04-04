package net.wickedshell.hellogradle;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by stefan on 01/04/16.
 */
public class IntegrationTestHelloGradle {

    private HelloGradle helloGradle;

    @Before
    public void setUp(){
        helloGradle = new HelloGradle();
    }

    @Test
    public void shouldPrintGreetings(){
        // given
        PrintStream stream = mock(PrintStream.class);

        // when
        helloGradle.printToStream(stream);

        // then
        verify(stream).println("Hello Gradle!");
        verify(stream).println("Goodbye Maven!");
    }
}
