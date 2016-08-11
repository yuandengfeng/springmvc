<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/2/11
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
 <%--<form action="user/reg.do">--%>
 <form action="user.do">
   用户名：<input type="text" name="uname" /><br/>
   <input type="hidden" name="method" value="reg6" />
  <input type="submit" value="注册" />


     ${requestScope.a}<br/>
     ${sessionScope.a}<br/>
     ${sessionScope.c}<br/>
     <%--通过ModelAndView获取值--%>
     ${requestScope.mUser.uname}<br/>
     ${requestScope.uu.uname}<br/>

 </form>
</body>
</html>
