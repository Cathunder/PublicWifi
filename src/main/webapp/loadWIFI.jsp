<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<%@ page import="org.example.midassignment.dao.WIFIInfoDAO" %>
<%@ page import="org.example.midassignment.service.WIFIInfoService" %>

<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${contextPath}/res/css/main.css" rel="stylesheet"/>
</head>
<body>
    <%
        WIFIInfoDAO wifiInfoDAO = new WIFIInfoDAO();
        WIFIInfoService wifiInfoService = new WIFIInfoService(wifiInfoDAO);
        int totalCnt = wifiInfoService.getWIFITotalCnt();
        wifiInfoService.getWIFIInfo();
    %>

    <h1 class="result-div">
        <%=totalCnt%>개의 WIFI 정보를 정상적으로 저장하였습니다.
    </h1>

    <div class="api-action result-div">
        <a href="${contextPath}/">홈으로 가기</a>
    </div>
</body>
</html>