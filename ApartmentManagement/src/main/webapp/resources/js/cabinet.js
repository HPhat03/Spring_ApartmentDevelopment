function deleteCabinet(url, cabinetId) {
    console.log(url);
    console.log(cabinetId);
    if (confirm("Bạn có chắc chắn muốn xóa đơn hàng này không?")) {
        fetch(url, {
            method: "DELETE",
        })
            .then(response => {
                if (response.status === 204) {
                    alert("Xóa thành công");
                    location.reload();
                } else {
                    alert("Xóa đơn thất bại");
                }
            })
            .catch(error => {
                console.error("Loi fetch api:", error);
                alert("Có lỗi xảy ra khi xóa đơn hàng");
            });
    }
}

