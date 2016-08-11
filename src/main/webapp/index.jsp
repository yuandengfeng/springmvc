<html>
<body>
<h2>Hello World!</h2>

${requestScope.a}<br/>
${sessionScope.a}<br/>
${sessionScope.c}<br/>
<%--通过ModelAndView获取值--%>
${requestScope.mUser.uname}<br/>
${requestScope.uu.uname}<br/>
</body>
</html>
