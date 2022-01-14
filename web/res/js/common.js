const msg = {
    'isdel': '삭제하시겠습니까?',
}

const regex = { //정규식은 따옴표 필요없음 약간 int개념?
    id: /^([a-zA-Z0-9]{4,15})$/,
    pw: /^([a-zA-Z0-9!@_]{4,20})$/,
    nm: /^([가-힣]{2,5})$/,
    ctnt: /^([^><])*$/,
    isWrongWith: function (target, val) {
        return !this[target].test(val); //test값이 범위에 속하면 true, 속하지않으면 false리턴
    }
}

const myFetch = {
    send : function (fetchObj, cb) {
        return fetchObj
            .then(res => res.json())
            .then(cb)
            .catch(e => {console.log(e)});
    },
    get: function(url, cb, param){
        if(param){
            const queryString = Object.keys(param).map(key => `${key}=${param[key]}`).join('&');
            //object.keys()는 key만으로 구성된 배열을 만들어준다.
            url = `${url}?${queryString}`;
        }
        return this.send(fetch(url), cb);
    },
    post: function(url, cb, param) {
        return this.send(fetch(url, {
            'method': 'post',
            'headers': { 'Content-Type': 'application/json' },
            'body': JSON.stringify(param)
        }), cb);
    }
}
