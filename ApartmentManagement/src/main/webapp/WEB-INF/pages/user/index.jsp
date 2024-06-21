<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/14/2024
  Time: 3:27 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>

</style>

<div class="row mt-6">
    <div class="col-12">
        <div class="card my-4 mx-4">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 ">
                <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                    <h6 class="text-white text-capitalize text-center ps-3">Quản lí người dùng</h6>
                </div>
            </div>

            <div class="d-flex align-items-center">
                <a class="btn btn-success mt-4 mx-3" href="<c:url value='/users/add' />">
                    <i class="bi bi-plus"></i> Thêm người dùng mới
                </a>
                <div class="dropdown mt-3">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="roleFilterDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                        Lọc theo vai trò
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="roleFilterDropdown" id="roleDropdown">
                        <li>
                            <a class="dropdown-item" href="?role=ADMIN">Quản trị viên</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="?role=RESIDENT">Cư dân</a>
                        </li>
                    </ul>
                </div>

                <div class="d-flex align-items-center search-bar mt-3">
                    <form action="<c:url value=''/>" class="d-flex align-items-center mt-3">
                        <input class="form-control me-2" type="text" name="kw" placeholder="Nhập tên người dùng.." value="${param.kw}">
                        <button class="btn btn-primary col-2 mt-3" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </form>
                </div>

            </div>

            <div class="card-body px-0 pb-2">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Mã số</th>
                            <th scope="col">Họ và tên</th>
                            <th scope="col">Tên đăng nhập</th>
                            <th scope="col">Vai trò</th>
                            <th scope="col">Ảnh đại diện</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.username}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.role == 'ADMIN'}">
                                            <span class="bold-text">Quản trị viên</span>
                                        </c:when>
                                        <c:when test="${user.role == 'RESIDENT'}">
                                            <span class="bold-text">Cư dân</span>
                                        </c:when>
                                        <c:otherwise>
                                            ${user.role}
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${not empty user.avatar}">
                                            <img src="${user.avatar}" alt="Avatar" class="avatar-image">
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Nếu không có avatar, có thể hiển thị một ảnh mặc định hoặc thông báo rỗng -->
                                            <span>Không có ảnh đại diện</span>
                                        </c:otherwise>
                                    </c:choose>

                                </td>
                                <td >
                                    <c:choose>
                                        <c:when test="${user.isActive == 1}">
                                            <Button onclick="changeStatus(${user.id},0)" class="btn-success border-radius-2xl shadow">Đang hoạt động</Button>
                                        </c:when>
                                        <c:otherwise>
                                            <Button onclick="changeStatus(${user.id},1)" class="btn-outline-danger border-radius-2xl shadow">Không hoạt động</Button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a class="btn btn-sm btn-outline-success" href="<c:url value='/users/${user.id}' />">
                                        <i class="bi bi-pencil"></i>Hồ sơ
                                    </a>
                                    <a class="btn btn-sm btn-primary" href="<c:url value='/users/edit/${user.id}' />">
                                        <i class="bi bi-pencil"></i> Sửa
                                    </a>
                                    <c:url value="/users/${user.id}/" var="urlDelete" />

                                    <button class="btn btn-sm btn-danger removeRoom" data-id="${user.id}" onclick="deleteUser('${urlDelete}', ${user.id})">
                                        <i class="bi bi-trash"></i> Xóa
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="d-flex justify-content-center">
                <ul class="pagination align-items-center">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}">Previous</a></li>
                    </c:if>
                    <c:forEach begin="1" end="${totalPages}" var="pageNumber">
                        <c:choose>
                            <c:when test="${pageNumber == currentPage}">
                                <li class="page-item active"><a class="page-link" href="?page=${pageNumber}">${pageNumber}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="?page=${pageNumber}">${pageNumber}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}">Next</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/js/user.js" /> "></script>
