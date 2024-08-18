<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/20/2024
  Time: 5:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 5px;
        }
        h1, h2, h3 {
            color: #343a40;
            margin-bottom: 10px;
        }
        .detail-section {
            margin-bottom: 30px;
        }
        .detail-section h3 {
            font-size: 1.2em;
            margin-bottom: 10px;
            color: #007bff;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            background-color: #fff;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
        }
        .text-right {
            text-align: right;
        }
        .total {
            font-size: 1.2em;
            font-weight: bold;
            color: #28a745;
        }
    </style>

<body>
<div class="container">
    <h1>Chi tiết hóa đơn</h1>

    <div class="detail-section">
        <h2>Mã hóa đơn: ${receipt.id}</h2>
        <p>Số phòng: ${receipt.apartmentId.roomId.roomNumber}</p>
    </div>
    <div class="detail-section">
        <h3>Chi tiết các dịch vụ</h3>
        <c:choose>
            <c:when test="${empty detail_receipt}">
                <p>Chưa đăng kí dịch vụ</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>Tên dịch vụ</th>
                        <th class="text-right">Thành tiền (VNĐ)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="d" items="${detail_receipt}">
                        <tr>
                            <td>${d.content}</td>
                            <td class="text-right">${String.format("%,d", d.price)}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>


    <div class="detail-section total">
        <h3>Tổng tiền hóa đơn: ${String.format("%,d", receipt.total)} VNĐ</h3>
    </div>
    
        <c:choose>
            <c:when test="${empty payment}">
                <h4 class="text-danger">CHƯA THANH TOÁN</h4>
            </c:when>
            <c:otherwise>
                <div class="detail-section">
                    <h4>Hình thức thanh toán</h4>
                   <c:forEach var="p" items="${payment}">

                                        <div>Thanh toán theo phương thức: ${p.paymentMethod}</div>
                                        <div>Mã giao dịch: ${p.transactionId}</div>
                                        <div>Ngày thanh toán: ${p.createdDate}</div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    
    
</div>
</body>









