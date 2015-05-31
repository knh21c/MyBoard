<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>

<style>
.pagebar {
	text-align:center;
}
.pagebar li {
	display:inline-block;
	border:1px solid black;
	padding:2px 5px;
}
table{
	width: 100%;
}
table, th, td {
	border: 1px solid black;
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
tr:hover{
	background-color:lightgray;
}
a:link {
	color:#000000;
	background-color:transparent;
	text-decoration:none;
}
a:visted {
	color:#000000;
    background-color:transparent;
    text-decoration:none;
}
a:hover {
    color:#ff0000;
    background-color:transparent;
    text-decoration:underline;
}
a:active {
    color:#ff0000;
    background-color:transparent;
    text-decoration:underline;
}
</style>
<script>
window.onload=function(){
	  var popup = "${popup}";
	  if(popup != "")
		  alert(popup);
}
function toInnerView(index){
	document.indexform.id.value = index;
	document.indexform.submit();
}
function pageswitch(num){
	document.pageSwitchForm.pageNum.value=num;
	document.pageSwitchForm.submit();
}
function searchStart(){
	if(event.keyCode == '13'){
		document.search.type.value = document.search.searchType.value;
		document.search.action="./searchArticle.do";
		document.search.submit();
	}
}

</script>
</head>
<body>
<form name="indexform" method="post" action="./inner.do">
  <input type="hidden" name="id" value=""/>
  <table>
  
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>작성자</th>
      <th>작성일</th>
      <th>조회수</th>
    </tr>
    <c:set var="beginNum" value="${pageNum*10 }" />
    <c:set var="listNum" value="${message.size() }"/>
    <c:forEach var="i" items="${message}" begin="0" end="${message.size() }" step="1" varStatus="status">
    	<c:choose>
    	<c:when test="${(status.index >= beginNum) && (status.index < (beginNum+10))}">
    	<tr style="cursor:pointer;" onclick="toInnerView(${i.getId()})">
			<td>${ listNum-status.index }</td>
			<td>${ i.getSubject() }</td>
			<td>${ i.getAuthor() }</td>
			<td>${ i.getDate() }</td>
			<td>${ i.getHit() }</td>
			<c:set var="tmp" value="${message.size()/10 }" />
			<c:set var="pageCnt" value="${tmp+(1-(tmp%1))%1 }" />
		</tr>
		</c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
	</c:forEach>
  </table>
  </form>
  <form name="pageSwitchForm" method="post" action="./pageswitch.do">
  <input type="hidden" name="pageNum" />
  <div class="pagebar">
    <ul>
    <c:choose>
      <c:when test="${pageCnt > 0 }">
        <c:forEach var="i" begin="0" end="${pageCnt-1 }" step="1" varStatus="status">
  	      <li><a style="cursor:pointer;" onclick="pageswitch(${status.index })">${status.count}</a></li>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <li><a style="cursor:pointer;" onclick="pageswitch(0)">1</a></li>
      </c:otherwise>
    </c:choose>
    </ul>
  </div>
  </form>
  <p>
      <a href="./index.do"><input type="button" value="list" /></a>
      <a href="./write.do"><input type="button" value="Write" /></a>
  <form name="search" method="get">
  <div align="center">
    
    <select name="searchType" id="searchType" ontoggle="selectFunc()">
      <option value="total" >전체</option>
      <option value="subject" >제목</option>
      <option value="author">작성자</option>
      <option value="content">내용</option>
    </select>
    <input type="hidden" name="type" />
    <input type="text" name="key" id="key" onkeydown="searchStart()"/>
  </div>
  </form>
</body>
</html>