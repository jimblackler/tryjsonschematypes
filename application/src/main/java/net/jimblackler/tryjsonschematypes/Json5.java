package net.jimblackler.tryjsonschematypes;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jimblackler.usejson.Json5Parser;

@WebServlet(value = "/json5")
public class Json5 extends HttpServlet {
  public Json5() {}

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/json");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Object json = new Json5Parser().parse(request.getParameter("json5"));
    PrintWriter writer = response.getWriter();
    writer.print(gson.toJson(json));
  }
}
