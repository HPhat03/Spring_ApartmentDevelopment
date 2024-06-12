<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/12/2024
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1 class="h3">Quản lý phòng</h1>
        <div class="d-flex align-items-center">
            <div class="form-group mr-3">
                <select id="filter" class="form-control">
                    <option value="all" selected>Tất cả</option>
                    <option value="active">Đang hoạt động</option>
                    <option value="inactive">Không hoạt động</option>
                    <option value="empty">Phòng trống</option>
                    <option value="rented">Phòng đã thuê</option>
                </select>
            </div>
            <a class="btn btn-success"  id="filterBtn">
                <i class="bi bi-plus"></i> Lọc phòng
            </a>
        </div>
        <a class="btn btn-success" href="<c:url value='/rooms/add' />">
            <i class="bi bi-plus"></i> Thêm phòng
        </a>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Số phòng</th>
                <th scope="col">Giá</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Tầng</th>
                <th scope="col">Phòng trống </th>
                <th scope="col">Hành động</th>
            </tr>
            </thead>
            <tbody id="roomTableBody">
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td>${room.id}</td>
                    <td>${room.roomNumber}</td>
                    <td>${String.format("%,d", room.price)} VNĐ</td>
                    <td>
                        <c:choose>
                            <c:when test="${room.isActive == 1}">Đang hoạt động</c:when>
                            <c:otherwise>Không hoạt động</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${room.floor.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${room.isBlank == 0}">Trống</c:when>
                            <c:otherwise>Đã thuê</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a class="btn btn-sm btn-primary" href="<c:url value='/rooms/edit/${room.id}' />">
                            <i class="bi bi-pencil"></i> Sửa
                        </a>
                        <button class="btn btn-sm btn-danger removeRoom" data-id="${room.id}">
                            <i class="bi bi-trash"></i> Xóa
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


