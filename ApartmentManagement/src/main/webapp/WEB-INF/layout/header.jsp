
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="javascript:void(0)">MY SALE APP</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/"/>">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/services/"/>">Dịch vụ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/rooms/"/>">Phòng</a>
                </li>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link " href="<c:url value="/rooms/"/>">Phòng</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="<c:url value="/reports/"/>">Thống kê, báo cáo</a>--%>
<%--                </li>--%>
            </ul>
        </div>
    </div>
</nav>