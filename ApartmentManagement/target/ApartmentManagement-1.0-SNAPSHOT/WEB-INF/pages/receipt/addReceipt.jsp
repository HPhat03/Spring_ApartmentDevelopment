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
    </style>

<body>
<div class="container">
    <h1>Tạo hóa đơn mới</h1>

    <form id="receiptForm">
        <label for="apartmentId">Số phòng:</label>
        <select id="apartmentId" name="apartmentId" required>
            <option value="" label="-- Chọn phòng --"></option>
            <c:forEach items="${constracts}" var="con">
                <option value="${con.id}">${con.roomId.roomNumber}</option>
            </c:forEach>
        </select>

        <label for="electric_usage">Tiêu thụ điện (kWh):</label>
        <input type="number" id="electric_usage" name="electric_usage" required>

        <label for="water_usage">Tiêu thụ nước (m3):</label>
        <input type="number" id="water_usage" name="water_usage" required>

        <input type="submit" value="Tạo hóa đơn">
    </form>
</div>

<script>
    document.getElementById('receiptForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Ngăn chặn form gửi mặc định

        const formData = new FormData(event.target);
        const data = {
            month: new Date().getMonth() + 1, // Tháng hiện tại
            year: new Date().getFullYear().toString(), // Năm hiện tại
            electric_usage: Number(formData.get('electric_usage')), // Chuyển đổi thành số
            water_usage: Number(formData.get('water_usage')), // Chuyển đổi thành số
            apartmentId: Number(formData.get('apartmentId')) // Chuyển đổi thành số
        };

        fetch('/ApartmentManagement/admin/receipts/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (response.status === 201) {
                    alert('Hóa đơn đã được tạo thành công!');

                    window.location.href = '/ApartmentManagement/admin/receipts/';
                } else if (response.status === 400) {
                    alert('Thiếu các thông tin cần thiết');
                } else if (response.status === 404) {
                    alert('Không tìm thấy phòng với ID đã chọn');
                } else {
                    return response.json().then(errorData => {
                        throw new Error(errorData.message || 'Có lỗi xảy ra');
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Có lỗi xảy ra khi tạo hóa đơn: ' + error.message);
            });
    });
</script>
</body>
