let deleteBtns = document.querySelectorAll(".deleteBtn");

deleteBtns.forEach(function(deleteBtn) {
    deleteBtn.addEventListener("click", function() {
        let id = this.closest('tr').querySelector('td:first-child').innerText;

        $.ajax({
            url: "http://localhost:8080/historyList.jsp",
            type: "GET",
            data: {ID: id},
            success: function() {
                location.reload();
            },
            error: function() {
                alert("삭제 실패");
            }
        });
    });
});
