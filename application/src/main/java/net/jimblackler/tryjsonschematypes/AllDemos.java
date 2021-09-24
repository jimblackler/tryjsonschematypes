package net.jimblackler.tryjsonschematypes;

import com.google.appengine.repackaged.com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/allDemos.json")
public class AllDemos extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/json");
    List<Object> out = new ArrayList<>();
    DemoStore.getInstance().getDemos().forEach(out::add);
    HttpUtils.setCacheStatus(resp, Calendar.MONTH, 1);
    PrintWriter writer = resp.getWriter();
    writer.print(new Gson().toJson(out));
  }
}
