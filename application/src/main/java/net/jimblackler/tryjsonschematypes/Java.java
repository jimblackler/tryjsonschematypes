package net.jimblackler.tryjsonschematypes;

import static net.jimblackler.jsonschemafriend.DocumentUtils.parseJson;
import static net.jimblackler.tryjsonschematypes.StreamUtils.transfer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jimblackler.jsonschemafriend.GenerationException;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschematypes.codegen.CodeGenerationException;
import net.jimblackler.jsonschematypes.codegen.JavaCodeGenerator;
import org.json.JSONException;

@WebServlet(value = "/java")
public class Java extends HttpServlet {
  public Java() {}

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/x-java-source");
    try {
      Object schemaObject = parseJson(request.getParameter("schema"));
      SchemaStore schemaStore = new SchemaStore();
      Schema schema = schemaStore.loadSchema(schemaObject);

      JavaCodeGenerator javaCodeGenerator = new JavaCodeGenerator("com.example");
      javaCodeGenerator.build(schema);

      if (false) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        javaCodeGenerator.output(stream);
        transfer(new ByteArrayInputStream(stream.toByteArray()), response.getOutputStream());
      } else {
        javaCodeGenerator.output(response.getOutputStream());
      }

    } catch (JSONException | GenerationException | CodeGenerationException e) {
      throw new ServletException(e);
    }
  }
}
