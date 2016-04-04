package net.wickedshell.hellogradle.web;

import net.wickedshell.hellogradle.HelloGradle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by stefan on 01/04/16.
 */
@WebServlet(name = "HelloGradle", urlPatterns = "/greetings")
public class HelloGradleServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintStream printStream = new PrintStream(resp.getOutputStream());
        new HelloGradle().printToStream(printStream);
        printStream.flush();
        printStream.close();
    }
}
