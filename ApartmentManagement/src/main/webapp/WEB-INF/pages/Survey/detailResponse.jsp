<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/17/2024
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<body>
<div class="container">



    <h1>Chi tiết đánh giá</h1>
    <h4>Mã phiếu: ${responses}</h4>
    <h4>Người thực hiện khảo sát:

    </h4>
    <p>${detail_responses}</p>
    <h4>Ngày thực hiện: ${requests.startDate}</h4>



</div>
</body>

