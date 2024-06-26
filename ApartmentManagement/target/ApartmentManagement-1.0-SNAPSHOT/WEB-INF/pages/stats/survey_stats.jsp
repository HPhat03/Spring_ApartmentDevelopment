<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    #myChart {
        height: 400px; /* Đặt chiều cao tối đa của canvas */
        margin: 20px auto; /* Canh giữa biểu đồ */
    }
</style>

<body>
<div class="container">
    <h1 class="text-center text-info mt-1">THỐNG KÊ THEO PHIẾU KHẢO SÁT</h1>

    <div class="row">
        <div class="col-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Câu hỏi</th>
                    <th>Thang điểm</th>
                    <th>Điểm trung bình</th>
                    <th>Số lần trả lời</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${SurveyStats}" var="stat" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${stat[1]}</td>
                        <td>${stat[2]}</td>
                        <td>${stat[3]}</td>
                        <td>${stat[4]}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-12">
            <!-- Đồ thị thống kê -->
            <canvas id="myChart"></canvas>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    var surveyStats = JSON.parse('${SurveyStatsJson}');

    var labels = surveyStats.map(stat => stat[1]);
    var averageScores = surveyStats.map(stat => stat[3]);

    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Điểm trung bình',
                    data: averageScores,
                    backgroundColor: ['pink', 'red', 'green', 'blue'],
                    borderColor: 'lightgreen',
                    borderWidth: 1
                }
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
</body>