package net.jimblackler.tryjsonschematypes;

import static net.jimblackler.jsonschemafriend.DocumentUtils.parseJson;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jimblackler.jsonschemafriend.DocumentUtils;
import org.json.JSONException;

@WebServlet(value = "/json5")
public class Json5 extends HttpServlet {
  public Json5() {}

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/json");
    try {
      Object json = parseJson(request.getParameter("json5"));
      PrintWriter writer = response.getWriter();
      writer.print(DocumentUtils.toString(json));
    } catch (JSONException e) {
      throw new ServletException(e);
    }
  }
}
