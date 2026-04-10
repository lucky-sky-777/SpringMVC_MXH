document.addEventListener("DOMContentLoaded", function () {
    const btnTop = document.getElementById("btnTop");
    if (btnTop) {
        btnTop.addEventListener("click", function () {
            document.querySelector(".overflow-auto").scrollTo({
                top: 0,
                behavior: "smooth"
            });
        });
    }
    const dialog = document.getElementById("createPostDialog");
    const btnAdd = document.getElementById("btnAdd");
    if (btnAdd) {
        btnAdd.addEventListener("click", function () {
            if (dialog) {
                dialog.show();
            }
        });
    }
    const createPostDialog_cancelButton = document.getElementById("createPostDialog_cancelButton");
    if (createPostDialog_cancelButton) {
        createPostDialog_cancelButton.addEventListener("click", function () {
            if (dialog) {
                dialog.close();
            }
        });
    }
    const createPostForm = document.getElementById("createPostForm");
    if (createPostForm) {
        createPostForm.addEventListener("submit", function (e) {
            //e.preventDefault();
            if (dialog) {
                dialog.close();
            }
        });
    }
});