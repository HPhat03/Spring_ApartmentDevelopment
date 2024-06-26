<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/12/2024
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/admin/rooms/add" var="action"/>

<div class="container mt-4">
    <h1 class="h3"><c:choose>
        <c:when test="${room.id == null}">
            Thêm phòng mới
        </c:when>
        <c:otherwise>
            Cập nhật thông tin phòng
        </c:otherwise>
    </c:choose></h1>
    <form:form action="${action}" method="post" modelAttribute="room">
        <div class="form-group">
            <label for="roomNumber">Số phòng:</label>
            <form:input path="roomNumber" id="roomNumber" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="price">Giá:</label>
            <form:input path="price" id="price" class="form-control"/>
        </div>
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="isActive" name="isActive" value="1" ${room.isActive == 1 ? 'checked' : ''}>
            <label class="form-check-label" for="isActive">Đang hoạt động</label>
        </div>
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="isBlank" name="isBlank" value="1" ${room.isBlank == 1 ? 'checked' : ''}>
            <label class="form-check-label" for="isBlank">Đang trống</label>
        </div>

        <div class="form-group">
            <label for="floor">Tầng:</label>
            <form:select path="floor.id" id="floor" class="form-control">
                <form:option value="" label="-- Chọn tầng --"/>
                <c:forEach items="${floors}" var="floor">
                    <c:choose>
                        <c:when test="${floor.id == room.floor.id}">
                            <form:option value="${floor.id}" label="${floor.name}" selected="selected"/>
                        </c:when>
                        <c:otherwise>
                            <form:option value="${floor.id}" label="${floor.name}"/>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </form:select>
        </div>

        <button type="submit" class="btn btn-primary mt-3"><c:choose>
            <c:when test="${room.id == null}">
                Thêm phòng
            </c:when>
            <c:otherwise>
                Cập nhật
            </c:otherwise>
        </c:choose></button>

    </form:form>
</div>


