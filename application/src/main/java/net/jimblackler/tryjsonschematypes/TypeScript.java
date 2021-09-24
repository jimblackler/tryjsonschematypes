package net.jimblackler.tryjsonschematypes;

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
import net.jimblackler.jsonschematypes.codegen.TypeScriptCodeGenerator;
import net.jimblackler.usejson.Json5Parser;

@WebServlet(value = "/typescript")
public class TypeScript extends HttpServlet {
  public TypeScript() {}

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/x-typescript");
    try {
      Object schemaObject = new Json5Parser().parse(request.getParameter("schema"));
      SchemaStore schemaStore = new SchemaStore();
      Schema schema = schemaStore.loadSchema(schemaObject);

      TypeScriptCodeGenerator typeScriptCodeGenerator = new TypeScriptCodeGenerator();
      typeScriptCodeGenerator.build(schema);

      if (false) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        typeScriptCodeGenerator.output(stream);
        transfer(new ByteArrayInputStream(stream.toByteArray()), response.getOutputStream());
      } else {
        typeScriptCodeGenerator.output(response.getOutputStream());
      }
    } catch (GenerationException e) {
      throw new ServletException(e);
    }
  }
}
