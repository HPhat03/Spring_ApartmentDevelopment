<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f8f9fa;
        padding: 20px;
        margin: 0;
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

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        padding: 12px;
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

    .score-band {
        font-weight: bold;
        text-transform: uppercase;
    }

    .missing-score {
        color: red;
        font-size: 18px;
        font-weight: bold;
    }
</style>

<body>
<div class="container">
    <h1>Chi tiết đánh giá</h1>
    <h4>Người thực hiện khảo sát: ${responses.name}</h4>
    <p>Ngày đánh giá: ${responses.submitDate}</p>

    <div>
        <h4>Các câu hỏi chi tiết:</h4>
        <table>
            <thead>
            <tr>
                <th>Câu hỏi</th>
                <th>Thang điểm</th>
                <th>Điểm</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="d" items="${detail_requests}">
                <tr>
                    <td>${d.question}</td>
                    <td class="score-band">
                        <c:choose>
                            <c:when test="${d.scoreBand == 'BAND_5'}">Thang 5</c:when>
                            <c:when test="${d.scoreBand == 'BAND_10'}">Thang 10</c:when>
                            <c:otherwise>Không xác định</c:otherwise>
                        </c:choose>
                    </td>
                    <c:forEach var="detail" items="${detail_responses}">
                        <c:if test="${d.id == detail.questionId.id}">
                            <td>${detail.answer}</td>
                        </c:if>
                    </c:forEach>
                    <c:if test="${empty detail_responses}">
                        <td><span class="missing-score">X</span></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
