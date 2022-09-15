<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>業務経歴登録画面</title>
</head>
<body>
	<div class="mx-auto" style="width: 70%;">

		<h1 class="mb-3" style="text-align: center">業務経歴登録</h1>
		
		<form class="row g-3" action="<%= request.getContextPath() %>/ResumeRegisterServlet" method="post">
		  <div class="col-md-6">
		    <label for="startdate" class="form-label">開始日</label>
		    <input type="date" class="form-control" id="startdate" name="startdate">
		  </div>
		  <div class="col-md-6">
		    <label for="enddate" class="form-label">終了日</label>
		    <input type="date" class="form-control" id="enddate" name="enddate" value="9999-12-31 00:00:00">
		  </div>
		  <div class="col-md-6">
		    <label for="sysname" class="form-label">システム名</label>
		    <input type="text" class="form-control" id="sysname" name="sysname">
		  </div>
		  <div class="col-md-12">
		    <label for="sysdtl" class="form-label">システム詳細</label>
		    <input type="text" class="form-control" id="sysdtl" name="sysdtl">
		  </div>
		  <div class="col-md-6">
		    <label for="roles" class="form-label">役割</label>
		    <input type="text" class="form-control" id="roles" name="roles">
		  </div>
		  <div class="col-md-6">
		    <label for="tools" class="form-label">ツール</label>
		    <input type="text" class="form-control" id="tools" name="tools">
		  </div>
		  <button type="submit" class="btn btn-primary">登録</button>
		</form>
		<div style="text-align:center;">
			<a href="<%=request.getContextPath() %>/ResumeServlet">業務経歴一覧</a>
		</div>
	</div>
</body>
</html>
