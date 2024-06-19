<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/17/2024
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết khảo sát</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1, h4 {
            color: #333;
            margin-bottom: 10px;
        }

        h1 {
            font-size: 28px;
        }

        h4 {
            font-size: 20px;
        }

        .status-button {
            border-radius: 20px;
            padding: 8px 20px;
            font-size: 14px;
            font-weight: bold;
            text-transform: uppercase;
            margin-top: 10px;
            display: inline-block;
        }

        .btn-success {
            background-color: #28a745;
            color: #fff;
            border: none;
        }

        .btn-outline-danger {
            background-color: transparent;
            border: 1px solid #dc3545;
            color: #dc3545;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px 16px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f0f0f0;
            font-size: 16px;
            font-weight: bold;
            color: #333;
        }

        td {
            font-size: 16px;
            color: #666;
        }
        .response-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .response-table th,
        .response-table td {
            padding: 12px 16px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .response-table th {
            background-color: #f0f0f0;
            font-size: 16px;
            font-weight: bold;
            color: #333;
        }

        .response-table td {
            font-size: 16px;
            color: #666;
        }

        .response-button {
            padding: 8px 16px;
            font-size: 14px;
            font-weight: bold;
            text-transform: uppercase;
            border: none;
            border-radius: 20px;
            cursor: pointer;
        }

        .response-button-success {
            background-color: #28a745;
            color: #fff;
        }


    </style>
</head>
<body>
<div class="container">
    <h1>Chi tiết khảo sát</h1>
    <h4>Mã phiếu: ${requests.id}</h4>
    <h4>Admin thực hiện:
        <c:forEach items="${admins}" var="a">
            <c:choose>
                <c:when test="${requests.adminId.userId == a.id}">
                    ${a.name}
                </c:when>
            </c:choose>
        </c:forEach>
    </h4>
    <h4>Ngày bắt đầu: ${requests.startDate}</h4>
    <h4>Ngày kết thúc: ${requests.endDate}</h4>

    <c:choose>
        <c:when test="${requests.isActive == 1}">
            <button class="status-button btn-success">Còn thời hạn</button>
        </c:when>
        <c:otherwise>
            <button class="status-button btn-outline-danger">Hết hạn</button>
        </c:otherwise>
    </c:choose>

    <div>
        <h4>Các câu hỏi chi tiết:</h4>
        <table>
            <thead>
            <tr>
                <th>Câu hỏi</th>
                <th>Thang điểm</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="d" items="${detail_requests}">
                <tr>
                    <td>${d.question}</td>
                    <td>
                        <c:choose>
                            <c:when test="${d.scoreBand == 'BAND_5'}">Thang 5</c:when>
                            <c:when test="${d.scoreBand == 'BAND_10'}">Thang 10</c:when>
                            <c:otherwise>Không xác định</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <h1> Danh sách phản hồi</h1>
    <c:choose>
        <c:when test="${empty responses}">
            <p>Chưa có ai phản hồi khảo sát.</p>
        </c:when>
        <c:otherwise>
            <table class="response-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Phòng</th>
                    <th>Ngày hoàn thành</th>
                    <th>Tên khách hàng phản hồi</th>
                    <th>Chi tiết</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${responses}" var="res">
                    <tr>
                        <td>${res.id}</td>
                        <td>${res.apartmentId.roomId.roomNumber}</td>
                        <td>${res.submitDate}</td>
                        <td>${res.name}</td>
                        <c:url value="/survey_request/${requests.id}/response/${res.id}" var="urlRes"/>
                        <td><button class="response-button response-button-success"><a href="${urlRes}">xem đánh giá</a></button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>
