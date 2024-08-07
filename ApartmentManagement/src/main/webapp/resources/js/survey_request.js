function addQuestion() {
    var questionContainer = document.getElementById('questionContainer');
    var newQuestionRow = document.createElement('div');
    newQuestionRow.className = 'row question-row align-items-center';

    newQuestionRow.innerHTML = `
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
    `;
    questionContainer.appendChild(newQuestionRow);
}

function removeQuestion(button) {
    var questionRow = button.parentNode.parentNode;
    questionRow.parentNode.removeChild(questionRow);
}

function formatDateToISO(dateString) {
    var date = new Date(dateString);
    var day = date.getDate();
    var month = date.getMonth() + 1; // Tháng trả về từ 0 - 11, cần +1 để đúng
    var year = date.getFullYear();

    // Định dạng ngày và tháng thành 2 chữ số nếu cần
    day = day < 10 ? '0' + day : day;
    month = month < 10 ? '0' + month : month;

    return year + '-' + month + '-' + day; // Cập nhật để phù hợp với định dạng ISO 8601
}

function formatDateToDDMMYYYY(dateString) {
    var date = new Date(dateString);
    var day = date.getDate();
    var month = date.getMonth() + 1; // Tháng trả về từ 0 - 11, cần +1 để đúng
    var year = date.getFullYear();

    // Định dạng ngày và tháng thành 2 chữ số nếu cần
    day = day < 10 ? '0' + day : day;
    month = month < 10 ? '0' + month : month;

    return day + '/' + month + '/' + year; // Định dạng dd/MM/yyyy
}
function createSurvey(url, urlIndex) {
    var startDate = document.getElementById('startDate').value;
    var endDate = document.getElementById('endDate').value;
    var questions = [];
    var questionRows = document.getElementsByClassName('question-row');
    var isValid = true;

    // Xóa thông báo lỗi trước đó
    document.getElementById('startDateErr').textContent = '';
    document.getElementById('endDateErr').textContent = '';
    document.getElementById('questionErr').textContent = '';

    // Kiểm tra ngày bắt đầu có được nhập hay không
    if (!startDate) {
        document.getElementById('startDateErr').textContent = 'Vui lòng chọn ngày bắt đầu.';
        isValid = false;
    }

    // Kiểm tra ngày kết thúc có được nhập hay không
    if (!endDate) {
        document.getElementById('endDateErr').textContent = 'Vui lòng chọn ngày kết thúc.';
        isValid = false;
    }

    // Kiểm tra các câu hỏi có được nhập hay không
    for (var i = 0; i < questionRows.length; i++) {
        var questionInput = questionRows[i].querySelector('input[name="questions[]"]');
        var questionValue = questionInput.value.trim(); // Loại bỏ khoảng trắng để xử lý trường hợp nhập không nhập nội dung

        if (!questionValue) {
            document.getElementById('questionErr').textContent = 'Vui lòng nhập nội dung câu hỏi.';
            isValid = false;
            break; // Dừng vòng lặp nếu có câu hỏi nào không được nhập
        }

        var scoreBandSelect = questionRows[i].querySelector('select[name="scoreBands[]"]');
        var bandValue = scoreBandSelect.value;

        questions.push({
            question: questionValue,
            band: bandValue
        });
    }

    if (!isValid) {
        // Nếu có lỗi thì ngăn người dùng tiếp tục và hiển thị thông báo lỗi
        return;
    }

    // Dữ liệu gửi đi
    var data = {
        startDate: formattedStartDate,
        endDate: formattedEndDate,
        questions: questions
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(function(response) {
            if (!response.ok) {
                throw new Error('Có lỗi xảy ra khi tạo phiếu khảo sát. Vui lòng thử lại sau.');
            }
        })
        .then(function(data) {
            console.log('Response:', data);
            alert('Phiếu khảo sát đã được tạo thành công!');
            window.location.href = urlIndex;
        })
        .catch(function(error) {
            console.error('Error:', error);
            alert('Đã xảy ra lỗi khi tạo phiếu khảo sát. Vui lòng thử lại!');
        });

}
