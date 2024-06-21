<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/19/2024
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<style>
    .table th, .table td {
        text-align: center;
        vertical-align: middle;
    }
</style>

<div class="row mt-6">
    <div class="col-12">
        <div class="card my-4 mx-5">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                    <h6 class="text-white text-capitalize ps-3">Quản lí tủ đồ thông minh</h6>
                </div>
                <a class="btn btn-success px-3 mt-3" href="<c:url value='/cabinets/add' />">
                    <i class="bi bi-plus"></i> Tạo đơn mới
                </a>
                <form>
                    <label for="status">Trạng thái đơn:</label>
                    <select id="status" name="status" class="p-1">
                        <option value="RECEIVED" ${status == 'RECEIVED' ? 'selected' : ''}>Đã nhận</option>
                        <option value="PENDING" ${status == 'PENDING' ? 'selected' : ''}>Đang vận chuyển</option>
                    </select>

                    <button class="btn btn-outline-primary mt-3" type="submit">Lọc</button>
                </form>

            </div>

            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Số phòng</th>
                        <th scope="col">Mô tả</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Cập nhật</th>
                        <th scope="col">Hành động</th>
                    </tr>
                    </thead>
                    <tbody id="roomTableBody">
                    <c:forEach var="cab" items="${cabinets}">
                        <tr>
                            <td>${cab.id}</td>
                            <td>${cab.apartmentId.roomId.roomNumber}</td>
                            <td>${cab.decription}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${cab.status == 'PENDING'}">
                                        <button class="btn-success border-radius-2xl shadow">
                                            Đang vận chuyển
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn-outline-danger border-radius-2xl shadow-blur">
                                            Đã nhận
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:formatDate value="${cab.createdDate}" pattern="dd/MM/yyyy" />
                            </td>
                            <td>
                                <fmt:formatDate value="${cab.updatedDate}" pattern="dd/MM/yyyy" />
                            </td>
                            <td>
                                <a class="btn btn-sm btn-primary" href="<c:url value='/cabinets/${cab.id}' />">
                                    <i class="bi bi-pencil"></i> Sửa
                                </a>
                                <c:url value="/cabinets/${cab.id}" var="urlDelete" />

                                <button class="btn btn-sm btn-danger removeRoom" data-id="${cab.id}" onclick="deleteCabinet('${urlDelete}', ${cab.id})">
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
<div class="d-flex justify-content-center">
    <ul class="pagination align-items-center">
        <c:if test="${currentPage > 1}">
            <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}">Previous</a></li>
        </c:if>
        <c:forEach begin="1" end="${totalPages}" var="pageNumber">
            <c:choose>
                <c:when test="${pageNumber == currentPage}">
                    <li class="page-item active"><a class="page-link" href="?page=${pageNumber}">${pageNumber}</a>
                    </li>
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
<script src="<c:url value="/js/cabinet.js" /> "></script>