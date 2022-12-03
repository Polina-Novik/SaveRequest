<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="MyStyle12.css"/>
</head>
<body>
<form action="/request" method="post">
    <input type="text" name="name" placeholder="name"/>
    <c:if test="${errors['name']!=null}"><div style="color:red"><c:out value="${errors['name']}"/></div> </c:if>
    <input type="text" name="number" placeholder="phone number"/>
    <c:if test="${errors['number']!=null}"><div style="color:red"><c:out value="${errors['number']}"/></div> </c:if>
    <input type="text" name="message" placeholder="message"/>
    <c:if test="${errors['message']!=null}"><div style="color:red"><c:out value="${errors['message']}"/></div> </c:if>
    <input type="submit" value="Send" />
</form>
</body>
</html>
