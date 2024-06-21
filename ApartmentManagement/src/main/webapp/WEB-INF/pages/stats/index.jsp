<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thống kê, báo cáo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .table {
            margin-top: 20px;
        }
        /*#myChart2 {*/
        /*    max-height: 400px;*/
        /*    margin: 20px auto;*/
        /*}*/
        .btn-custom {
            margin-top: 8px; /* Canh nút Thống kê ngang với select box */
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center text-info mt-1">THỐNG KÊ, BÁO CÁO</h1>

    <div class="row">
        <div class="col-md-6 col-12">
            <div class="form-group">
                <c:url value="/stats/survey/" var="url"/>
                <form method="get" action="${url}">
                    <label>Phiếu khảo sát:</label>
                    <div class="d-flex">
                        <select class="form-control col-5 mr-2 mt-2" name="id">
                            <option value="">-- Chọn phiếu khảo sát --</option>
                            <c:forEach items="${surveys}" var="su">
                                <option value="${su.id}">${su.id}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-success btn-custom">Thống kê phiếu khảo sát</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-6 col-12">
            <p>${RevenueSurvey}</p>
            <canvas id="myChart1"></canvas>
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col-md-6 col-12">
            <form class="row g-3 align-items-center">
                <div class="col-auto">
                    <label for="year" class="form-label">Chọn năm:</label>
                    <input class="form-control" id="year" placeholder="Năm" name="year">
                </div>
                <div class="col-auto">
                    <label for="period" class="form-label">Chọn thời gian:</label>
                    <select class="form-select" id="period" name="period">
                        <option selected value="MONTH">Theo tháng</option>
                        <option value="QUARTER">Theo quý</option>
                    </select>
                </div>
                <div class="col-auto">
                    <button class="btn btn-success" type="submit">Thống kê doanh thu</button>
                </div>
            </form>

            <table class="table table-striped mt-3"> <!-- Thêm margin-top để tách biểu đồ và bảng -->
                <thead>
                <tr>
                    <th>Thời gian</th>
                    <th>Doanh thu</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${RevenueByPeriod}" var="p">
                    <tr>
                        <td>${p[0]}</td>
                        <td>${String.format("%,d", p[1])} VNĐ</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-md-6 col-12 mt-9    ">
            <canvas id="myChart2"></canvas>
        </div>
    </div>


</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    var label2 = [];
    var data2 = [];

    <c:forEach items="${RevenueByPeriod}" var="p">
    label2.push('${p[0]}');
    data2.push(${p[1]});
    </c:forEach>

    window.onload = function () {
        var ctx2 = document.getElementById("myChart2");
        drawChartRevenue(ctx2, label2, data2, "DOANH THU ");
    }

    function drawChartRevenue(ctx, label, data, title) {
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: label,
                datasets: [{
                    label: title,
                    data: data,
                    borderWidth: 1,
                    backgroundColor: ['pink', 'yellow', 'green', 'white', 'orange']
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }
</script>
</body>
</html>
