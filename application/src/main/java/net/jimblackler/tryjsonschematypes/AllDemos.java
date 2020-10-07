package net.jimblackler.tryjsonschematypes;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

@WebServlet(value = "/allDemos.json")
public class AllDemos extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/json");
    JSONArray out = new JSONArray();
    DemoStore.getInstance().getDemos().forEach(out::put);

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
