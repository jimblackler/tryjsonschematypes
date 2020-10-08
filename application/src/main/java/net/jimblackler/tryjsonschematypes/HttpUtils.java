package net.jimblackler.tryjsonschematypes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

public class HttpUtils {
  static void setCacheStatus(HttpServletResponse resp, int unit, int amount) {
    resp.setHeader("Cache-Control", "public");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(unit, amount);
    resp.setHeader(
        "Expires", new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz").format(calendar.getTime()));
  }
}
