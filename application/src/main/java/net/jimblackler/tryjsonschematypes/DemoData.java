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

@WebServlet(value = "/demoData")
public class DemoData extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/json");
    String param = req.getParameter("demo");

    JSONObject out = new JSONObject();
    DemoStore demoStore = DemoStore.getInstance();
    out.put("document", demoStore.getDocument(param));
    out.put("schema", demoStore.getSchema(param));

    resp.setHeader("Cache-Control", "public");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.MONTH, 1);
    resp.setHeader(
        "Expires", new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz").format(calendar.getTime()));

    PrintWriter writer = resp.getWriter();
    writer.print(out.toString(2));
  }
}
