package net.jimblackler.tryjsonschematypes;

import org.json.JSONObject;

import java.io.IOException;
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
      chain.doFilter(request, response);
    } catch (ServletException ex) {
      JSONObject structuredError = new JSONObject();
      structuredError.put("message", ex.getMessage());
      Throwable rootCause = ex.getRootCause();
      if (rootCause != null) {
        structuredError.put("rootCause", rootCause.toString());
      }
      ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.setContentType("text/json");
      response.getWriter().write(structuredError.toString());
    }
  }
}