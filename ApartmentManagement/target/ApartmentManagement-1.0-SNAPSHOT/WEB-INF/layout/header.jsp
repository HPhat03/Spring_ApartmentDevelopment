
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %><!-- sidebar.jsp -->

<style>
    .sidenav {
        overflow: hidden; /* Tắt thanh cuộn */
    }
</style>
<aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark" id="sidenav-main">
    <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqs8KJMo6kdez1J6yKhGemIfPq5dS7G3TuBg&s" class="navbar-brand-img h-100" alt="main_logo">
            <span class="ms-1 font-weight-bold text-white">Quản lý chung cư</span>
        </a>
    </div>
    <hr class="horizontal light mt-0 mb-2">
    <div class="collapse navbar-collapse w-auto" id="sidenav-collapse-main">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link text-white active bg-gradient-primary" href="<c:url value="/"/>">
                    <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                        <i class="material-icons opacity-10">dashboard</i>
                    </div>
                    <span class="nav-link-text ms-1">Trang chủ</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="<c:url value="/services/?page=1"/>">
                    <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                        <i class="material-icons opacity-10">table_view</i>
                    </div>
                    <span class="nav-link-text ms-1">Dịch vụ</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="<c:url value="/rooms/?page=1"/>">
                    <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                        <i class="material-icons opacity-10">receipt_long</i>
                    </div>
                    <span class="nav-link-text ms-1">Phòng</span>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link text-white" href="#">
                    <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                        <i class="material-icons opacity-10">person</i>
                    </div>
                    <span class="nav-link-text ms-1">Hồ sơ</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="<c:url value="/constracts/"/>">
                    <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                        <i class="material-icons opacity-10">format_textdirection_r_to_l</i>
                    </div>
                    <span class="nav-link-text ms-1">Hợp đồng</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="#">
                    <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                        <i class="material-icons opacity-10">view_in_ar</i>
                    </div>
                    <span class="nav-link-text ms-1">Tủ đồ thông minh</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="<c:url value="/users/"/>">
                    <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                        <i class="material-icons opacity-10">person</i>
                    </div>
                    <span class="nav-link-text ms-1">Người dùng </span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="<c:url value="/stats/"/>">
                    <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                        <i class="material-icons opacity-10">person</i>
                    </div>
                    <span class="nav-link-text ms-1">Thống kê, báo cáo</span>
                </a>
            </li>
        </ul>
    </div>

    <div class="sidenav-footer position-absolute w-100 bottom-0">
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.name == null}">
            <div class="mx-3">
                <a  class="btn bg-gradient-primary w-100" type="button" href="<c:url value="/login"/>">Đăng nhập</a>
            </div>
        </c:when>
        <c:when test="${pageContext.request.userPrincipal.name != null}">

            <li class="nav-item">

                <a class="nav-link text-info" href="<c:url value="/" />">Chào ${pageContext.request.userPrincipal.name}!</a>
            </li>
            <div class="mx-3">
                <a  class="btn bg-gradient-primary w-100" type="button" href="<c:url value="/logout"/>">Đăng xuất</a>
            </div>
        </c:when>
    </c:choose>
    </div>


</aside>

