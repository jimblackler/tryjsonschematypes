package net.jimblackler.tryjsonschematypes;

import com.google.appengine.repackaged.com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jimblackler.jsongenerator.Configuration;
import net.jimblackler.jsongenerator.Generator;
import net.jimblackler.jsongenerator.JsonGeneratorException;
import net.jimblackler.jsonschemafriend.GenerationException;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.StandardGenerationException;
import net.jimblackler.usejson.Json5Parser;

@WebServlet(value = "/generate")
public class Generate extends HttpServlet {
  public Generate() {}

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Map<String, Object> out = new LinkedHashMap<>();
    response.setContentType("text/json");
    try {
      Object schemaObject = new Json5Parser().parse(request.getParameter("schema"));
      SchemaStore schemaStore = new SchemaStore();
      Schema schema = schemaStore.loadSchema(schemaObject);
      Object generated = new Generator(new Configuration() {
        @Override
        public boolean isPedanticTypes() {
          return false;
        }

        @Override
        public boolean isGenerateNulls() {
          return false;
        }

        @Override
        public boolean isGenerateMinimal() {
          return false;
        }

        @Override
        public float nonRequiredPropertyChance() {
          return 0.75f;
        }
      }, schemaStore, new Random()).generate(schema, 20);
      out.put("generated", generated);
    } catch (StandardGenerationException e) {
      out.put("result", "Schema did not validate against metaschema");
      out.put("validation", e.getStandardOutput());
    } catch (GenerationException | JsonGeneratorException e) {
      throw new ServletException(e);
    }

    PrintWriter writer = response.getWriter();
    writer.print(new Gson().toJson(out));
  }
}
