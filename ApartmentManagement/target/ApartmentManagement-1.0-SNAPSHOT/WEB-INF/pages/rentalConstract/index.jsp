<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/13/2024
  Time: 12:11 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row mt-6">
    <div class="col-12">
        <div class="card my-4 mx-5">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                    <h6 class="text-white text-capitalize ps-3">DANH SÁCH HỢP ĐỒNG THUÊ NHÀ</h6>
                </div>
                <a class="btn btn-success px-3 mt-3" href="<c:url value='/admin/constracts/add' />">
                    <i class="bi bi-plus"></i> Tạo hợp đồng
                </a>
            </div>

            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Tên khách hàng</th>
                        <th scope="col">Số Phòng</th>
                        <th scope="col">Ngày kí kết</th>
                        <th scope="col">Trạng thái hợp đồng</th>
                        <th scope="col">Hành động</th>
                    </tr>
                    </thead>

                    <tbody >
                    <c:forEach var="con" items="${constracts}">
                        <tr>
                            <td>${con.id}</td>
                            <td>${con.customerName}</td>
                            <td>${con.roomId.roomNumber}</td>
                            <td>
                                <fmt:formatDate value="${con.createdDate}" pattern="dd-MM-yyyy " />
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${con.isActive == 1}">
                                        <Button class="btn-success border-radius-2xl shadow">
                                            Còn hiệu lực
                                        </Button>
                                    </c:when>
                                    <c:otherwise>
                                        <Button class="btn-outline-danger border-radius-2xl shadow-blur">
                                           Hết hạn
                                        </Button>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <a class="btn btn-sm btn-success" href="<c:url value='/admin/constracts/${con.id}' />">
                                    <i class="bi bi-pencil"></i>Chi tiết
                                </a>
                                <a class="btn btn-sm btn-primary" href="<c:url value='/admin/constracts/edit/${con.id}' />">
                                    <i class="bi bi-pencil"></i> Sửa
                                </a>
                                <c:url value="/admin/constracts/${con.id}/" var="urlDelete" />

                                <button class="btn btn-sm btn-danger delete-btn"  data-id="${conid}" onclick="deleteConstract('${urlDelete}', ${con.id})">
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


</div>
<script>
    function deleteConstract(url, ID) {
        console.log(url);
        if (confirm("Bạn có chắc chắn muốn xóa hợp đồng này không?")) {
            fetch(url, {
                method: "DELETE",
            })
                .then(response => {
                    if (response.status === 200) {
                        alert("Xóa hợp đồng thành công");
                        location.reload();
                    } else {
                        alert("Xóa hợp đồng thất bại");
                    }
                })
                .catch(error => {
                    alert("Có lỗi xảy ra khi xóa hợp đồng");
                });
        }
    }


</script>