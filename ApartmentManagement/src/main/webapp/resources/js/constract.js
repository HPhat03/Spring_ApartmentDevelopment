function addRelative() {
    var container = document.getElementById('relatives-container');
    var div = document.createElement('div');
    div.classList.add('relative-input-group');

    var nameInput = document.createElement('input');
    nameInput.type = 'text';
    nameInput.name = 'other_members.name';
    nameInput.placeholder = 'Tên người thân';
    nameInput.classList.add('form-control');

    var relationshipInput = document.createElement('input');
    relationshipInput.type = 'text';
    relationshipInput.name = 'other_members.relationship';
    relationshipInput.placeholder = 'Mối quan hệ';
    relationshipInput.classList.add('form-control');

    var removeButton = document.createElement('button');
    removeButton.type = 'button';
    removeButton.innerHTML = '<i class="fa-solid fa-trash"></i>';
    removeButton.classList.add('remove-relative');
    removeButton.onclick = function() {
        container.removeChild(div);
    };
    div.appendChild(nameInput);
    div.appendChild(relationshipInput);
    div.appendChild(removeButton);

    container.appendChild(div);
}

function createConstract(url, urlIndex) {
    event.preventDefault();
    var form = document.getElementById("contractForm");
    var jsonData = {};
    var isValid = true;

    // Clear previous error messages
    document.getElementById('residentError').innerText = '';
    document.getElementById('roomError').innerText = '';
    document.getElementById('priceError').innerText = '';
    document.getElementById('relativesError').innerText = '';

    // Lấy dữ liệu từ các trường input của form
    var residentId = parseInt(form.querySelector('#resident').value);
    if (!residentId) {
        document.getElementById('residentError').innerText = 'Vui lòng chọn cư dân';
        isValid = false;
    }

    var roomId = parseInt(form.querySelector('#room').value);
    if (!roomId) {
        document.getElementById('roomError').innerText = 'Vui lòng chọn phòng';
        isValid = false;
    }

    var finalPrice = parseInt(form.querySelector('#contractPrice').value);
    if (!finalPrice) {
        document.getElementById('priceError').innerText = 'Vui lòng nhập giá hợp đồng';
        isValid = false;
    }

    // Thêm các dịch vụ đã chọn vào mảng services (nếu có)
    var services = [];
    var serviceCheckboxes = form.querySelectorAll('.service-checkbox:checked');
    if (serviceCheckboxes.length > 0) {
        serviceCheckboxes.forEach(function(checkbox) {
            services.push(parseInt(checkbox.value));
        });
    }

    // Thêm các thành viên khác vào mảng other_members
    var otherMembers = [];
    var relativeGroups = form.querySelectorAll('.relative-input-group');
    relativeGroups.forEach(function(group) {
        var nameInput = group.querySelector('input[name="other_members.name"]').value;
        var relationshipInput = group.querySelector('input[name="other_members.relationship"]').value;
        if (nameInput && relationshipInput) {
            otherMembers.push({
                name: nameInput,
                relationship: relationshipInput
            });
        }
    });

    // Nếu không hợp lệ, dừng việc gửi form
    if (!isValid) {
        return;
    }

    // Kiểm tra nếu tất cả các dịch vụ đã được chọn, loại bỏ trường services khỏi json
    if (serviceCheckboxes.length !== document.querySelectorAll('.service-checkbox').length) {
        jsonData.services = services;
    }

    jsonData.resident_id = residentId;
    jsonData.room_id = roomId;
    jsonData.final_price = finalPrice;
    jsonData.other_members = otherMembers;
    console.log('JSON DATA:', jsonData);

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
        .then(function(response) {
            if (!response.ok) {
                throw new Error('Có lỗi xảy ra khi tạo hợp đồng. Vui lòng thử lại sau.');
            }
        })
        .then(function(data) {
            console.log('Response:', data);
            alert('Hợp đồng đã được tạo thành công!');
            window.location.href = urlIndex;
        })
        .catch(function(error) {
            console.error('Error:', error);
            alert('Đã xảy ra lỗi khi tạo hợp đồng. Vui lòng thử lại!');
        });
}
