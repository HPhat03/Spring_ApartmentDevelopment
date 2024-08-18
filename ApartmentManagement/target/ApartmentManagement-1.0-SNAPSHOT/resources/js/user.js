document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('myForm');
    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const firstname = document.getElementById('firstname').value;
        const lastname = document.getElementById('lastname').value;
        var birthdate = document.getElementById('birthdate').value;
//        const birthdate = "23/11/2003"
        const gender = document.querySelector('input[name="gender"]:checked').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        const avatar = document.getElementById('file').files[0];
        const role = document.getElementById('role').value;
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const confirm = document.getElementById('confirmPassword').value;
        
        const array = birthdate.split("-");
        birthdate = `${array[2]}/${array[1]}/${array[0]}`;

        // In dữ liệu form ra console
        console.log({
            avatar,
            role,
            username,
            password,
        });
        if((role==="ADMIN")&&(username === "" || password=== "" || confirm === ""))
        {
            alert("Đối với tạo mới admin: cần điền đủ username và password");
            return;
        }
        
        if((role==="ADMIN")&&(password !== confirm))
        {
            alert("Mật khẩu không trùng khớp, vui lòng thử lại");
            return;
        }
        
        if(avatar === undefined)
        {
            alert("Thiếu hình ảnh, vui lòng thử lại");
            return;  
        }

        const formData = new FormData();
        formData.append('firstname', firstname);
        formData.append('lastname', lastname);
        formData.append('birthdate', birthdate);
        formData.append('gender', gender);
        formData.append('email', email);
        formData.append('phone', phone);
        formData.append('file', avatar === undefined ? null : avatar);
        
        if(role !== 'RESIDENT'){
            formData.append('role', role);
            formData.append('username', username);
            formData.append('password', password);
        }
        
        fetch("http://localhost:8080/ApartmentManagement/admin/users/add/", {
            method: 'POST',
            body: formData
            
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Success:', data);
                alert('Thêm người dùng mới thành công.');
                window.history.back(); // Chuyển hướng về trang trước đó
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Đã xảy ra lỗi khi gửi request.');
            });
    });
});

// api xoa user
function deleteUser(url, userId) {
    console.log("id", userId);
    if (confirm("Bạn có chắc chắn muốn xóa người dùng này không?")) {
        fetch(url, {
            method: "DELETE",
        })
            .then(response => {
                if (response.status === 204) {
                    alert("Xóa người dùng thành công");
                    location.reload();
                } else {
                    console.error("Lỗi xóa người dùng:", response.statusText);
                    alert("Xóa người dùng thất bại");
                }
            })
            .catch(error => {
                console.error("Lỗi fetch API:", error);
                alert("Có lỗi xảy ra khi xóa người dùng");
            });
    }
}

// cap nhat user

document.addEventListener('DOMContentLoaded', function (e) {
    
    var form = document.getElementById('editProfileForm');

    form.addEventListener('submit', function (event) {
        event.preventDefault(); // Ngăn form gửi yêu cầu mặc định của trình duyệt
        const userId = form.getAttribute('data-user-id');
        const firstNameInput = document.getElementById('firstName');
        const firstName = firstNameInput.value;
        const originalFirstName = firstNameInput.getAttribute('data-old');

        const lastNameInput = document.getElementById('lastName');
        const lastName = lastNameInput.value;
        const originalLastName = lastNameInput.getAttribute('data-old');

        // var birthdateInput = document.getElementById('birthdate');
        // var birthdate = birthdateInput.value;
        // var originalBirthdate = birthdateInput.getAttribute('data-old');

        const gender = document.querySelector('input[name="gender"]:checked').value;

        const emailInput = document.getElementById('email');
        const email = emailInput.value;
        const originalEmail = emailInput.getAttribute('data-old');

        const phoneInput = document.getElementById('phone');
        const phone = phoneInput.value;
        const originalPhone = phoneInput.getAttribute('data-old');

        // Tạo đối tượng dữ liệu JSON để gửi lên server
        const data = {};

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
        console.log("DATA", data);
        // Gửi yêu cầu PATCH lên server nếu có dữ liệu để gửi
        if (Object.keys(data).length > 0) {
            fetch(`/ApartmentManagement/admin/user/${userId}/`, {
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

// change status
// user.js

function changeStatus(userId, newStatus) {
    fetch(`/ApartmentManagement/admin/user/${userId}/`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            isActive: newStatus
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            location.reload();
        })
        .catch(error => {
            console.error('Lỗi', error);
            alert('Đã xảy ra lỗi khi cập nhật trạng thái người dùng.');
        });
}
