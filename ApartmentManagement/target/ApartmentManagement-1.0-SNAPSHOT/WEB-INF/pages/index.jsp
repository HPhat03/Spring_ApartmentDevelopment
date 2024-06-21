<%-- 
    Document   : index
    Created on : Apr 7, 2024, 9:51:56 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        <h1>Hello Wd!</h1>
       
            <c:forEach items="${services}" var="c">
                <h1>${c.name}</h1>
            </c:forEach>
            <a href="<c:url value="/login"/>">DANG NHAP</a>
    <p>
        ${pageContext.request.userPrincipal}
    </p>

          
               
            
        
    
</html>
