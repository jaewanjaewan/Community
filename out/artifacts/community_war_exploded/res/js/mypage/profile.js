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
        fData.append('profileimg', img);
        fetch('/user/mypage/profile', {
            'method': 'post',
            'body': fData
        })
            .then(res => res.json())//객체로 받는다
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