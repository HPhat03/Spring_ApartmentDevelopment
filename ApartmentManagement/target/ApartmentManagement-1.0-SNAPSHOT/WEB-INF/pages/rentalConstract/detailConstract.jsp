<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/16/2024
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="main-content position-relative max-height-vh-100 h-100">

    <div class="container-fluid px-2 px-md-4">
        <div class="page-header min-height-250 border-radius-xl mt-4" style="background-image: url('https://images.unsplash.com/photo-1531512073830-ba890ca4eba2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1920&q=80');">
            <h4 class="mask bg-gradient-primary opacity-6"></h4>
        </div>
        <div class="card card-body mx-3 mx-md-4 mt-n6">
            <div class="row gx-4 mb-2">

                <div class="col-auto my-auto">
                    <div class="h-100">
                        <h5 class="mb-1">
                            THÔNG TIN HỢP ĐỒNG
                        </h5>
                        <p class="mb-0 font-weight-normal text-sm">

                        </p>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="row">

                    <div class="col-12 col-xl-4">
                        <div class="card card-plain h-100">
                            <div class="card-header pb-0 p-3">
                                <div class="row">
                                    <div class="col-md-8 d-flex align-items-center">
                                        <h6 class="mb-0">Thông tin hợp đồng phòng ${constract.roomId.roomNumber}</h6>
                                    </div>
                                    <div class="col-md-4 text-end">
                                        <a href="javascript:;">
                                            <i class="fas fa-user-edit text-secondary text-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Edit Profile"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body p-3">

                                <hr class="horizontal gray-light my-4">
                                <ul class="list-group">
                                    <li class="list-group-item border-0 ps-0 text-sm">
                                        <strong class="text-dark">Ngày tạo:</strong>
                                        <fmt:formatDate value="${constract.createdDate}" pattern="dd-MM-yyyy" />
                                    </li>
                                    <li class="list-group-item border-0 ps-0 text-sm"><strong class="text-dark">Phòng thuê: </strong> &nbsp;${constract.roomId.roomNumber}</li>
                                    <li class="list-group-item border-0 ps-0 text-sm"><strong class="text-dark">Tên khách hàng:</strong>${constract.customerName} &nbsp; </li>
                                    <li class="list-group-item border-0 ps-0 text-sm"><strong class="text-dark">Giá phòng:</strong> ${String.format("%,d", constract.finalPrice)} VNĐ</li>
                                    <li class="list-group-item border-0 ps-0 text-sm"><strong class="text-dark">Dịch vụ:</strong>
                                        <c:forEach items="${constractServices}" var="service" varStatus="loop">
                                            <c:if test="${loop.index > 0}">, </c:if>
                                            ${service.name}
                                        </c:forEach>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>
