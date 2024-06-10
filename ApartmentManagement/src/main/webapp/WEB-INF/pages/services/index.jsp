<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1 class="h3">Dịch vụ chung cư</h1>
        <a class="btn btn-success" href="<c:url value="/services/add" />">
            <i class="bi bi-plus"></i> Thêm dịch vụ
        </a>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Tên dịch vụ</th>
                <th scope="col">Giá</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="service" items="${services}">
                <tr>
                    <td>${service.id}</td>
                    <td>${service.name}</td>
                    <td>${service.price}</td>

                    <td><c:choose>
                        <c:when test="${service.isActive == 1}">Đang hoạt động</c:when>
                        <c:otherwise>Không hoạt động</c:otherwise>
                    </c:choose>
                    </td>
                    <td>
                        <a class="btn btn-sm btn-primary" href="<c:url value="/services/edit/${service.id}" />">
                            <i class="bi bi-pencil"></i> Sửa
                        </a>
                        <button class="btn btn-sm btn-danger removeService" data-id="${service.id}">
                            <i class="bi bi-trash"></i> Xóa
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


