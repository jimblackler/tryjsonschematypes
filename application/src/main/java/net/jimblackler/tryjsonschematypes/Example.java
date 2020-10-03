package net.jimblackler.tryjsonschematypes;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Example", value = "/example")
public class Example extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    response.getWriter().println("<h1>Good afternoon. And good morning.</h1>");
  }

  public static String getInformation() {
    return "Here's even more information";
  }
}
