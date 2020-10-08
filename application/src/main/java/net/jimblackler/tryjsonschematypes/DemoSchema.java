package net.jimblackler.tryjsonschematypes;

import static net.jimblackler.tryjsonschematypes.StreamUtils.transfer;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/demoSchema")
public class DemoSchema extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/json");
    HttpUtils.setCacheStatus(resp, Calendar.MONTH, 1);
    transfer(DemoStore.getInstance().getSchema(req.getParameter("demo")), resp.getOutputStream());
  }
}
