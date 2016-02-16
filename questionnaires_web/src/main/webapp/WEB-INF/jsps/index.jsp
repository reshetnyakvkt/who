<%@ page import="java.lang.String" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: reashetnyak_viktor
  Document : Authenticate
--%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/login.css"/>
  <link rel="shortcut icon" href="../../img/favicon.png" type="image/png">
  <title>World Health Organization</title>
</head>
<div id="branding">
  <a linkindex="0" href="/en/" title="WHO | World Health Organization"></a>
</div>
<p><img src="../../img/who-logo-en.jpg" align="top" alt="World Health Organization"/></p>
<h5>
  <p style="color: red; horiz-align: center"; align="middle">
    <%
      if (request.getAttribute("error_auth") != null) {
        boolean error = (boolean) request.getAttribute("error_auth");
        if (error) {
          String error_message = (String) request.getAttribute("error_message");
          if (error_message != null) {
            out.println(error_message);
          } else {
            out.println("Error authenticate!");
          }
        }
      }
    %>
  </p>
</h5>
<form action="auth" class="authenticate" method="post">
<fieldset>
    <legend> Authenticate </legend>
    <p><label style="width: 80px" class="field">Login</label><input type="text" name="login" value="${login}"/></p>
    <p><label style="width: 80px" class="field">Password</label><input type="password" name="password" /></p>
    <button style="width: 100px; height: 25px" NAME="submit" type="submit" value="Login">Login</button>
</fieldset>
</form>
</body>
</html>