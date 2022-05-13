<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
</h1>
<br/>
<form action="${pageContext.request.contextPath}/MSServlet" method="get">
    <label>编号：<input type="text" name="id"></label><br/>
    <label>详细信息：<input type="text" name="detail"></label><br/>
    <label>上传附件：<input type="file" name="uploadFile"></label><br/>
    <input type="submit" value="提交"/>
</form>
<a href="${pageContext.request.contextPath}/DisplayAll">查看所有数据</a>
</body>
</html>