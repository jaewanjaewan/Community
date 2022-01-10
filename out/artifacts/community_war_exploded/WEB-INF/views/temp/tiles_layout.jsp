<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/res/css/<tiles:getAsString name='common'/>.css">
    <link rel="stylesheet" href="/res/css/<tiles:getAsString name='addr1'/>/index.css">
    <link rel="stylesheet" href="/res/css/<tiles:getAsString name='addr2'/>.css">
    <title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
    <div id="container">
        <tiles:insertAttribute name="header"/> <!--header.jsp가 들어옴-->
        <section>
            <tiles:insertAttribute name="content"/>
        </section>
        <tiles:insertAttribute name="footer"/> <!--name : tiles.xml에 있는 이름을 적은것-->
    </div>
    <script src="/res/js/<tiles:getAsString name='common'/>.js"></script>
    <script src="/res/js/<tiles:getAsString name='addr1'/>/index.js"></script>
    <script src="/res/js/<tiles:getAsString name='addr2'/>.js"></script>
</body>
</html>