
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>
        Đăng nhập
    </title>
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
    <link id="pagestyle"  href="<c:url value="/css/material-dashboard.css?v=3.1.0"/>"  rel="stylesheet" />

</head>

<body class="bg-gray-200">

<main class="main-content  mt-0">
    <div class="page-header align-items-start min-vh-100" style="background-image: url('https://images.unsplash.com/photo-1497294815431-9365093b7331?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80');">
        <span class="mask bg-gradient-dark opacity-6"></span>
        <div class="container my-auto">
            <div class="row">
                <div class="col-lg-4 col-md-8 col-12 mx-auto">
                    <div class="card z-index-0 fadeIn3 fadeInBottom">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">Đăng nhập</h4>

                            </div>
                        </div>
                        <div class="card-body">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p> Lỗi đăng nhập</p>
                                </div>

                            </c:if>
                            <form  action="<c:url value="/login"/>"  method="post" role="form" class="text-start">
                                <div class="input-group input-group-outline my-3">
                                    <label for="username">Username</label>
                                    <input type="text" id="username" name="username" class="form-control">
                                </div>
                                <div class="input-group input-group-outline mb-3">
                                    <label for="password" >Mật khẩu</label>
                                    <input type="password" id="password" name="password" class="form-control">
                                </div>

                                <div class="text-center">
                                    <button type="submit"  class="btn bg-gradient-primary w-100 my-4 mb-2">Đăng nhập</button>
                                </div>
                                <p class="mt-4 text-sm text-center">
                                    Don't have an account?
                                    <a href="" class="text-primary text-gradient font-weight-bold">Đăng ký</a>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</main>

</body>

</html>