<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%@ page import="java.util.*" %>
<% 
List<Career> career_data = (List<Career>)request.getAttribute("career");
Personal personal_data = (Personal)request.getAttribute("personal");
List<Station> station_data = (List<Station>)request.getAttribute("station");
List<Certi> certi_data = (List<Certi>)request.getAttribute("certi");
String lastlogin = (String)request.getAttribute("lastlogin");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resume一覧</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>

<body>

<div class="mx-auto text-center" style="width: 90%;">
	<h2 class="text-center mb-3">社員情報</h2>
	<table class="table table-striped">
		<thead class="table-info">
			 <tr>
			 	<th scope="col">社員番号</th>
			 	<th scope="col">名前</th> 
			 	<th scope="col">性別</th>
			 	<th scope="col">年齢</th>
			 	<th scope="col">最寄り駅</th>
			 	<th scope="col">取得資格</th>
			 	<th scope="col">－</th>
			  </tr>
		</thead>
		<tbody>
			 <tr>
			 	<td><%= personal_data.getId() %></td>
			 	<td><%= personal_data.getName() %></td>
			 	<td><%= personal_data.getSex() %></td>
			 	<td><%= personal_data.getAge() %></td>
			 	<td><% for(Station stars : station_data){ %>
			 		<%= stars.getStation() %><br>
			 		<% } %>
			 	 </td>
			 	<td><% for(Certi certis : certi_data){ %>
			 		<%= certis.getCertification() %><br>
			 		<% } %>
			 	</td>
			 	<td><a class="btn btn-success" href="<%= request.getContextPath() %>/PersonalModServlet" role="button">変更</a> </td>
			 </tr>
		</tbody>
	</table>
</div>
<br>
<br>	
<div class="mx-auto text-center" style="width: 90%;">
	<h2 class="text-center mb-3">業務経歴一覧</h2>
	<table class="table table-striped">
		<thead class="table-primary">
			 <tr>
			 	<th scope="col">no</th>
			 	<th scope="col">開始日</th>
			 	<th scope="col">終了日</th>
			 	<th scope="col">システム名</th>
			 	<th scope="col">システム詳細</th>
			 	<th scope="col">役割</th>
			 	<th scope="col">ツール</th>
			 	<th scope="col">更新日</th>
			 	<th scope="col">－</th>
			  </tr>
		</thead>
		<tbody>
			 <% int i=1;%>
			 <% for(Career cars : career_data){  %>
			 <tr>
			 	<td><%= i %></td>
			 	<td><%= cars.getStartdate() %></td>
			 	<td><%= cars.getEnddate() %></td>
			 	<td><%= cars.getSysname() %></td>
			 	<td><%= cars.getSysdtl() %></td>
			 	<td><%= cars.getRoles() %></td>
			 	<td><%= cars.getTools() %></td>
			 	<td><%= cars.getUpdate() %></td>
			 	<td><button type="button" class="btn btn-info" 
			 		 onclick="buttonModClick('<%= cars.getCredate() %>')">変更/削除</button>
				</td>
			 </tr>
			 <% 	i++; %>
			 <% } %>
		</tbody>
	</table>
	<button type="button" class="btn btn-primary" 
	 onclick="location.href='<%= request.getContextPath() %>/ResumeRegisterServlet'">経歴登録</button>
</div>

</body>

<script type="text/javascript">
function buttonModClick(val){
	
	location.href = "<%= request.getContextPath() %>/ResumeModServlet?credate=" + val;
}
</script>


</html>
