document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('myForm');
    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const firstname = document.getElementById('firstname').value;
        const lastname = document.getElementById('lastname').value;
        // const birthdate = document.getElementById('birthdate').value;
        const birthdate = "23/11/2003"
        const gender = document.querySelector('input[name="gender"]:checked').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        const avatar = document.getElementById('file').files[0];
        const role = document.getElementById('role').value;
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        console.log(' Birthdate:', birthdate);
        // In dữ liệu form ra console
        console.log({
            firstname,
            lastname,
            birthdate,
            gender,
            email,
            phone,
            avatar,
            role,
            username,
            password,
        });

        const formData = new FormData();
        formData.append('firstname', firstname);
        formData.append('lastname', lastname);
        formData.append('birthdate', birthdate);
        formData.append('gender', gender);
        formData.append('email', email);
        formData.append('phone', phone);
        formData.append('file', avatar);
        formData.append('role', role);
        formData.append('username', username);
        formData.append('password', password);

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
