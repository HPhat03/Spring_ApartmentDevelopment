<%--
  Created by IntelliJ IDEA.
  User: FShop
  Date: 6/15/2024
  Time: 2:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
        .form-label {
            font-weight: bold;
            display: block;
            margin-bottom: 0.5rem;
        }
        .form-control {
            width: 100%;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
            transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }
        .form-control:focus {
            outline: none;
            border-color: #80bdff;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
        }
        .btn {
            display: inline-block;
            font-weight: 400;
            color: #fff;
            text-align: center;
            vertical-align: middle;
            user-select: none;
            background-color: #007bff;
            border: 1px solid transparent;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            line-height: 1.5;
            border-radius: 0.25rem;
            transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>

<div class="container">
    <h2>Chỉnh sửa hồ sơ</h2>
    <form id="editProfileForm"  >
        <div class="form-group">
            <label for="firstName" class="form-label">Họ của người dùng</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="${user.lastName}" data-old="${user.lastName}" required>
        </div>
        <div class="form-group">
            <label for="lastName" class="form-label">Tên của người dùng</label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="${user.firstName}"  data-old="${user.firstName}" required>
        </div>
<%--        <div class="form-group">--%>
<%--            <label for="birthdate" class="form-label">Ngày sinh</label>--%>
<%--            <input type="date" class="form-control" id="birthdate" name="birthdate" value="${user.birthdate}"  data-old="${user.birthdate}" required>--%>
<%--        </div>--%>
        <div class="form-group">
            <label class="form-label">Giới tính</label>
            <div>
                <input type="radio" id="male" name="gender" value="male" ${user.gender == 'Nam' ? 'checked' : ''} />
                <label for="male">Nam</label>
                <input  type="radio" id="female" name="gender" value="female" ${user.gender == 'Nữ' ? 'checked' : ''} />
                <label for="female">Nữ</label>

            </div>
        </div>
        <div class="form-group">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.email}" data-old="${user.email}">
        </div>
        <div class="form-group">
            <label for="phone" class="form-label">Số điện thoại</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}" data-old="${user.phone}"required>
        </div>
<%--        <div class="form-group">--%>
<%--            <label for="avatar" class="form-label">Ảnh đại diện</label>--%>
<%--            <input type="file" class="form-control" id="avatar" name="avatar">--%>
<%--        </div>--%>
        <button type="submit" class="btn btn-dribbble">Lưu thay đổi</button>
    </form>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var form = document.getElementById('editProfileForm');

        form.addEventListener('submit', function (event) {
            event.preventDefault(); // Ngăn form gửi yêu cầu mặc định của trình duyệt

            var firstNameInput = document.getElementById('firstName');
            var firstName = firstNameInput.value;
            var originalFirstName = firstNameInput.getAttribute('data-old');

            var lastNameInput = document.getElementById('lastName');
            var lastName = lastNameInput.value;
            var originalLastName = lastNameInput.getAttribute('data-old');

            // var birthdateInput = document.getElementById('birthdate');
            // var birthdate = birthdateInput.value;
            // var originalBirthdate = birthdateInput.getAttribute('data-old');

            var gender = document.querySelector('input[name="gender"]:checked').value;

            var emailInput = document.getElementById('email');
            var email = emailInput.value;
            var originalEmail = emailInput.getAttribute('data-old');

            var phoneInput = document.getElementById('phone');
            var phone = phoneInput.value;
            var originalPhone = phoneInput.getAttribute('data-old');

            // Tạo đối tượng dữ liệu JSON để gửi lên server
            var data = {};

            // Kiểm tra từng trường và thêm vào data nếu có thay đổi
            if (firstName !== originalFirstName) {
                data.firstname = firstName;
            }
            if (lastName !== originalLastName) {
                data.lastname = lastName;
            }
            // if (birthdate !== originalBirthdate) {
            //     data.birthdate = birthdate;
            // }
            if (email !== originalEmail) {
                data.email = email;
            }
            if (phone !== originalPhone) {
                data.phone = phone;
            }
            console.log("DATA",data);
            // Gửi yêu cầu PATCH lên server nếu có dữ liệu để gửi
            if (Object.keys(data).length > 0) {
                fetch(`/ApartmentManagement/admin/user/${user.id}`, {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.json();
                    })
                    .then(data => {
                        // Xử lý phản hồi từ server sau khi cập nhật thành công
                        alert('Cập nhật thành công');
                        // Có thể thực hiện chuyển hướng hoặc các hành động khác ở đây
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('Đã xảy ra lỗi khi cập nhật hồ sơ');
                    });
            } else {
                // Nếu không có dữ liệu thay đổi, thông báo cho người dùng
                alert('Không có thay đổi để lưu');
            }
        });



    });
</script>


