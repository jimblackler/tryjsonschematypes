package net.jimblackler.tryjsonschematypes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jimblackler.jsonschemafriend.GenerationException;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;

@WebServlet(value = "/example")
public class Example extends HttpServlet {
  public static String getInformation() {
    return "Here's even more information";
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.println("<h1>Good afternoon. And good morning.</h1>");
    SchemaStore schemaStore = new SchemaStore();
    try {
      Schema schema =
          schemaStore.loadSchema(URI.create("https://json.schemastore.org/chrome-manifest"));
      writer.println("<ul>");
      Map<String, Object> document = new LinkedHashMap<>();
      new Validator().validate(
          schema, document, error -> writer.println("<li>" + error + " </li>"));
      writer.println("</ul>");
    } catch (GenerationException e) {
      throw new ServletException(e);
    }
  }
}
