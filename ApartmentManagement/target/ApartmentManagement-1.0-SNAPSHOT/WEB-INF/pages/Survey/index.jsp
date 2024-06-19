<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/17/2024
  Time: 9:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    .table th, .table td {
        text-align: center;
        vertical-align: middle;
    }
</style>

<div class="row mt-6">
    <div class="col-12">

        <div class="card my-4 mx-4">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 ">
                <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                    <h6 class="text-white text-capitalize text-center ps-3">Quản lí các phiếu khảo sát</h6>
                </div>

            </div>
            <div class="d-flex align-items-center">
                <a class="btn btn-success mt-4 mx-3" href="<c:url value='/survey_request/add' />">
                    <i class="bi bi-plus"></i> Tạo phiếu khảo sát mới
                </a>
            </div>


            <div class="card-body px-0 pb-2">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Mã phiếu </th>
                            <th scope="col">Thực hiện</th>
                            <th scope="col">Ngày bắt đầu</th>
                            <th scope="col">Ngày kết thúc</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="r" items="${requests}">
                            <tr>
                                <td>${r.id}</td>
                                <td>
                                    <c:forEach items="${admins}" var="a">
                                        <c:choose>
                                            <c:when test="${r.adminId.userId == a.id}">
                                                ${a.name}
                                            </c:when>

                                        </c:choose>
                                    </c:forEach>
                                </td>
                                <td>${r.startDate}</td>
                                <td>${r.endDate}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${r.isActive == 1}">
                                            <Button class="btn-success border-radius-2xl shadow">Đang hoạt động</Button>
                                        </c:when>
                                        <c:otherwise>
                                            <Button class="btn-outline-danger border-radius-2xl shadow">Không hoạt động</Button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a class="btn btn-sm btn-secondary" href="<c:url value='/survey_request/${r.id}' />">
                                        <i class="bi bi-pencil"></i> Xem chi tiết
                                    </a>
                                    <a class="btn btn-sm btn-primary" href="<c:url value='/services/edit/${r.id}' />">
                                        <i class="bi bi-pencil"></i> Sửa
                                    </a>
                                    <c:url value="/survey_request/${r.id}" var="urlDelete" />
                                    <button class="btn btn-sm btn-danger removeRoom" data-id="${r.id}" onclick="deleteSurvey('${urlDelete}', ${r.id})">
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
<script>
    function deleteSurvey(urlDelete, surveyId) {
        if (confirm('Bạn có chắc chắn muốn xóa phiếu khảo sát này?')) {
            fetch(urlDelete, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (response.ok) {
                        alert('Xóa phiếu khảo sát thành công!');
                        window.location.reload(); // Reload trang sau khi xóa thành công
                    } else {
                        throw new Error('Xóa phiếu khảo sát thất bại.');
                    }
                })
                .catch(error => {
                    console.error('Lỗi khi xóa phiếu khảo sát:', error);
                    alert('Đã xảy ra lỗi khi xóa phiếu khảo sát. Vui lòng thử lại sau.');
                });
        }
    }
</script>
