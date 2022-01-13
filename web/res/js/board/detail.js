{
    const btnDel = document.querySelector('#btnDel');
    const data = document.querySelector('#data');

    if (btnDel) {
        btnDel.addEventListener('click', function () {
            let iboard = data.dataset.iboard;
            let icategory = data.dataset.icategory;
            if (confirm(msg.isdel)) {
                location.href = `/board/del?icategory=${icategory}&iboard=${iboard}`;
            }
        })
    }

    const btnMod = document.querySelector('#btnMod');
    if (btnMod) {
        btnMod.addEventListener('click', function () {
            let iboard = data.dataset.iboard;
            location.href = `/board/mod?iboard=${iboard}`;
        });
    }
}


