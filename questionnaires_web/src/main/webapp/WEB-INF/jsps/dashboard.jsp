<%@ page import="questionnaires.domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: reashetnyak_viktor
  Document : Dashboard
--%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/dashboard.css"/>
  <link rel="stylesheet" href="css/menu.css"/>
  <link rel="shortcut icon" href="../../img/favicon.png" type="image/png">
  <title>WHO dashboard</title>
</head>
<body>
<a href="/dashboard"><img src="../../img/who-logo-en.jpg" align="top" alt="World Health Organization"/>
<%
  //String login = (String)request.getAttribute("login");
  Date lastEdit = (Date)request.getAttribute("last_edit");
%>
<%--<h4><%=login%></h4>--%>
<h4><%= session.getAttribute("UserName")%></h4>
</a>

<div id="main_menu">
  <ul id="nav">
    <li><a href="/dataentry">Fill form</a></li>
    <li><a href="#">Save form</a></li>
    <li><a href="#">List templates</a></li>
    <li><a href="/">Log out</a></li>
  </ul>
</div>
<div id="content">
  <p>Last edited:<%=lastEdit%></p>
  <p hidden="true">Сессия: '<%=session.getId()%>'</p>
</div>

</body>
</html>
<script type="text/javascript" charset="utf8" src="js/menu.js"></script>