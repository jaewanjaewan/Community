{
    const dataElem = document.querySelector('#data');

    //글 삭제 버튼
    const delBtnElem = document.querySelector('#delBtn');
    if (delBtnElem) {
        delBtnElem.addEventListener('click', () => {
            const icategory = dataElem.dataset.icategory;
            const iboard = dataElem.dataset.iboard;

            if (confirm(msg.fnIsDel(`${iboard}번 글`))) {
                location.href = `/board/del?icategory=${icategory}&iboard=${iboard}`;
            }
        });
    }

    //글 수정 버튼
    const modBtnElem = document.querySelector('#modBtn');
    if (modBtnElem) {
        modBtnElem.addEventListener('click', () => {
            const iboard = dataElem.dataset.iboard;
            location.href = `/board/mod?iboard=${iboard}`;
        });
    }

    const cmtFrmElem = document.querySelector('#cmtFrm');
    if (cmtFrmElem) { // true: 로그인 한 상태

        //input-text ctnt에서 엔터치면 submit날아가기 때문에 막는다.
        cmtFrmElem.addEventListener('submit', (e) => {
            e.preventDefault();
        });

        cmtFrmElem.btn_submit.addEventListener('click', () => {
            const cmtVal = cmtFrmElem.ctnt.value;
            if (cmtVal.length === 0) {
                alert('댓글 내용을 작성해 주세요.');
            } else if (regex.isWrongWith('ctnt', cmtVal)) {
                alert(regex.msg.ctnt);
            } else { //댓글 insert 시도
                insBoardCmtAjax(cmtVal);
            }
        });

        const insBoardCmtAjax = (val) => {
            const param = {
                'iboard': dataElem.dataset.iboard,
                'ctnt': val
            };
            myFetch.post('/board/cmt', (data) => {
                console.log('result : ' + data.result);
                switch (data.result) { //icmt값이 넘어온다
                    case 0:
                        alert('댓글 등록에 실패하였습니다.');
                        break;
                    default:
                        //기존 table태그가 있는지 확인
                        const cmtListElem = document.querySelector('#cmt_list');
                        let table = cmtListElem.querySelector('table');
                        if (!table) { //없으면 table 생성!
                            cmtListElem.innerHTML = null; //댓글 없음 내용 삭제!
                            table = makeTable();
                            cmtListElem.appendChild(table);
                        }
                        const item = {
                            icmt: data.result,
                            iuser: parseInt(dataElem.dataset.iuser),
                            writernm: dataElem.dataset.nm,
                            profileimg: dataElem.dataset.profileimg,
                            ctnt: cmtFrmElem.ctnt.value,
                        }
                        const tr = makeTr(item);
                        table.appendChild(tr);

                        cmtFrmElem.ctnt.value = null;
                        window.scrollTo(0, document.body.scrollHeight); //댓글을 달면 스크롤이동
                        break;
                }
            }, param);
        }
    }

    //통신 시작!!!
    const getCmtList = () => {
        const iboard = dataElem.dataset.iboard;
        myFetch.get(`/board/cmt/${iboard}`, setCmtList);
    }

    //통신 결과물 세팅
    const setCmtList = (list) => {
        const cmtListElem = document.querySelector('#cmt_list');

        //댓글이 없으면 "댓글 없음"
        if (list.length === 0) {
            cmtListElem.innerText = '댓글 없음!';
            return;
        }

        const table = makeTable();
        cmtListElem.appendChild(table);

        list.forEach(item => {
            const tr = makeTr(item);
            table.appendChild(tr);
        });
    }

    const makeTable = () => {
        const table = document.createElement('table');
        table.innerHTML = `
            <tr>
                <th>no</th>
                <th>content</th>
                <th>writer</th>
                <th></th>
            </tr>`;
        return table;
    }

    const makeTr = item => {
        const tr = document.createElement('tr');

        const imgSrc = item.profileimg === null
            ? '/res/img/defaultProfile.png'
            : `/images/user/${item.iuser}/${item.profileimg}`;

        tr.innerHTML = `
                <td>${item.icmt}</td>
                <td>${item.ctnt}</td>
                <td>
                    <span>${item.writernm}</span>
                    <div class="circular--img wh-30">
                        <img src="${imgSrc}" onerror="this.style.display='none';">
                    </div>
                </td>
            `;
        const td = document.createElement('td');
        tr.appendChild(td);

        if (parseInt(dataElem.dataset.iuser) === item.iuser) {
            const modBtn = document.createElement('input');
            modBtn.type = 'button';
            modBtn.value = '수정';
            modBtn.addEventListener('click', () => {
                const tdArr = tr.querySelectorAll('td');
                const tdCell = tdArr[1];

                const modinput = document.createElement('input');
                modinput.value = item.ctnt;
                const saveBtn = document.createElement('input');
                saveBtn.type = 'button';
                saveBtn.value = '저장';
                saveBtn.addEventListener('click', () => {
                    const param = {
                        icmt: item.icmt,
                        ctnt: modinput.value
                    }
                    myFetch.put('/board/cmt', data => {
                        switch (data.result) {
                            case 0:
                                alert('댓글 수정에 실패했습니다')
                                break;
                            case 1:
                                tdCell.innerText = modinput.value;
                                item.ctnt = modinput.value;
                                removeCancelBtn();
                                break;
                        }
                    }, param);
                });

                tdCell.innerHTML = null;
                tdCell.appendChild(modinput);
                tdCell.appendChild(saveBtn);

                const cancelBtn = document.createElement('input');
                cancelBtn.type = 'button';
                cancelBtn.value = '취소';
                cancelBtn.addEventListener('click', () => {
                    tdCell.innerText = item.ctnt;
                    removeCancelBtn();
                })

                const removeCancelBtn = () => {
                    modBtn.classList.remove('hidden');
                    delBtn.classList.remove('hidden');
                    cancelBtn.remove(); //자기자신을 없앤다
                }

                td.insertBefore(cancelBtn, delBtn); //수정버튼 클릭했을때 취소버튼을 삭제버튼 앞에놓겠다
                modBtn.classList.add('hidden'); //수정버튼을 클릭했을때 각버튼에 hidden클래스 추가해서 버튼이 안보임
                delBtn.classList.add('hidden'); //수정버튼을 클릭했을때 각버튼에 hidden클래스 추가해서 버튼이 안보임
            });

            const delBtn = document.createElement('input');
            delBtn.type = 'button';
            delBtn.value = '삭제';

            delBtn.addEventListener('click', () => {
                if (confirm('삭제하시겠습니까?')) {
                    delCmt(item.icmt, tr);
                }
            });

            td.appendChild(modBtn);
            td.appendChild(delBtn);
        }
        return tr;
    }

    //댓글 삭제
    const delCmt = (icmt, tr) => {
        myFetch.delete(`/board/cmt/${icmt}`, data => {
            if (data.result) {
                tr.remove();

                //만약 댓글이 하나도 없다면
                if (getTrLen() === 1) {
                    const cmtListElem = document.querySelector('#cmt_list');
                    cmtListElem.innerText = '댓글 없음!';
                }

            } else {
                alert('댓글을 삭제할 수 없습니다.');
            }
        });
    }

    const getTrLen = () => {
        const cmtListElem = document.querySelector('#cmt_list');
        const trArr = cmtListElem.querySelectorAll('table tr');
        return trArr.length;
    }

    getCmtList();

    //좋아요 ------------------------------------------------------------ [start]
    const favIconElem = document.querySelector('#fav_icon');
    const isfav = () => {
        const iboard = dataElem.dataset.iboard;
        myFetch.get(`/board/fav/${iboard}`, data => {
            switch (data.result) {
                case 0:
                    disableFav();
                    break;
                case 1:
                    enableFav();
                    break;
            }
        })
    }

    //far : 좋아요안된상태 fas : 좋아요된상태
    const disableFav = () => {
        if (favIconElem) {
            favIconElem.classList.remove('fas');
            favIconElem.classList.add('far');
        }
    }

    const enableFav = () => {
        if (favIconElem) {
            favIconElem.classList.remove('far');
            favIconElem.classList.add('fas');
        }
    }

    if (dataElem.dataset.iuser) { //있으면 true 없으면 false
        isfav();
        favIconElem.addEventListener('click', () => {
            const iboard = dataElem.dataset.iboard;
            if(favIconElem.classList.contains('far')){ //no 좋아요
                const param = {
                    'iboard': iboard
                }
                myFetch.post(`/board/fav`, data => {
                    switch (data.result) {
                        case 0:
                            alert('좋아요처리에 실패했습니다.')
                            break;
                        case 1:
                            enableFav();
                            break;
                    }
                }, param)
            } else { //yes 좋아요
                myFetch.delete(`/board/fav/${iboard}`, data => {
                    switch (data.result) {
                        case 0:
                            alert('좋아요처리에 실패했습니다.')
                            break;
                        case 1:
                            disableFav();
                            break;
                    }
                });
            }
        });
    }
}


// Restful API > POST, GET, PUT, DELETE



