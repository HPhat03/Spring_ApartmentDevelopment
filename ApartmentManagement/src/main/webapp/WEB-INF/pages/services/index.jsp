<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    .table th, .table td {
        text-align: center; /* Căn giữa chữ trong các ô */
        vertical-align: middle; /* Căn giữa theo chiều dọc */
    }
</style>

<div class="row mt-6">
    <div class="col-12">

        <div class="card my-4 mx-4">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 ">
                <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                    <h6 class="text-white text-capitalize text-center ps-3">Quản lí dịch vụ</h6>
                </div>

            </div>
            <a class="btn btn-success mt-3 col-2 mx-3" href="<c:url value="/services/add" />">
                <i class="bi bi-plus"></i> Thêm dịch vụ
            </a>

            <div class="card-body px-0 pb-2">
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

                                <td>
                                    <c:choose>
                                        <c:when test="${service.isActive == 1}"><Button class="btn-success border-radius-2xl shadow">
                                            Đang hoạt động</Button></c:when>
                                        <c:otherwise><Button class="btn-outline-danger border-radius-2xl shadow">
                                            Không hoạt động</Button></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a class="btn btn-sm btn-primary" href="<c:url value='/services/edit/${service.id}' />">
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
        </div>
    </div>
</div>
