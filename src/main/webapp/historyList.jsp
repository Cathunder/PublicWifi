<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="/res/css/main.css" rel="stylesheet"/>
</head>
<body>
    <h1>
        와이파이 정보 구하기
    </h1>

    <div class="api-action">
        <a href="/">홈</a>
        |
        <a href="/historyList.jsp">위치 히스토리 목록</a>
        |
        <a href="/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    </div>

    <div class="position-info">
        LAT: <input type="text" size="20">,
        LNT: <input type="text" size="20">
        <button>내 위치 가져오기</button>
        <button>근처 WIFI 정보 가져오기</button>
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
            <td>id내용</td>
            <td>X좌표내용</td>
            <td>Y좌표내용</td>
            <td>조회일자내용</td>
            <td class="td-center">
                <button>삭제</button>
            </td>
        </tbody>
    </table>
</body>
</html>