<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.jimblackler.tryjsonschematypes.Example" %>
<html>
<body>
<h1>Greetings 3</h1>
<h2>Message: <%= Example.getInformation() %></h2>
<h2><a href='${pageContext.request.contextPath}/example'>Testing service</a></h2>
</body>
</html>
