function deleteRoom(url, roomId) {
    console.log(url);
    if (confirm("Bạn có chắc chắn muốn xóa phòng này không?")) {
        fetch(url, {
            method: "DELETE",
        })
            .then(response => {
                if (response.status === 200) {
                    alert("Xóa phòng thành công");
                    location.reload();
                } else {
                    console.log("id dich vu:", serviceId);
                    alert("Xóa phòng thất bại");
                }
            })
            .catch(error => {
                console.log("id rooom:", roomId);
                console.error("Loi fetch api:", error);
                alert("Có lỗi xảy ra khi xóa phòng");
            });
    }
}

