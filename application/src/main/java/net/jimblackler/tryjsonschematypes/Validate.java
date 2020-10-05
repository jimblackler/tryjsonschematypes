package net.jimblackler.tryjsonschematypes;

import static net.jimblackler.jsonschemafriend.DocumentUtils.parseJson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jimblackler.jsonschemafriend.GenerationException;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "Validate", value = "/validate")
public class Validate extends HttpServlet {
  public Validate() {}

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    JSONObject out;
    response.setContentType("text/json");
    try {
      Object schemaObject = parseJson(request.getParameter("schema"));
      Object documentObject = parseJson(request.getParameter("document"));
      SchemaStore schemaStore = new SchemaStore();
      Schema schema = schemaStore.loadSchema(schemaObject);
      out = new Validator().validateWithOutput(schemaStore, schema, documentObject);
    } catch (JSONException | GenerationException e) {
      throw new ServletException(e);
    }

    PrintWriter writer = response.getWriter();
    writer.print(out.toString(2));
  }
}
