<%@ page import="org.example.midassignment.dao.HistoryDAO" %>
<%@ page import="org.example.midassignment.dto.HistoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>

<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${contextPath}/res/css/main.css" rel="stylesheet"/>
    <script src="<c:url value="/webjars/jquery/3.7.1/jquery.min.js" />"></script>
</head>
<body>
    <h1>
        와이파이 정보 구하기
    </h1>

    <div class="api-action">
        <a href="${contextPath}/">홈</a>
        |
        <a href="${contextPath}/historyList.jsp">위치 히스토리 목록</a>
        |
        <a href="${contextPath}/loadWIFI.jsp">Open API 와이파이 정보 가져오기</a>
    </div>

    <table class="table-list">
        <thead>
            <tr>
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <%
                HistoryDAO historyDAO = new HistoryDAO();

                String idValue = request.getParameter("ID");
                if (idValue != null) {
                    historyDAO.deleteHistory(idValue);
                }

                List<HistoryDTO> list = historyDAO.findAllHistory();

                if (list.isEmpty()) {
            %>
                    <tr>
                        <td class="td-center" colspan="5">
                            저장된 위치 기록이 없습니다.
                        </td>
                    </tr>
            <%
                } else {
                    for(HistoryDTO dto : list) {
            %>
                        <tr>
                            <td><%=dto.getId()%></td>
                            <td><%=dto.getLat()%></td>
                            <td><%=dto.getLnt()%></td>
                            <td><%=dto.getSearchTime()%></td>
                            <td class="td-center">
                                <button class="deleteBtn">삭제</button>
                            </td>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

    <script src="${contextPath}/res/js/historyList.js"></script>
</body>
</html>