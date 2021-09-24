package net.jimblackler.tryjsonschematypes;

import com.google.appengine.repackaged.com.google.gson.Gson;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("*")
public class ExceptionFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException {
    try {
      try {
        chain.doFilter(request, response);
      } catch (Throwable ex) {
        throw new ServletException(ex);
      }
    } catch (ServletException ex) {
      ex.printStackTrace();
      Map<String, Object> structuredError = new LinkedHashMap<>();
      structuredError.put("message", ex.getMessage());
      Throwable rootCause = ex.getRootCause();
      if (rootCause != null) {
        structuredError.put("rootCause", rootCause.toString());
      }
      ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.setContentType("text/json");
      response.getWriter().write(new Gson().toJson(structuredError));
    }
  }
}
