<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>


<%@include file="DashBoardHeader.jspf" %>

            <!-- Content Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">會員資訊</h6>
                        <button type="button" class="btn btn-primary mb-0" onclick="location.href='./member'">新增會員</button>
                    </div>
                    <div class="table-responsive">
                        <table class="table align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                	<th scope="col">會員編號</th>
                                    <th scope="col">帳號(身分證)</th>
                                    <th scope="col">密碼</th>
                                    <th scope="col">姓名</th>
                                    <th scope="col">性別</th>
                                    <th scope="col">會員點數</th>
                                    <th scope="col">修改</th>
                                </tr>
                            </thead>
                            <tbody>
                           	<c:forEach items="${members }" var="member">
                           
                                <tr>
                               	 	<td>${member.idNumber }</td>
                                    <td>${member.userid }</td>
                                    <td>${member.pwd }</td>
                                    <td>${member.username }</td>
                                    <td>${member.gender }</td>
                                    <td>${member.point }</td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary m-0" onclick="location.href='member/${member.idNumber }'">修改會員</button>
                                       
                                        <button type="button" class="btn btn-outline-danger m-0" onclick="del(this.id)" id="${member.idNumber }">刪除會員</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
            </div>
            <!-- Content End -->

<script type="text/javascript">

function del(id) {
	if (confirm('確定刪除?')) {
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState==4&&xhr.status==200) {
				if (xhr.responseText==="ok") {
					window.location.href="<c:url value='/members'/>"
				}
			}
		}
		xhr.open('delete',"<c:url value='/member'/>"+"/"+id, false)  
		xhr.send();
	}
}

</script>




<%@include file="DashBoardFooter.jspf" %>