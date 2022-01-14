{
    const dataElem = document.querySelector('#data');

    const profileFileElem = document.querySelector('#profile-file');
    if(profileFileElem){
        profileFileElem.addEventListener('change', () => {
            const img = profileFileElem.files[0];
            if(img != null){
                uploadProfileImg(img);
            }
        })
    }
    //프로필 이미지 클릭 이벤트
    const profileViewElem = document.querySelector('#profile-view');
    if(profileViewElem){
        profileViewElem.addEventListener('click', () => {
            if(profileFileElem){
                profileFileElem.click();
            }
        })
    }

    //이미지 업로드
    const uploadProfileImg = (img) => {
        const fData = new FormData();
        /*FromData란 ajax로 폼 전송을 가능하게 해주는 FormData 객체입니다.
          보통은 Ajax로 폼(form 태그) 전송을 할 일이 거의 없습니다.
          주로 JSON 구조로 "KEY-VALUE" (키와 값) 구조로 데이터를 전송합니다.
          하지만, form전송이 필요한 경우가 있는데, 이미지를 ajax로 업로드할 때 필요합니다.*/
        fData.append('profileimg', img);
        fetch('/user/mypage/profile', {
            'method': 'post',
            'body': fData
        })
            .then(res => res.json())//fetch로 호출하면 promise 객체로 return된다
            .then(data => {
                console.log(data);
                setProfileImg(data);
            })
            .catch(e => {
                console.log(e);
            })
    }

    const setProfileImg = (data) => {
        if(!data.result){return;}

        const iuser = dataElem.dataset.iuser;
        const src = `/images/user/${iuser}/${data.result}`;

        //프로필 이미지
        const profileImgElem = profileViewElem.querySelector('img');
        profileImgElem.src = src;

        //헤더 이미지
        const headerProfileImgElem = document.querySelector('#header-profileimg');
        headerProfileImgElem.src = src;
    }
}