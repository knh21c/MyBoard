<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Content of Article</title>
<style>
table, th, td {
/* 	border: 1px solid black; */
	border-collapse: collapse;
}
th, td {
	padding: 15px;
	text-align: left;
}
th {
	background-color: black;
		color: white;
}
</style>
</head>
<body>

<fieldset>
  <legend>Content of Article</legend>
  <form id="bottomForm" name="bottomForm" method="post">
  	<table>
  	  <tr>
  	    <th align="left">제목</th>
  	    <th align="center">${ message.getSubject() }</th>
  	    <th align="left">작성자</th>
  	    <th align="center">${ message.getAuthor() }</th>
  	  </tr>
  	  <tr>
        <td colspan="4">${message.getContent() }</td>
      </tr>
      <tr>
        <td>댓글:</td>
      </tr>
      <tr>
      	<td colspan="3"><textarea name="comment" rows="2" cols="80"></textarea></td>
      	<td align="center"><input type="button" id="insertComment" name="insertComment" value="등록" onclick="addComment()" /></td>
      </tr>
    </table>
      <input type="hidden" name="id" />
      <input type="button" name="deleteBtn" value="삭제" onclick="delFunc()"/>
      <input type="button" name="editBtn" value="수정" onclick="editFunc()"/>
      <input type="button" name="listBtn" value="돌아가기" onclick="toIndex()"/>
</form>
</fieldset>
<c:forEach var="i" items="${comments}" begin="0" end="${comments.size() }" step="1" varStatus="status">
	<table>
	  <tr>
	    <td>${status.count}</td>
	    <td>${i.getComment() }</td>
	  </tr>
	</table>
</c:forEach>
<script>

  window.onload=function(){
	  var popup = "${popup}";
	  if(popup != "")
		  alert(popup);
	  
  }
  function addComment(){
	  if(document.bottomForm.comment.value == "")
		  alert("코멘트를 작성하세요");
	  else{
		  document.bottomForm.id.value="${ message.getId() }";
		  document.bottomForm.action="./addComment.do";
		  document.bottomForm.submit();
	  }
  }
  function toIndex(){
	  document.bottomForm.action="./index.do";
	  document.bottomForm.submit();
  }
  function editFunc(){
	  document.bottomForm.id.value="${ message.getId() }";
	  document.bottomForm.action="./editArticle.do";
	  document.bottomForm.submit();
  }
  function delFunc(){
	  var parent = document.getElementById("bottomForm");
	  var childLength = parent.childNodes.length;
	  if(childLength > 11){
		  parent.removeChild(parent.lastChild);
		  parent.removeChild(parent.lastChild);
	  }
	  var input = document.createElement("input");
	  var btn = document.createElement("input");
	  input.setAttribute("type", "text");
	  input.setAttribute("name", "pw");
	  input.setAttribute("value", "비밀번호 입력");
	  
	  btn.setAttribute("type", "button");
	  btn.setAttribute("name", "delCheckBtn");
	  btn.setAttribute("value", "삭제");
	  
	  btn.setAttribute("onclick", "delPwCheck()");
	  
	  parent.appendChild(input);
	  parent.appendChild(btn);
	  input.select();
	  input.focus();

  }
  function delPwCheck(){
	  document.bottomForm.id.value="${ message.getId() }";
	  document.bottomForm.action="./delArticle.do";
	  document.bottomForm.submit();
  }
</script>
</html>