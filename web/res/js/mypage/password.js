{
    const frmElem = document.querySelector('#password_frm');
    if(frmElem){
        frmElem.addEventListener('submit', function (e) {
            const currentupwVal = frmElem.currentupw.value;
            const upwVal = frmElem.upw.value;
            const confirmupwVal = frmElem.confirmupw.value

            if(currentupwVal.length === 0){
                alert('현재 비밀번호를 작성해 주세요.')
                e.preventDefault();
            } else if(upwVal.length === 0){
                alert('변경 비밀번호를 작성해 주세요.')
                e.preventDefault();
            } else if(upwVal !== confirmupwVal){
                alert('변경 비밀번호와 확인 비밀번호가 다릅니다.')
                e.preventDefault();
            } else if(regex.isWrongWith('pw', currentupwVal)){ //common.js로 정규식 검사
                alert('현재 비밀번호가 대소문자+숫자+!@_ 조합으로 4~20글자인지 확인해 주세요.')
                e.preventDefault();
            } else if(regex.isWrongWith('pw', upwVal)){
                alert('변경 비밀번호가 대소문자+숫자+!@_ 조합으로 4~20글자인지 확인해 주세요.')
                e.preventDefault();
            }
        })
    }
}