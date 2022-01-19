<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="p-10">
    <c:if test="${sessionScope.loginUser.iuser == data.iuser}">
        <div>
            <button id="btnMod">수정</button>
            <button id="btnDel">삭제</button>
        </div>
    </c:if>
    <div id="data"
         data-iboard="${data.iboard}"
         data-icategory="${data.icategory}"
         data-nm="${sessionScope.loginUser.nm}"
         data-iuser="${sessionScope.loginUser.iuser}"
         data-profileimg="${sessionScope.loginUser.profileimg}">
    </div>
    <div>
        <c:if test="${requestScope.prevnext.previboard > 0}">
            <a href="/board/detail?iboard=${requestScope.prevnext.previboard}"><button>이전글</button></a>
        </c:if>
        <c:if test="${requestScope.prevnext.nextiboard > 0}">
            <a href="/board/detail?iboard=${requestScope.prevnext.nextiboard}"><button>다음글</button></a>
        </c:if>
    </div>
    <c:if test="${sessionScope.loginUser != null}">
        <!--좋아요-->
        <div>
            <i id="fav_icon" class="fs-30 pointer far fa-thumbs-up"></i> <!--font쓸려면 cdn코드 추가해야함 tileslayout.jsp에추가했음-->
        </div>
    </c:if>
    <div>
        <div>${data.categorynm}</div>
        <div>조회수 : ${data.hits} | 등록일시 : ${data.rdt}</div>
        <div>글쓴이 : ${data.writernm}</div>
        <div>제목: <c:out value="${data.title}"/></div>
        <hr>
        <div><c:out value="${data.ctnt}"/></div>
    </div>
    <c:if test="${sessionScope.loginUser != null}">
        <div class="m-t-20">
            <form id="cmtFrm">
                <input type="text" name="ctnt"><input type="button" id="btn_submit" value="댓글달기">
            </form>
        </div>
    </c:if>
    <div class="m-t-20" id="cmt_list"></div>
</div>