<%--
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 24.10.2015
 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
  <link rel="stylesheet" href="css/dataentry.css"/>
  <link rel="stylesheet" href="css/menu.css"/>
    <link rel="shortcut icon" href="../../img/favicon.png" type="image/png">
  <title>WHO data entry</title>
</head>
<body>
<a href="/dashboard"><img src="../../img/who-logo-en.jpg" align="top" alt="World Health Organization"/>
<%String login = (String)request.getAttribute("login");%>
<%--<h4><%=login%></h4>--%>
</a>
<div id="main_menu"></div>

<div id="form_sel_title"><br/>
  <table width="95%">
    <tr>
      <td width="130px"><a>Select dataset</a></td>
      <td><select id="sel_form_title"></select></td>
    </tr>
    <tr>
      <td></td>
      <td><button id="btn_new_form" hidden="true">Fill in a new form</button></td>
    </tr>
  </table>

  <div id="form_page", hidden="true"></div>
</div>
</body>
</html>
<script type="text/javascript" charset="utf8" src="js/menu.js"></script>
<script type="text/javascript" charset="utf8" src="js/dataentry.js"></script>
