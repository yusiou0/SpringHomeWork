<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="DashBoardHeader.jspf"%>

<title>履歷總覽</title>
<style>
</style>
<div class="container-fluid pt-4 px-4">
<div class="bg-light text-center rounded p-4">
<div class="d-flex align-items-center justify-content-between mb-4">
<h1 class="mb-0">所有履歷</h1>
<button type="button" class="btn btn-primary mb-0" onclick="location.href='./resume'">新增履歷</button>

</div>

<table class="table align-middle table-bordered table-hover mb-0">
	<tr>
		<th>履歷編號</th>
		<th>學歷</th>
		<th>畢業學校</th>
		<th>畢業科系</th>
		<th>工作經驗</th>
		<th>取得證照</th>
		<th>身分證字號</th>
		<th>更新</th>
		<th>刪除</th>
	</tr>
    <c:forEach items="${resumes }" var="resume">
	<tr>
		<td>${resume.resume_id }</td>
		<td>${resume.edu }</td>
		<td>${resume.school}</td>
		<td>${resume.dept}</td>
		<td>${resume.work_exp}</td>
		<td>${resume.skills}</td>
		<td>${resume.user_id}</td>
		<td>
			<button type="button" class="btn btn-outline-primary m-0" onclick="location.href='resume/${resume.resume_id }'">更新</button>
		</td>
		<td>
		<!--
		 	<button type="button" class="btn btn-outline-danger m-0" onclick="javascript:if(confirm('確定要刪除嗎?'))location.href='resume/${resume.resume_id }'">刪除</button>
		 -->
			<button type="button" class="btn btn-outline-danger m-0" onclick="del(this.id)" id="${resume.resume_id }">刪除</button>
		</td>
	</tr>
	</c:forEach>
</table>
</div>
</div>
<script type="text/javascript">

function del(id) {
	if (confirm('確定刪除?')) {
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState==4&&xhr.status==200) {
				if (xhr.responseText==="ok") {
					window.location.href="<c:url value='/resumes'/>"
					//windows.location.href="<c:url value='/resume'/>"
				}
			}
		}
		xhr.open('delete',"<c:url value='/resume'/>"+"/"+id, false)  //這邊的true/false甚麼意思?
		xhr.send();
		//windows.location.href="<c:url value='/resumes'/>"
	}
}

</script>

<%@include file="DashBoardFooter.jspf"%>