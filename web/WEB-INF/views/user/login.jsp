<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<h1>로그인</h1>
<div>${requestScope.msg}</div>
<form action="/user/login" method="post" id="login_frm">
    <div><label>id : <input type="text" name="uid" value="${requestScope.id}"></label></div>
    <div><label>password : <input type="password" name="upw"></label></div>
    <div>
        <input type="submit" value="로그인">
        <a href="/user/join"><input type="button" value="회원가입"></a>
    </div>
</form>
