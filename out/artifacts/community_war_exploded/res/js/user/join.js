{
    let idChkState = 2; //2: 체크 안함, 1: 아이디 사용가능, 0: 아이디 사용 불가능
    const joinFrmElem = document.querySelector('#join-frm');
    const idRegex = /^([a-zA-Z0-9]{4,15})$/; //대소문자+숫자 조합으로 4~15글자인경우만 ok!
    const pwRegex = /^([a-zA-Z0-9!@_]{4,20})$/; //대소문자+숫자+!@ 조합으로 4~20글자인 경우만 ok!
    const nmRegex = /^([가-힣]{2,5})$/;

    if(joinFrmElem){
        //아이디 중복체크 버튼
        const idBtnChkElem = joinFrmElem.querySelector('#id-btn-chk');
        const idChkmsg = joinFrmElem.querySelector('#id-chk-msg');

        idBtnChkElem.addEventListener('click', () => {
           const idVal = joinFrmElem.uid.value; //joinFrmElem.uid는 타입이 object
            if(idVal.length < 4){
                alert('아이디는 4자 이상 작성해 주세요.');
                return;
            }
            if(!idRegex.test(idVal)){
               alert('아이디는 대소문자, 숫자조합으로 4~15글자가 되어야 합니다.');
               return;
           }
           fetch(`/user/idChk/${idVal}`)
               .then(res => res.json())
               .then((data) => {
                   console.log(data);
                   idChkState = data.result; //0 or 1
                   if(data.result === 1){
                       idChkmsg.innerText = '사용할 수 있는 아이디 입니다.'
                   } else {
                       idChkmsg.innerText = '이미 사용중인 아이디 입니다.'
                   }
               }).catch((e) => {
                   console.log(e);
           });
        });

        joinFrmElem.addEventListener('submit', (e) => {

            const uid = joinFrmElem.uid.value;
            const upw = joinFrmElem.upw.value;
            const upwChk = joinFrmElem.upw_chk.value;
            const nm = joinFrmElem.nm.value;

            if(!idRegex.test(uid)){
                alert('아이디는 대소문자, 숫자조합으로 4~15글자가 되어야 합니다.');
                e.preventDefault();
            } else if (!pwRegex.test(upw)){  //test값이 범위에 속하면 true, 속하지않으면 false리턴
                alert('비밀번호는 대소문자, 숫자, !@_ 조합으로 4~20글자가 되어야 합니다.');
                e.preventDefault();
            } else if(upw !== upwChk){
                alert('비밀번호를 확인해주세요')
                e.preventDefault();
            } else if (!nmRegex.test(nm)){  //test값이 범위에 속하면 true, 속하지않으면 false리턴
                alert('이름은 한글로 2~5글자가 되어야 합니다.');
                e.preventDefault();
            }

            if(idChkState !== 1){
                switch (idChkState) {
                    case 0:
                        alert('다른 아이디를 사용해주세요.');
                        break;
                    case 2:
                        alert('아이디 중복 체크를 해주세요.')
                        break;
                }
                e.preventDefault();
            }
            alert('회원가입에 성공하였습니다.');
        });

        //change: 다른곳으로 포커스가 이동이될때 이벤트 발생 keyup: 글자를 칠때 이벤트 발생
        joinFrmElem.uid.addEventListener('keyup', () => {
           idChkState = 2;
           idChkmsg.innerText = '';
        });
    }
}