<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/17/2024
  Time: 2:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 800px;
            margin-top: 50px;
        }
        .card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .card-header {
            background-color: #ee91d6;
            color: white;
            border-radius: 10px 10px 0 0;
        }
        .table thead {
            background-color: #d9aac8;
            color: white;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-add {
            color: green;
            cursor: pointer;
        }
        .btn-remove {
            color: red;
            cursor: pointer;
        }
    </style>
    <script>
        function addMember() {
            const table = document.getElementById('memberTable');
            const row = table.insertRow();
            row.innerHTML = `
                <td><input type="text" name="memberName" class="form-control" required></td>
                <td><input type="text" name="memberRelationship" class="form-control" required></td>
                <td><span class="btn-remove" onclick="removeMember(this)"><i class="fa-solid fa-square-minus"></i></span></td>
            `;
        }

        function removeMember(element) {
            const row = element.parentElement.parentElement;
            row.remove();
        }
    </script>
<body>
<div class="container">
    <div class="card">
        <div class="card-header text-center">
            <h2>Chỉnh sửa thông tin hợp đồng</h2>
        </div>
        <div class="card-body">
            <h6 class="mb-4"><strong>Thông tin hợp đồng phòng:</strong> ${constract.roomId.roomNumber}</h6>
            <h6 class="mb-4"><strong>Tên khách hàng:</strong> ${constract.customerName}</h6>
            <form>
                <div class="form-group mb-4">
                    <label for="contractPrice"><strong>Giá hợp đồng:</strong></label>
                    <input type="number" id="contractPrice" name="final_price" class="form-control" value="${constract.finalPrice}" required>
                </div>

                <div class="form-group mb-4">
                    <label><strong>Thành viên hợp đồng:</strong></label>
                    <table class="table mt-3" id="memberTable">
                        <thead>
                        <tr>
                            <th>Tên</th>
                            <th>Mối quan hệ</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${memberConstract}" var="member">
                            <tr>
                                <td><input type="text" name="memberName" class="form-control" value="${member.name}" required></td>
                                <td><input type="text" name="memberRelationship" class="form-control" value="${member.relationship}" required></td>
                                <td><span class="btn-remove" onclick="removeMember(this)"><i class="fa-solid fa-square-minus"></i></span></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <span class="btn-add" onclick="addMember()">+ Thêm thành viên</span>
                </div>

                <div class="form-group mb-4">
                    <label><strong>Các dịch vụ:</strong></label>
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
                                    <input type="checkbox" name="services" value="${service.id}"
                                    <c:forEach items="${constractServices}" var="cs">
                                           <c:if test="${cs.id == service.id}">checked</c:if>
                                    </c:forEach>
                                    >
                                </td>
                                <td>${service.name}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>

