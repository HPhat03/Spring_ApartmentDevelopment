function createReceipt(url, urlIndex) {
    var month = document.getElementById('month').value;
    var year = document.getElementById('year').value;
    var electric =document.getElementById("electric_usage").value;
    var water = document.getElementById("water_usage").value;
    var apartmentId = document.getElementById("apartmentId").value;

    document.getElementById('apartmentIdError').innerHTML = '';
    document.getElementById('monthError').innerHTML = '';
    document.getElementById('yearError').innerHTML = '';
    document.getElementById('electricUsageError').innerHTML = '';
    document.getElementById('waterUsageError').innerHTML = '';

    var isValid = true;

    if (!apartmentId) {
        document.getElementById('apartmentIdError').innerHTML = "Vui lòng chọn phòng.";
        isValid = false;
    }
    if (!month || month < 1 || month > 12) {
        document.getElementById('monthError').innerHTML = "Tháng không hợp lệ.";
        isValid = false;
    }
    if (!year || year < 0 || year > new Date().getFullYear()) {
        document.getElementById('yearError').innerHTML = "Năm không hợp lệ.";
        isValid = false;
    }
    if (!electric || electric < 0) {
        document.getElementById('electricUsageError').innerHTML = " Số Tiêu thụ điện không hợp lệ.";
        isValid = false;
    }
    if (!water || water < 0) {
        document.getElementById('waterUsageError').innerHTML = " Số tiêu thụ nước không hợp lệ.";
        isValid = false;
    }

    if (!isValid) {
        return;
    }
    const data = {
        month: month,
        year: year,
        electric_usage: electric,
        water_usage: water,
        apartmentId: apartmentId
    };
    console.log(url);

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.status === 201) {
                alert('Hóa đơn đã được tạo thành công!');

                window.location.href =urlIndex;
            } else if (response.status === 400) {
                alert('Thiếu các thông tin cần thiết');
            } else if (response.status === 404) {
                alert('Không tìm thấy phòng với ID đã chọn');
            } else {
                return response.json().then(errorData => {
                    throw new Error(errorData.message || 'Có lỗi xảy ra');
                });
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Có lỗi xảy ra khi tạo hóa đơn: ' + error.message);
        });

}







