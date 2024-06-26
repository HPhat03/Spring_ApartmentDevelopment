<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 800px;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        .question-row {
            margin-bottom: 15px;
        }
        .btn-success {
            margin-left: 15px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .btn-danger {
            margin-top: 6px;
        }
    </style>

<body>
<div class="container">
    <h1 class="mb-4 text-center">Tạo phiếu khảo sát mới</h1>
    <form id="surveyForm">
        <div class="mb-3">
            <label for="startDate" class="form-label">Ngày bắt đầu:</label>
            <input type="date" class="form-control" id="startDate" name="startDate">
        </div>
        <div class="mb-3">
            <label for="endDate" class="form-label">Ngày kết thúc:</label>
            <input type="date" class="form-control" id="endDate" name="endDate">
        </div>
        <div class="d-flex align-items-center mb-3">
            <label class="mb-0 me-2">Câu hỏi khảo sát</label>
            <button type="button" class="btn btn-success" onclick="addQuestion()">+</button>
        </div>
        <div id="questionContainer">
            <div class="row question-row align-items-center">
                <div class="col-md-6">
                    <input type="text" class="form-control" name="questions[]" placeholder="Nhập câu hỏi">
                </div>
                <div class="col-md-3">
                    <select class="form-select" name="scoreBands[]">
                        <option value="BAND_5">Thang 5</option>
                        <option value="BAND_10">Thang 10</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <button type="button" class="btn btn-danger" onclick="removeQuestion(this)">x</button>
                </div>
            </div>
        </div>
        <c:url value="/admin/survey_request/add/" var="urlAdd"/>
        <c:url value="/admin/survey_request/" var="urlIndex"/>

        <button type="button" class="btn btn-primary mt-4" onclick="createSurvey('${urlAdd}', '${urlIndex}')">Tạo phiếu khảo sát</button>
    </form>
</div>

<script src="<c:url value="/js/survey_request.js" />"></script>
</body>

