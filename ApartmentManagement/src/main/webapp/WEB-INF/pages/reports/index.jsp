<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/17/2024
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                    <h6 class="text-white text-capitalize ps-3">Danh sách báo cáo của cư dân</h6>
                </div>
                      </div>


            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Phòng</th>
                        <th scope="col">Tiêu đề</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Hành động</th>

                    </tr>
                    </thead>
                    <tbody id="roomTableBody">
                    <c:forEach var="r" items="${reports}">
                        <tr>
                            <td>${r.id}</td>
                            <td>${r.apartmentId.roomId.roomNumber}</td>

                            <td>
                                ${r.title}
                            </td>
                            <td>
                                <fmt:formatDate value="${r.createdDate}" pattern="dd-MM-yyyy" />
                            </td>
                            <td>

                                    <a class="btn btn-sm border-radius-2xl btn-success"
                                       href="<c:url value='/admin/reports/${r.id}' />">
                                        <i class="bi bi-person-plus"></i> Xem chi tiết
                                    </a>
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
</div>
<script src="<c:url value="/js/rooms.js" /> "></script>

