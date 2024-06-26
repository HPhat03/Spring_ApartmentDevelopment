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
    </style>
</head>

<div class="container mt-5">
    <h2 class="text-center">Tạo Hợp Đồng Thuê Nhà</h2>
    <form id="contractForm" onsubmit="handleSubmit(event)">

        <div class="form-group">
            <label for="resident">Cư dân:</label>
            <select id="resident" name="resident_id" class="form-control" required>
                <option value="" label="-- Chọn cư dân --"></option>
                <c:forEach items="${residents}" var="resident">
                    <option value="${resident.id}">${resident.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="room">Số phòng:</label>
            <select id="room" name="room_id" class="form-control" required>
                <option value="" label="-- Chọn phòng --"></option>
                <c:forEach items="${rooms}" var="room">
                    <option value="${room.id}">${room.roomNumber}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
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
            <label>Thêm người thân:</label>
            <button type="button" class="btn btn-success mb-2" onclick="addRelative()"><i class="fa-solid fa-square-plus"></i></button>
            <div id="relatives-container">

            </div>
        </div>

        <button type="submit" class="btn btn-primary">Tạo hợp đồng</button>
    </form>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Select all checkboxes for services by default
        var checkboxes = document.querySelectorAll('.service-checkbox');
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = true;
        });
    });

    function addRelative() {
        var container = document.getElementById('relatives-container');
        var div = document.createElement('div');
        div.classList.add('relative-input-group');

        var nameInput = document.createElement('input');
        nameInput.type = 'text';
        nameInput.name = 'other_members.name';
        nameInput.placeholder = 'Tên người thân';
        nameInput.classList.add('form-control');

        var relationshipInput = document.createElement('input');
        relationshipInput.type = 'text';
        relationshipInput.name = 'other_members.relationship';
        relationshipInput.placeholder = 'Mối quan hệ';
        relationshipInput.classList.add('form-control');

        var removeButton = document.createElement('button');
        removeButton.type = 'button';
        removeButton.innerHTML = '<i class="fa-solid fa-trash"></i>';
        removeButton.classList.add('remove-relative');
        removeButton.onclick = function() {
            container.removeChild(div);
        };
        div.appendChild(nameInput);
        div.appendChild(relationshipInput);
        div.appendChild(removeButton);

        container.appendChild(div);
    }

    function handleSubmit(event) {
        event.preventDefault();
        var form = event.target;
        var jsonData = {};

        // Lấy dữ liệu từ các trường input của form
        var residentId = parseInt(form.querySelector('#resident').value);
        var roomId = parseInt(form.querySelector('#room').value);
        var finalPrice = parseInt(form.querySelector('#contractPrice').value);

        // Thêm các dịch vụ đã chọn vào mảng services (nếu có)
        var services = [];
        var serviceCheckboxes = form.querySelectorAll('.service-checkbox:checked');
        if (serviceCheckboxes.length > 0) {
            serviceCheckboxes.forEach(function(checkbox) {
                services.push(parseInt(checkbox.value));
            });
        }

        // Thêm các thành viên khác vào mảng other_members
        var otherMembers = [];
        var relativeGroups = form.querySelectorAll('.relative-input-group');
        relativeGroups.forEach(function(group) {
            var nameInput = group.querySelector('input[name="other_members.name"]').value;
            var relationshipInput = group.querySelector('input[name="other_members.relationship"]').value;
            otherMembers.push({
                name: nameInput,
                relationship: relationshipInput
            });
        });

        // Kiểm tra nếu tất cả các dịch vụ đã được chọn, loại bỏ trường services khỏi json
        if (serviceCheckboxes.length !== document.querySelectorAll('.service-checkbox').length) {
            jsonData.services = services;
        }

        jsonData.resident_id = residentId;
        jsonData.room_id = roomId;
        jsonData.final_price = finalPrice;
        jsonData.other_members = otherMembers;
        console.log('JSON DATA:', jsonData);

        fetch('/ApartmentManagement/admin/constract/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jsonData)
        })
            .then(function(response) {
                if (!response.ok) {
                    throw new Error('Có lỗi xảy ra khi tạo hợp đồng. Vui lòng thử lại sau.');
                }
            })
            .then(function(data) {
                console.log('Response:', data);
                alert('Hợp đồng đã được tạo thành công!');
                window.location.href = '/ApartmentManagement/admin/constracts/';
            })
            .catch(function(error) {
                console.error('Error:', error);
                alert('Đã xảy ra lỗi khi tạo hợp đồng. Vui lòng thử lại!');
            });
    }
</script>
