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
  <form id="inputform" name="inputform" method="post" action="index" enctype="multipart/form-data">
    <fieldset>
      <legend>Write a New Article</legend>
      제목: <input type="text" id="subject" name="subject" />
      이름: <input type="text" id="author" name="author" />
      비밀번호: <input type="password" id="pw" name="pw" />
      <br><br>
      <textarea id="content" name="content" rows="10" cols="100"></textarea>
      <br><br>
      <div id="uploadDiv">
        <input type="file" name="image0" style="display:block;" onchange="uploadImg(this)" />
      </div>
      <br><br>
      <input id="cancel" type="button" value="취소" onClick="cancelData()"/>
      <input type="button" value="작성 완료" onClick="return insertArticle()"/>
      <p>
    </fieldset>
  </form>
<script language=JavaScript>
var imgs=[0, 0, 0, 0, 0];
var fileCnt = 1;
  window.onload=function(){
	  document.inputform.subject.focus();
  }
  function cancelData(){
	document.inputform.action="./index.do";
	document.inputform.submit();
  }
  function insertArticle(){
		if(document.getElementById("subject").value=="" || document.getElementById("author").value==""
				|| document.getElementById("pw").value==""){
			alert("값을 입력하세요!");
			return false;
		}else{
			document.inputform.action = "./insert.do"
			document.inputform.submit();
			return true;
		}
  }
  function findNum(fileObj){
	  var name=fileObj.name;
	  var num=name.substring(5, 6);
	  var intNum = parseInt(num);
	  return intNum;
  }
  function lastUpLoadImg(fileObj){
	  var num = findNum(fileObj);
	  if(imgs[num] == 0){
	      addTag(fileCnt-1);
		  alert("이미지는 5개 까지 등록 가능합니다.");
		  imgs[num]++;
	  }
  }
  function uploadImg(fileObj){
	  var num = findNum(fileObj);
	  if(fileCnt < 4 ){
		  var inputImg = document.createElement("input");
		  var parent = document.getElementById("uploadDiv");
		  inputImg.setAttribute("type", "file");
		  inputImg.setAttribute("name", "image" + fileCnt);
		  inputImg.setAttribute("style", "display:block;");
		  inputImg.setAttribute("onchange", "uploadImg(this)");
		  if(imgs[num] == 0){
			  parent.appendChild(inputImg);
			  addTag(fileCnt-1);
			  fileCnt++;
			  imgs[num]++;
		  }
	  }else if(fileCnt == 4){
		  var inputImg = document.createElement("input");
		  var parent = document.getElementById("uploadDiv");
		  inputImg.setAttribute("type", "file");
		  inputImg.setAttribute("name", "image" + fileCnt);
		  inputImg.setAttribute("style", "display:block;");
		  inputImg.setAttribute("onchange", "lastUpLoadImg(this)");
		  if(imgs[num] == 0){
			  parent.appendChild(inputImg);
			  addTag(fileCnt-1);
			  fileCnt++;
			  imgs[num]++;
		  }
	  }
  }
  function addTag(cnt){
	  var content = document.getElementById("content");
	  var str = content.value;
	  if(str.indexOf('<image' + cnt + '>'))
		  content.value += "<image"+ cnt + ">" + "\r\n";
  }
  
</script>
</body>
</html>