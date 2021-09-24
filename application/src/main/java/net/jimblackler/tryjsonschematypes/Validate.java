package net.jimblackler.tryjsonschematypes;

import com.google.appengine.repackaged.com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
import net.jimblackler.jsonschemafriend.StandardGenerationException;
import net.jimblackler.jsonschemafriend.Validator;
import net.jimblackler.usejson.Json5Parser;

@WebServlet(value = "/validate")
public class Validate extends HttpServlet {
  public Validate() {}

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Map<String, Object> out = new LinkedHashMap<>();
    response.setContentType("text/json");
    try {
      Json5Parser json5Parser = new Json5Parser();
      Object schemaObject = json5Parser.parse(request.getParameter("schema"));
      Object documentObject = json5Parser.parse(request.getParameter("document"));
      SchemaStore schemaStore = new SchemaStore();
      Schema schema = schemaStore.loadSchema(schemaObject);
      Map<String, Object> validation = new Validator().validateWithOutput(schema, documentObject);
      out.put("result",
          Boolean.TRUE.equals(validation.get("valid"))
              ? "Document validated against schema"
              : "Document did not validate against schema");
      out.put("validation", validation);
    } catch (StandardGenerationException e) {
      out.put("result", "Schema did not validate against metaschema");
      Map<String, Object> standardOutput = e.getStandardOutput();
      out.put("validation", standardOutput);
    } catch (GenerationException e) {
      throw new ServletException(e);
    }

    PrintWriter writer = response.getWriter();
    writer.print(new Gson().toJson(out));
  }
}
