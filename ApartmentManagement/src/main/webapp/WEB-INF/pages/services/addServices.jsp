<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/11/2024
  Time: 2:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/services/add" var="action"/>

<div class="container m-4">
    <h1 class="h3 mb-3">${service.id != null ? 'Chỉnh sửa' : 'Thêm mới'} dịch vụ</h1>
    <form:form method="post" action="${action}" modelAttribute="service">
        <form:hidden path="id"/>
        <form:errors path="name" cssClass="alert alert-danger" role="alert"/>
        <div class="form-floating mb-3">
            <form:input class="form-control" id="name" placeholder="Tên dịch vụ" path="name"/>
            <label for="name">Tên dịch vụ</label>
        </div>
        <form:errors path="price" cssClass="alert alert-danger" role="alert"/>
        <div class="form-floating mb-3">
            <form:input class="form-control" id="price" placeholder="Giá" path="price"/>
            <label for="price">Giá</label>
        </div>
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="isActive" name="isActive" value="1" ${service.isActive == 1 ? 'checked' : ''}>
            <label class="form-check-label" for="isActive">Đang hoạt động</label>
        </div>
        <div class="mb-3">
            <button type="submit" class="btn btn-info">
                    ${service.id != null ? 'Cập nhật dịch vụ' : 'Thêm mới'}
            </button>
            <a href="<c:url value="/services/" />" class="btn btn-secondary ms-2">Quay lại</a>
        </div>
    </form:form>
</div>
