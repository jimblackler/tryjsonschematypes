package net.jimblackler.tryjsonschematypes;

import static net.jimblackler.jsonschemafriend.DocumentUtils.loadJson;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "DemoData", value = "/demoData")
public class DemoData extends HttpServlet {
  private final JSONObject demos;

  public DemoData() throws IOException {
    this.demos = (JSONObject) loadJson(DemoData.class.getResourceAsStream("/demos.json"));
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/json");
    String param = request.getParameter("demo");
    JSONObject allDemos = this.demos.getJSONObject("demos");

    JSONObject out = new JSONObject();
    JSONObject value = allDemos.getJSONObject(param);
    out.put("document", value.get("data"));
    out.put("schema", this.demos.getJSONObject("schemas").getJSONObject(value.getString("schema")));

    response.setHeader("Cache-Control", "public");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.YEAR, 1);
    response.setHeader(
        "Expires", new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz").format(calendar.getTime()));

    PrintWriter writer = response.getWriter();
    writer.print(out.toString(2));
  }
}
