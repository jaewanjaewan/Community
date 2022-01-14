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

    const cmtFrmElem = document.querySelector('#cmtFrm');
    if(cmtFrmElem){ //true: 로그인한 상태

        //input-text ctnt에서 엔터치면 submit날아가기 때문에 막는다
        cmtFrmElem.addEventListener('submit', (e) => {
            e.preventDefault();
        })

        //클릭만으로 댓글달게 할수있음
        cmtFrmElem.btn_submit.addEventListener('click', () => {
            const cmtVal = cmtFrmElem.ctnt.value;
            if(cmtVal.length === 0){
                alert('댓글을 작성해 주세요.')
            } else if(regex.isWrongWith('ctnt', cmtVal)){
                alert('<, >는 사용할 수 없습니다.')
            } else {
                insBoardCmtAjax(cmtVal);
            }
        });

        const insBoardCmtAjax = (val) => {
            fetch('/board/cmt', { //fetch에 인자 두개를 넣음
                'method' : 'post',
                'headers' : {'Content-Type': 'application/json'},
                'body' : JSON.stringify({
                    'iboard': data.dataset.iboard,
                    'ctnt': val
                }) //json으로 변환시킴
            })
                .then(res => res.json())
                .then(data => {
                    console.log(data);
                }).catch(err => {
                console.log(err);
            })
        }
    }
}



