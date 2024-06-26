<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    .card {
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .card-header {
        background-color: #007bff;
    }

    .card-header h6 {
        font-size: 1.25rem;
        padding: 10px;
        color: white;
        margin-bottom: 0;
    }

    .form-group {
        margin-bottom: 1.5rem;
    }

    .form-check {
        margin-bottom: 1rem;
    }

    .form-check-label {
        padding-left: 1.5rem;
    }

    .btn-primary {
        background-color: #007bff;
        border-color: #007bff;
        color: white;
        padding: 10px 20px;
        border-radius: 4px;
    }

    .btn-primary:hover {
        opacity: 0.8;
    }

    .pagination {
        margin-top: 2rem;
    }

    .pagination .page-item {
        margin-right: 5px;
    }

    .pagination .page-link {
        color: #007bff;
    }

    .pagination .page-item.active .page-link {
        background-color: #007bff;
        border-color: #007bff;
    }
</style>

<div class="row mt-6">
    <div class="col-12">
        <div class="card my-4 mx-5">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                    <h6 class="text-white text-capitalize ps-3">${cabinet.id != null ? 'Chỉnh sửa' : 'Thêm mới'} đơn hàng</h6>
                </div>
            </div>
            <c:url value="/admin/cabinets/add" var="ac" />
            <form:form action="${ac}" method="post" modelAttribute="cabinet">
                <form:hidden path="id"/>
<%--                <p>${contract}</p>--%>
                <div class="card-body mx-3">
                    <div class="form-group">
                        <label for="apartmentId">Phòng:</label>
                        <c:choose>
                            <c:when test="${cabinet.id != null}">
                                <form:hidden path="apartmentId.id" id="apartmentId" class="form-control" value="${contract.id}" readonly="true"/>
                                <input id="apartmentId" class="form-control" value="${contract.roomId.roomNumber}" readonly="true"/>
                            </c:when>
                            <c:otherwise>
                                <form:select path="apartmentId.id" id="apartmentId" class="form-control">
                                    <option value="" label="-- Chọn phòng --"></option>
                                    <c:forEach items="${contracts}" var="con">
                                        <option value="${con.id}" ${contract.id == con.id ? 'selected' : ''}>${con.roomId.roomNumber} - ${con.residentId.apartmentUser.name}</option>
                                    </c:forEach>
                                </form:select>
                            </c:otherwise>
                        </c:choose>
                    </div>


                    <div class="form-group">
                        <label for="decription">Mô tả:</label>
                        <form:input path="decription" id="decription" class="form-control"/>
                    </div>

<%--                    <p> ${cabinet.status}</p>--%>
                    <div class="form-check mb-3">
                        <form:radiobutton path="status" id="isPending" value="PENDING" />
                        <label class="form-check-label" for="isPending">Đang chờ nhận</label>
                    </div>

                    <div class="form-check mb-3">
                        <form:radiobutton path="status" id="isReceived" value="RECIEVCED" />
                        <label class="form-check-label" for="isReceived">Đã nhận</label>
                    </div>

                    <c:choose>
                        <c:when test="${cabinet.id != null}">
                            <div class="form-group">
                                <label for="createdDate">Ngày tạo:</label>
                                <fmt:formatDate value="${cabinet.createdDate}" pattern="dd/MM/yyyy" var="formattedCreatedDate" />
                                <form:input path="createdDate" value="${formattedCreatedDate}" id="createdDate" class="form-control" type="text" readonly="true"/>
                            </div>

                            <div class="form-group">
                                <label for="updatedDate">Ngày cập nhật:</label>
                                <fmt:formatDate value="${cabinet.updatedDate}" pattern="dd/MM/yyyy" var="formattedUpdatedDate" />
                                <form:input path="updatedDate" value="${formattedUpdatedDate}" id="updatedDate" class="form-control" type="text" readonly="true"/>
                            </div>
                        </c:when>
                    </c:choose>


                    <button type="submit" class="btn btn-primary mt-3">${cabinet.id != null ? 'Cập nhật' : 'Tạo mới'}</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
