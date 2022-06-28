<%@page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@page import="org.springframework.ui.Model"%>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="DashBoardHeader.jspf" %>


<style>
textarea {
	resize: none;
}
.center{
 text-align:center;
}
</style>
<div class="container-fluid pt-4 px-4">
<div class="col-sm-12 col-xl-10">
<div class="bg-light rounded h-100 p-4">
<h1>更新履歷</h1>
<form action="<c:url value='/resumeupdate'/>" method="post" id="form">
<!--  <input type="hidden" name="id" value="${resume.resume_id }">   -->
<div class="row mb-3">
<label class="col-sm-2 col-form-label">履歷編號</label>
<div class="col-sm-8">
<input type='text' name='resume_id' value='${resume.resume_id }' readonly id="resume_id"/>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">身分證字號:</label>
<div class="col-sm-8">
<input type='text' name='user_id' value='${resume.user_id }' class="form-control" readonly/>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">學歷:</label>
<div class="col-sm-8">
<select name='edu' class="form-select">
<option>${resume.edu}</option>
<option>大學</option>
<option>科技大學</option>
<option>研究所</option>
<option>博士</option>
<option>高中職</option>
<option>五專</option>
</select>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">畢業學校:</label>
<div class="col-sm-8">
<input type='text' name='school' value='${resume.school }' class="form-control"/>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">畢業科系:</label>
<div class="col-sm-8">
<input type='text' name='dept' value='${resume.dept}' class="form-control"/>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">工作經驗:</label>
<div class="col-sm-8">
<textarea name="work_exp" id="" cols="63" rows="6">${resume.work_exp}</textarea>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">取得證照:</label>
<div class="col-sm-8">
<textarea name="skills" id="" cols="63" rows="4">${resume.skills}</textarea>
</div>
</div>
<div class="center">	
<button type="submit" class="btn btn-primary" name="update" value="confirm">確認修改</button>
<button type="button" class="btn btn-primary" onclick='location.href="<c:url value='/resumes'/>"'>取消修改</button>
</div>

</form>
</div>
</div>
</div>


<%@include file="DashBoardFooter.jspf"%>