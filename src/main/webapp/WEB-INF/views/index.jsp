<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MEMO APP</title>
</head>
<body>
    <h2>MEMO 조회 및 작성</h2>
    <p><%= request.getAttribute("msg") %></p>
    <p><%= request.getAttribute("memos") %></p>

    <form method="post">
        <input name="content" placeholder="메모내용"> <br>
        <button>작성</button>
    </form>
</body>
</html>
