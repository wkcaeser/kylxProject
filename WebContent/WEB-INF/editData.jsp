<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息修改</title>
</head>
<body>
<body>
<p style="text-align:center; color:red">
	<%
		String log = (String)session.getAttribute("editDataLog");
		
		if(log!=null || "".equals(log)){
			out.print(log);
			session.setAttribute("editDataLog", "");
		}
	%>
</p>
<form method="post" action="editData">
<table>
<tr>
<td>用户类型：</td>
<td><input type="radio" name='Type' value="KH"/>客户
	<input type="radio" name='Type' value="SGZ"/>施工者
<td><input type="radio" name='Type' value="GLZ"/>管理者
</tr>
<tr>
<td>账号：</td>
<td><input type="text" name='ZH'/></td>
</tr>
<tr>
<td>密码：</td>
<td><input type="text" name='MM'/></td>
</tr>
<tr>
<td>姓名：</td>
<td><input type="text" name='XM'/></td>
</tr>
<tr>
<td>性别：</td>
<td><input type="text" name='XB'/></td>
</tr>
<tr>
<td>邮箱：</td>
<td><input type="text" name='YX'/></td>
</tr>
<tr>
<td>电话：</td>
<td><input type="text" name='DH'/></td>
</tr>
<tr>
<td>材料价格（管理者必填）：</td>
<td><input type="text" name='LX'/></td>
</tr>
<tr>
<td>修改？</td>
<td><input type="submit"/></td>
</tr>
</table>
</form>
</body>
</html>