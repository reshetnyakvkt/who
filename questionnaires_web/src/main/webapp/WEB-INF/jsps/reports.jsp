<%--
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 02.11.2015
 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/reports.css"/>
  <link rel="stylesheet" href="css/menu.css"/>
    <link rel="shortcut icon" href="../../img/favicon.png" type="image/png">
  <title>WHO data entry</title>
</head>
<body>
<a href="/dashboard"><img src="../../img/who-logo-en.jpg" align="top" alt="World Health Organization"/>
<h4><%= session.getAttribute("UserName")%></h4>

<div id="main_menu"></div>
</a>
<%out.println((String)request.getAttribute("form_sel_title"));%>
<div id="form_list"></div>
</body>
</html>
<script type="text/javascript" charset="utf8" src="js/dateutils.js"></script>
<script type="text/javascript" charset="utf8" src="js/menu.js"></script>
<script type="text/javascript" charset="utf8" src="js/reports.js"></script>
<script type="text/javascript" charset="utf8" src="js/exports.js"></script>

