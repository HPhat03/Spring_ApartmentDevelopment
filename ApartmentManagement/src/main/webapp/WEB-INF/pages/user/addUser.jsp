    <%--
      Created by IntelliJ IDEA.
      User: FShop
      Date: 6/14/2024
      Time: 7:35 PM
      To change this template use File | Settings | File Templates.
    --%>

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <c:url value="/users/add/" var="url"/>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 600px;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-label {
            font-weight: bold;
        }
        .form-control {
            border-radius: 0.25rem;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .mb-4 {
            margin-bottom: 1.5rem !important;
        }
        .form-create {
            background-color: #e2bdca;
        }
    </style>

    <div class="container form-create mt-5">
        <h2 class="mb-4">TẠO NGƯỜI DÙNG</h2>

    <form id="myForm"  enctype="multipart/form-data">
            <div class="row mb-3">
                <div class="col">
                    <label for="firstname" class="form-label">Họ của người dùng</label>
                    <input type="text" class="form-control" id="firstname" name="firstname" required>
                </div>
                <div class="col">
                    <label for="lastname" class="form-label">Tên người dùng</label>
                    <input type="text" class="form-control" id="lastname" name="lastname" required>
                </div>
            </div>
            <div class="mb-3">
                <label for="birthdate" class="form-label">Ngày sinh</label>
                <input type="date" class="form-control" id="birthdate" name="birthdate" required>
            </div>
            <div class="mb-3">
                <label  class="form-label">Giới tính</label>
                <div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="male" value="1" checked required>
                        <label class="form-check-label" for="male">Nam</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="female" value="0" required>
                        <label class="form-check-label" for="female">Nữ</label>
                    </div>
                </div>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
            </div>
            <div class="mb-3">
                <label for="file" class="form-label">Ảnh đại diện</label>
                <input type="file" class="form-control" id="file" name="avatar">

            </div>
            <div class="mb-3">
                <label for="role" class="form-label">Vai trò</label>
                <select class="form-select" id="role" name="role" required>
                    <option value="RESIDENT">Resident</option>
                    <option value="ADMIN">Admin</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="username" class="form-label">Username*</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu*</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Xác nhận mật khẩu*</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Thêm người dùng mới</button>
        </form>
    </div>
    <script src="<c:url value="/js/user.js" /> "></script>