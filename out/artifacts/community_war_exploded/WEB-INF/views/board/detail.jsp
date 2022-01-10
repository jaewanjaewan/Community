<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.loginUser.iuser == data.iuser}">
    <div>
        <button id="btnMod">수정</button>
        <button id="btnDel">삭제</button>
    </div>
</c:if>
<div id="data" data-iboard="${data.iboard}" data-icategory="${data.icategory}"></div>
<div>
    <div>${data.categorynm}</div>
    <div>조회수 : ${data.hits} | 등록일시 : ${data.rdt}</div>
    <div>글쓴이 : ${data.writernm}</div>
    <div>제목: <c:out value="${data.title}"/></div>
    <hr>
    <div><c:out value="${data.ctnt}"/></div>
</div>