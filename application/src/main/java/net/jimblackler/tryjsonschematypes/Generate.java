package net.jimblackler.tryjsonschematypes;

import net.jimblackler.jsongenerator.Configuration;
import net.jimblackler.jsongenerator.Generator;
import net.jimblackler.jsongenerator.JsonGeneratorException;
import net.jimblackler.jsonschemafriend.GenerationException;
import net.jimblackler.jsonschemafriend.MissingPathException;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.StandardGenerationException;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import static net.jimblackler.jsonschemafriend.DocumentUtils.parseJson;

@WebServlet(value = "/generate")
public class Generate extends HttpServlet {
  public Generate() {}

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    JSONObject out = new JSONObject();
    response.setContentType("text/json");
    try {
      Object schemaObject = parseJson(request.getParameter("schema"));
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
    } catch (JSONException | GenerationException | JsonGeneratorException | MissingPathException e) {
      throw new ServletException(e);
    }

    PrintWriter writer = response.getWriter();
    writer.print(out.toString(2));
  }
}
