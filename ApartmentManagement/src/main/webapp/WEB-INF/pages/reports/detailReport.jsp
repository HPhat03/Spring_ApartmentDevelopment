<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/17/2024
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    body {
        background-color: #f0f2f5;
        font-family: 'Arial', sans-serif;
    }
    .container {
        margin-top: 50px;
    }
    .card {
        padding: 30px;
        border-radius: 15px;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        background-color: #ffffff;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
    }
    .card:hover {
        transform: translateY(-10px);
        box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
    }
    .card h1 {
        font-size: 2.5rem;
        margin-bottom: 20px;
        color: #007bff;
        border-bottom: 3px solid #007bff;
        padding-bottom: 10px;
    }
    .card h3 {
        font-size: 1.5rem;
        margin-bottom: 15px;
        color: #343a40;
    }
    .card h4 {
        font-size: 1.25rem;
        margin: 10px 0;
        color: #6c757d;
    }
    .text-muted {
        font-weight: 500;
    }
    .images {
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
        margin-top: 20px;
    }
    .images img {
        max-width: 400px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease, box-shadow 0.3s ease;
        margin-left: 10px;
    }
    .images img:hover {
        transform: scale(1.05);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    }
</style>

<body>
<div class="container">
    <div class="card">
        <h1>Chi tiết báo cáo</h1>
        <h3>Id: <span class="text-muted">${report.id}</span></h3>
        <h3>Phòng: <span class="text-muted">${report.apartmentId.roomId.roomNumber}</span></h3>
        <h3>Tiêu đề: <span class="text-muted">${report.title}</span></h3>
        <h3>Ngày tạo: <span class="text-muted"><fmt:formatDate value="${report.createdDate}" pattern="dd-MM-yyyy" /></span></h3>
        <h3>Nội dung:</h3>
        <c:forEach var="d" items="${details}">
            <h4>* ${d.content}</h4>
        </c:forEach>
        <h3>Hình ảnh:</h3>
        <div class="images">
            <c:forEach var="picture" items="${pictures}">
                <img src="${picture.picture}" alt="Report Picture">
            </c:forEach>
        </div>
    </div>
</div>
</body>
