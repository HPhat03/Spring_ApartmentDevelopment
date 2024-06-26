<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/15/2024
  Time: 9:50 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <style>
        .relative-input-group {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }

        .relative-input-group .form-control {
            flex: 1;
            margin-right: 10px;
        }

        .relative-input-group .form-control:last-child {
            margin-right: 0;
        }

        .relative-input-group .remove-relative {
            background: none;
            border: none;
            color: red;
            font-size: 20px;
            cursor: pointer;
        }
        .error-message {
            color: red;
            font-size: 16px;
            /*margin-bottom: 15px;*/
        }
    </style>
</head>

<div class="container mt-5">
    <h2 class="text-center">Tạo Hợp Đồng Thuê Nhà</h2>
    <form id="contractForm" onsubmit="handleSubmit(event)">

        <div class="form-group">
            <div id="residentError" class="error-message"></div>
            <label for="resident">Cư dân:</label>
            <select id="resident" name="resident_id" class="form-control" required>
                <option value="" label="-- Chọn cư dân --"></option>
                <c:forEach items="${residents}" var="resident">
                    <option value="${resident.id}">${resident.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <div id="roomError" class="error-message"></div>
            <label for="room">Số phòng:</label>
            <select id="room" name="room_id" class="form-control" required>
                <option value="" label="-- Chọn phòng --"></option>
                <c:forEach items="${rooms}" var="room">
                    <option value="${room.id}">${room.roomNumber}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <div id="priceError" class="error-message"></div>
            <label for="contractPrice">Giá hợp đồng:</label>
            <input type="number" id="contractPrice" name="final_price" class="form-control" required>
        </div>

        <div class="form-group">
            <label>Các dịch vụ:</label>
            <table class="table mt-3">
                <thead>
                <tr>
                    <th>Chọn</th>
                    <th>Tên Dịch Vụ</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${services}" var="service">
                    <tr>
                        <td>
                            <input type="checkbox" name="services" value="${service.id}" class="service-checkbox" checked>
                        </td>
                        <td>${service.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="form-group">
            <div id="relativesError" class="error-message"></div>
            <label>Thêm người thân:</label>
            <button type="button" class="btn btn-success mb-2" onclick="addRelative()"><i class="fa-solid fa-square-plus"></i></button>
            <div id="relatives-container">

            </div>
        </div>

        <c:url value="/admin/constract/" var="urlAdd"/>
        <c:url value="/admin/constracts/" var="urlIndex"/>

        <button type="button" class="btn btn-primary mt-4" onclick="createConstract('${urlAdd}', '${urlIndex}')">Tạo phiếu khảo sát</button>
    </form>
</div>
<script src="<c:url value="/js/constract.js" />"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Select all checkboxes for services by default
        var checkboxes = document.querySelectorAll('.service-checkbox');
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = true;
        });
    });


</script>
