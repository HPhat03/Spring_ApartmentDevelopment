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
                    <h6 class="text-white text-capitalize text-center ps-3">Quản lí dịch vụ</h6>
                </div>

            </div>
            <div class="d-flex align-items-center">
                <a class="btn btn-success mt-4 mx-3" href="<c:url value='/admin/services/add' />">
                    <i class="bi bi-plus"></i> Thêm dịch vụ
                </a>
                <form action="<c:url value=''/>" class="d-flex  align-items-center mt-3">
                    <input class="form-control me-2" type="text" name="kw" placeholder="Nhập tên dich vụ.." value="${param.kw}">
                    <button class="btn btn-primary col-4 mt-3" type="submit">Tìm kiếm</button>
                </form>
            </div>


            <div class="card-body px-0 pb-2">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Tên dịch vụ</th>
                            <th scope="col">Giá</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="service" items="${services}">
                            <tr>
                                <td>${service.id}</td>
                                <td>${service.name}</td>
                                <td>${service.price}</td>

                                <td>
                                    <c:choose>
                                        <c:when test="${service.isActive == 1}"><Button class="btn-success border-radius-2xl shadow">
                                            Đang hoạt động</Button></c:when>
                                        <c:otherwise><Button class="btn-outline-danger border-radius-2xl shadow">
                                            Không hoạt động</Button></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a class="btn btn-sm btn-primary" href="<c:url value='/admin/services/edit/${service.id}' />">
                                        <i class="bi bi-pencil"></i> Sửa
                                    </a>
                                    <c:url value="/admin/services/${service.id}" var="urlDelete" />

                                    <button class="btn btn-sm btn-danger removeRoom" data-id="${service.id}" onclick="deleteRoom('${urlDelete}', ${service.id})">
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
<%--        <h1>Total page : ${totalPages}</h1>--%>
<%--        <h1>CUrrent page : ${currentPage}</h1>--%>
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
    function deleteRoom(url,roomId) {
        console.log(url);
        if (confirm("Bạn có chắc chắn muốn xóa dịch vụ này không?")) {
            fetch(url, {
                method: "DELETE",
            })
                .then(response => {
                    if (response.status === 200) {
                        alert("Xóa dịch vụ thành công");
                        location.reload();
                    } else {
                        console.log("id rooom:", roomId);
                        alert("Xóa dịch vụ thất bại");
                    }
                })
                .catch(error => {
                    console.log("id rooom:", roomId);
                    console.error("Loi fetch api:", error);
                    alert("Có lỗi xảy ra khi xóa dịch vụ");
                });
        }
    }
</script>
