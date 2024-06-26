<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/20/2024
  Time: 5:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 20px;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 5px;
        }
        h1 {
            color: #343a40;
            margin-bottom: 10px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="number"], select {
            width: calc(100% - 12px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 3px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: red;
            font-size: 16px;
            /*margin-bottom: 15px;*/
        }
    </style>

<body>
<div class="container">
    <h1>Tạo hóa đơn mới</h1>

    <form id="receiptForm">
        <div id="apartmentIdError" class="error-message"></div>
        <label for="apartmentId">Số phòng:</label>
        <select id="apartmentId" name="apartmentId" required>
            <option value="" label="-- Chọn phòng --"></option>
            <c:forEach items="${constracts}" var="con">
                <option value="${con.id}">[${con.id}] ${con.roomId.roomNumber} - ${con.residentId.apartmentUser.name}</option>
            </c:forEach>
        </select>
        <div id="monthError" class="error-message"></div>
        <label for="month">Tháng:</label>
        <input type="number" id="month" name="month" min="1" max="12" required>

        <div id="yearError" class="error-message"></div>
        <label for="year">Năm:</label>
        <input type="number" id="year" name="year" min="0" max="2024" required>

        <div id="electricUsageError" class="error-message"></div>
        <label for="electric_usage">Tiêu thụ điện (kWh):</label>
        <input type="number" id="electric_usage" name="electric_usage" required>

        <div id="waterUsageError" class="error-message"></div>
        <label for="water_usage">Tiêu thụ nước (m3):</label>
        <input type="number" id="water_usage" name="water_usage" required>


        <c:url value="/admin/receipts/add" var="urlAdd"/>
        <c:url value="/admin/receipts/?page=1" var="urlIndex"/>
        <button type="button" class="btn btn-primary mt-4" onclick="createReceipt('${urlAdd}', '${urlIndex}')">Tạo hóa đơn</button>
    </form>
</div>

<script src="<c:url value="/js/receipt.js" />"></script>
</body>

