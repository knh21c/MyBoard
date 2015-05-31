<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Write a New Article</title>

</head>
<body>
  <form id="inputform" name="inputform" method="post" action="index">
    <fieldset>
      <legend>Write a New Article</legend>
      제목: <input type="text" id="subject" name="subject" value="${message.getSubject() }"/>
      이름: <input type="text" id="author" name="author" value="${message.getAuthor() }"/>
      비밀번호: <input type="password" id="pw" name="pw" />
      <br><br>
      <textarea id="content" name="content" rows="10" cols="100">${message.getContent() }</textarea>
      <br><br>
      <input id="cancel" type="button" value="취소" />
      <input type="button" value="작성 완료" />
      <p>
    </fieldset>
  </form>
</body>
</html>