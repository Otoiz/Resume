<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%@ page import="java.util.*" %>
<% 
Career career = (Career)request.getAttribute("career");
List<Role> role_data = (List<Role>)request.getAttribute("role");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>業務経歴変更画面</title>
</head>
<body>
						
	<div class="mx-auto" style="width: 70%;">
		<h1 class="mb-3" style="text-align: center">業務経歴変更</h1>
		
		<form name="ResumeMod" class="row g-3"  action="<%= request.getContextPath() %>/ResumeModServlet" method="post">
		  <div class="col-md-4">
		    <label for="employeeId" class="form-label">登録日時</label>
		    <input type="text" class="form-control" id="credate" name="credate" value="<%= career.getCredate() %>" readonly>
		  </div>
		  <div class="col-md-4">
		    <label for="startdate" class="form-label">開始日</label>
		    <input type="date" class="form-control" id="startdate" name="startdate" value="<%= career.getStartdate() %>">
		  </div>
		  <div class="col-md-4">
		    <label for="enddate" class="form-label">終了日</label>
		    <input type="date" class="form-control" id="enddate" name="enddate" value="<%= career.getEnddate() %>">
		  </div>
		  <div class="col-md-6">
		    <label for="sysname" class="form-label">システム名</label>
		    <input type="text" class="form-control" id="sysname" name="sysname" value="<%= career.getSysname() %>">
		  </div>
		  <div class="col-md-12">
		    <label for="sysdtl" class="form-label">システム詳細</label>
		    <input type="text" class="form-control" id="sysdtl" name="sysdtl" value="<%= career.getSysdtl() %>">
		  </div>
		  
		  <div class="col-md-6">
		    <label for="roles" class="form-label">役割</label>
		    <input type="text" class="form-control" id="roles" name="roles" value="<%= career.getRoles() %>">
		  </div>

		  <div class="col-md-6">
		    <label for="tools" class="form-label">ツール</label>
		    <input type="text" class="form-control" id="tools" name="tools" value="<%= career.getTools()%>">
		  </div>
		  
		  <div class="col-md-12 btn-toolbar">
		  	<input type="hidden" id="kind" name="kind">
		  	<div class="col btn-group"><button type="button" class="btn btn-success" name="btnmod"
		  	onclick="buttonClick('変更')">変更</button></div>
		    <div class="col btn-group"><button type="button" class="btn btn-secondary" name="btndel"
		    onclick="buttonClick('削除')">削除</button></div>
		  </div>
		</form>
		
		<div style="text-align:center;">
			<a href="<%=request.getContextPath() %>/ResumeServlet">業務経歴一覧</a>
		</div>
	</div>

</body>
<script type="text/javascript">
function buttonClick(val){
	
	document.getElementById("kind").value = val;

	var result = window.confirm(val + "しますか");
	
	if( result ) {
		var f = document.forms["ResumeMod"];
		f.method = "POST"; 
		f.submit();
		return;
		
	} else {
		return;
	}
}



</script>

</html>