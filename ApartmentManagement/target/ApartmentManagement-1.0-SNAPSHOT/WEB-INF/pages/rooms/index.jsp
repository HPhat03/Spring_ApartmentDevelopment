<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h6 class="text-white text-capitalize ps-3">Quản lí phòng</h6>
                </div>
                <form >
                    <label for="status">Trạng thái phòng:</label>
                    <select id="status" name="status" class="p-1">
                        <option value="all" ${status == 'all' ? 'selected' : ''}>Tất cả</option>
                        <option value="active" ${status == 'active' ? 'selected' : ''}>Hoạt động</option>
                        <option value="inactive" ${status == 'inactive' ? 'selected' : ''}>Không hoạt động</option>
                        <option value="blank" ${status == 'blank' ? 'selected' : ''}>Phòng trống</option>
                        <option value="iblank" ${status == 'iblank' ? 'selected' : ''}>Đã thuê</option>
                    </select>

                    <button class="btn btn-outline-primary mt-3" type="submit">Lọc</button>
                </form>


                <a class="btn btn-success" href="<c:url value='/rooms/add' />">
                    <i class="bi bi-plus"></i> Thêm phòng
                </a>

            </div>


            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Số phòng</th>
                        <th scope="col">Giá</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Tầng</th>
                        <th scope="col">Phòng trống</th>
                        <th scope="col">Hành động</th>

                    </tr>
                    </thead>
                    <tbody id="roomTableBody">
                    <c:forEach var="room" items="${rooms}">
                        <tr>
                            <td>${room.id}</td>
                            <td>${room.roomNumber}</td>
                            <td>${String.format("%,d", room.price)} VNĐ</td>
                            <td>
                                <c:choose>
                                    <c:when test="${room.isActive == 1}">
                                        <Button class="btn-success border-radius-2xl shadow">
                                            Đang hoạt động
                                        </Button>
                                    </c:when>
                                    <c:otherwise>
                                        <Button class="btn-outline-danger border-radius-2xl shadow-blur">
                                            Đang sửa chữa
                                        </Button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${room.floor.name}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${room.isBlank == 0}">
                                        <a class="btn btn-sm border-radius-2xl btn-success"
                                           href="<c:url value='/rooms/rent/${room.id}' />">
                                        <i class="bi bi-person-plus"></i> Tạo hợp đồng
                                    </a></c:when>
                                    <c:otherwise>
                                    <a class="btn btn-sm border-radius-2xl btn-danger"
                                       href="<c:url value='/rooms/rent/${room.id}' />">
                                        <i class="bi bi-person-plus"></i> Đã thuê
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a class="btn btn-sm btn-primary" href="<c:url value='/rooms/edit/${room.id}' />">
                                    <i class="bi bi-pencil"></i> Sửa
                                </a>
                                <button class="btn btn-sm btn-danger removeRoom" data-id="${room.id}">
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
