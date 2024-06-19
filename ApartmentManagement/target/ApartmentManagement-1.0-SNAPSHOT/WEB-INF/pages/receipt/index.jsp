<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/20/2024
  Time: 4:45 AM
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
                    <h6 class="text-white text-capitalize ps-3">Quản lí HÓA ĐƠN</h6>
                </div>
                <a class="btn btn-success px-3 mt-3" href="<c:url value='/receipts/add' />">
                    <i class="bi bi-plus"></i> Tạo hóa đơn mới
                </a>


            </div>

            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Mã hóa đơn</th>
                        <th scope="col">Số phòng</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Thành tiền</th>
                        <th scope="col">Hành động</th>
                    </tr>
                    </thead>
                    <tbody id="roomTableBody">
                    <c:forEach var="re" items="${receipts}">
                        <tr>
                            <td>${re.id}</td>
                            <td>${re.apartmentId.roomId.roomNumber}</td>
                            <td><fmt:formatDate value="${re.createdDate}" pattern="dd/MM/yyyy" /></td>

                            <td>${String.format("%,d", re.total)} VNĐ</td>


                            <td>
                                <a class="btn btn-sm btn-success" href="<c:url value='/receipts/${re.id}' />">
                                    <i class="bi bi-pencil"></i> Chi tiết
                                </a>
                                <a class="btn btn-sm btn-primary" href="<c:url value='' />">
                                    <i class="bi bi-pencil"></i> Sửa
                                </a>
                                <c:url value="/cabinets/${re.id}" var="urlDelete" />

                                <button class="btn btn-sm btn-danger removeRoom" data-id="${re.id}" onclick="deleteCabinet('${urlDelete}', ${re.id})">
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

