package net.wickedshell.hellogradle;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by stefan on 01/04/16.
 */
public class TestHelloGradle {

    private HelloGradle helloGradle;
    private PrintStream streamMock;

    @Before
    public void setUp(){
        helloGradle = new HelloGradle();
    }

    @Test
    public void shouldReturnTwoGreetings() {
        // given

        // when
        List<String> greetings = helloGradle.listGreetings();

        //then
        assertThat(greetings, CoreMatchers.hasItem("Hello Gradle!"));
        assertThat(greetings, CoreMatchers.hasItem("Goodbye Maven!"));
    }
}
