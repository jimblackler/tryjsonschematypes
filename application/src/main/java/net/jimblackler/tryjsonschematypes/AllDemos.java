package net.jimblackler.tryjsonschematypes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
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
    HttpUtils.setCacheStatus(resp, Calendar.MONTH, 1);
    PrintWriter writer = resp.getWriter();
    writer.print(out.toString(2));
  }
}
