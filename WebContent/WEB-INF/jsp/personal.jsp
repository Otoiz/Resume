<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%@ page import="java.util.*" %>
<% 
Personal personal_data = (Personal)request.getAttribute("personal");
List<Station> station_data = (List<Station>)request.getAttribute("station");
List<Certi> certi_data = (List<Certi>)request.getAttribute("certi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



<title>社員情報変更画面</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>


<% 
String s1="";
String s2="";
for(Station stars : station_data){ 
	if(s1 == "") {
		s1 = stars.getStation();
	} else {
		s2 = stars.getStation();
	}
}

%>



	<div class="mx-auto text-center" style="width: 70%;">
		<h1 style="text-align: center">社員情報変更</h1>

		<form class="row g-3" action="<%= request.getContextPath() %>/PersonalModServlet" method="post">
		  <div class="col-md-4">
		    <label for="employeeId" class="form-label">社員番号</label>
		    <input type="text" class="form-control" id="employeeId" name="employeeId" value="<%= personal_data.getId() %>" readonly>
		  </div>
		  <div class="col-md-4">
		    <label for="name" class="form-label">名前</label>
		    <input type="text" class="form-control" id="name" name="name" value="<%= personal_data.getName() %>" readonly>
		  </div>
		  <div class="col-md-4">
		    <label for="birthday" class="form-label">生年月日</label>
		    <input type="date" class="form-control" id="birthday" name="birthday" value="<%= personal_data.getBirthday() %>">

		  </div>
		  <div class="col-md-6">
		    <label for="stations" class="form-label">最寄り駅1</label>
		    <input type="text" class="form-control" id="station1" name="station1" value="<% out.print(s1); %>">
		  </div>	  
		  <div class="col-md-6">
		    <label for="stations" class="form-label">最寄り駅2</label>
		    <input type="text" class="form-control" id="station2" name="station2" value="<% out.print(s2); %>">
		  </div>			  
		  
		  <div class="col-md-6">
		    <label for="certi" class="form-label">取得資格</label>
		    <input type="text" class="form-control" id="certi" name="certi" 
		    value="<% for(Certi certis : certi_data){ %>
			 		<%= certis.getCertification() %>
			 		<% } %> ">
		  </div>
		  
		  <button type="submit" class="btn btn-success">変更</button>
		  
		</form>
		  
		<div style="text-align:center;">
			<a href="<%=request.getContextPath() %>/ResumeServlet">業務経歴一覧</a>
		</div>
	</div>


</body>
</html>