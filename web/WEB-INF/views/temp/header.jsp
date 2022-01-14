<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" uri="tld/MyCustomJstlTag.tld" %>
<tiles:importAttribute name="menuList"/>
<c:set var="currentPagePath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="lastPath" value="0"/> <!--분기안해주면 /user/login에서 오류남 이유: 문자열(login)이랑 (정수)icategory 비고뷸가해서?-->
<c:if test="${fn:contains(currentPagePath, 'list')}">
    <c:set var="splitURI" value="${fn:split(currentPagePath, '/')}"/> <!--문자열 쪼개기 /기준으로 0번방 1번방식으로 짜른다-->
    <c:set var="lastPath" value="${splitURI[fn:length(splitURI) - 1]}"/> <!--배열이니까 마지막인덱스는 길이에서 -1-->
</c:if>
<header class="h-50">
    <div class="flex-container flex-align-center p-lr-20">
        <c:choose>
            <c:when test="${sessionScope.loginUser == null}">
                <div class="m-r-20"><a href="/user/login" class="font-color-white">로그인</a></div>
            </c:when>
            <c:otherwise>
                <div class="m-r-20"><a href="/user/mypage/profile"><my:profileImg classVal="circular--img wh-30"
                                                                                  iuser="${sessionScope.loginUser.iuser}"
                                                                                  imgIdVal="header-profileimg"
                                                                                  profileImgVal="${sessionScope.loginUser.profileimg}"/></a></div>
                <div class="m-r-20"><a href="/user/logout" class="font-color-white">로그아웃</a></div>
            </c:otherwise>
        </c:choose>
        <c:forEach items="${menuList}" var="item">
            <div class="m-r-20 ${lastPath == ''.concat(item.icategory) ? 'menu-selected' : ''}">
                <a href="/board/list/${item.icategory}" class="font-color-white">${item.nm}</a>
            </div>
        </c:forEach>
    </div>
</header>