<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>装修管理系统</title>
 	<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script language="JavaScript" src="js/jquery.min.js"></script>
<!-- 	<script language="JavaScript" src="js/bootstrap.min.js"></script> -->
	<script type="text/JavaScript" src="js/util.js"></script>
 	
<!--  	<script src="js/echarts.min.js"></script> -->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/mainView.css" rel="stylesheet" type="text/css" />

<%
	String XB = (String)session.getAttribute("XB");
	String sex = null;
	if(XB.equals("man"))
		sex = "男";
	else
		sex = "女";
	
	String userType = (String)session.getAttribute("userType");
	String type = null;
	if("GLZ".equals(userType))
		type = "管理者";
	if("SGZ".equals(userType))
		type = "施工者";
%>  
 
</head>

<body style = "background:#0080ff;">
<div class="header">
    <span>后台管理界面平台</span>    
    <ul>
    <li><a href="index.jsp">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>   
</div>

<div class ="container">
 <div class="menu">
	<div class="id">
		<div class = "type">个人资料</div>
		<div class="xuxian"></div>
		<table class="table table-bordered">
		<tr><th colspan='2'>个人信息</th></tr>
		<tr>
			<td >姓名:</td>
			 <td style="vertical-align:middle; text-align:center;"><%=session.getAttribute("XM") %></td>
		</tr>
	
		<tr>    
			<td>性别:</td>
			<td style="vertical-align:middle; text-align:center;"><%=sex %></td>
		</tr>    
		<tr>
			<td>权限:</td>
			<td style="vertical-align:middle; text-align:center;"><%=type %></td>
		</tr>
		
		</table>
		<br>
		<br>	
		</div>
		
		<div id="con" style="margin-left:10%;margin-right:10%;">
		<table class="table table-bordered">
			<tr><td id="KH">客户信息管理</td></tr>
			<tr><td id="SGZ">施工者管理</td></tr>
			<tr><td id="GC">工程信息管理</td></tr>
			<%
				if(session.getAttribute("topPower")!=null && (int)session.getAttribute("topPower") == 1){
					out.print("<tr><td id='GLZ'>管理人员管理</td></tr>");
					out.print("<tr><td id='editData'>信息修改</td></tr>");
				}
			%>
			<tr><td id="view">工程信息查询</td></tr>
		</table>
		</div>
 </div><!-- class="menu -->
 	
 <div class="view" id="view">
 	<div> <!--dataContrl  -->
 			<div>
 				<h3 id='view_title'>客户信息管理</h3>
 			</div>
 			<p style="color:red">
			<%
			String log = (String)session.getAttribute("Log_add");
			
			if(log!=null || "".equals(log)){
				out.print(log);
				session.removeAttribute("Log_add");
			}
			%>
			</p>
			<div id='first' style='margin-left:5%'>
					<table class="mytable">
						<thead>
							<tr>
								<th>
									编号
								</th>
								<th>
									账号
								</th>
								<th>
									密码
								</th>
								<th>
									姓名
								</th>
								<th>
									性别
								</th>
								<th>
									邮箱
								</th>
								<th>
									电话
								</th>
								<th>
									管理
								</th>
							</tr>
						</thead>
						<tbody id="kh_data">
						<form method='post' action="KH_ADD">
							<tr>
								<td><label>ID</label></td>
								<td><input name='ZH' type='text'/></td>
								<td><input name='MM' type='text'/></td>
								<td><input name='XM' type='text'/></td>
								<td><input name='XB' type='text'/></td>
								<td><input name='YX' type='text'/></td>
								<td><input name='DH' type='text'/></td>
								<td><input name='add' type='submit' value='添加'/></td>
							</tr>
						</form>	
						</tbody>
					</table>
				</div><!-- id=first -->
 	
 			<div style='display:none;margin-left:5%' id='second' >
					<table class="mytable">
						<thead>
							<tr>
								<th>
									账号
								</th>
								<th>
									密码
								</th>
								<th>
									姓名
								</th>
								<th>
									性别
								</th>
								<th>
									电话
								</th>
								<th>
									施工类型
								</th>
								<th>
									邮箱
								</th>
								<th>
									管理
								</th>
							</tr>
						</thead>
						<tbody id="sgz_data">
							<form method='post' action="SGZ_ADD">
							<tr>
								<td><input name='ZH' type='text'/></td>
								<td><input name='MM' type='text'/></td>
								<td><input name='XM' type='text'/></td>
								<td><input name='XB' type='text'/></td>
								<td><input name='DH' type='text'/></td>
								<td><input name='FZLX' type='text'/></td>
								<td><input name='YX' type='text'/></td>
								<td><input name='add' type='submit' value='添加'/></td>
							</tr>
							</form>
						</tbody>
					</table>
				</div><!-- id=second -->
			
			<div ID='four'  style='display:none'>
				<table class="mytable">
						<thead>
							<tr>
								<th>
									编号
								</th>
								<th>
									账号
								</th>
								<th>
									密码
								</th>
								<th>
									姓名
								</th>
								<th>
									性别
								</th>
								<th>
									邮箱
								</th>
								<th>
									电话
								</th>
								<th>
									管理
								</th>
							</tr>
						</thead>
						<tbody id="glz_data">
						<form method='post' action="GLZ_ADD">
							<tr>
								<td><label>ID</label></td>
								<td><input name='ZH' type='text'/></td>
								<td><input name='MM' type='text'/></td>
								<td><input name='XM' type='text'/></td>
								<td><input name='XB' type='text'/></td>
								<td><input name='YX' type='text'/></td>
								<td><input name='DH' type='text'/></td>
								<td><input name='add' type='submit' value='添加'/></td>
							</tr>
						</form>	
						</tbody>
					</table>		
			</div>
			
			<div ID='third'  style='display:none'>
					<table class="mytable">
						<thead>
							<tr>
								<th>
									工程ID
								</th>
								<th>
									管理者账号
								</th>
								<th>
									客户账号
								</th>
								<th>
									工程地区
								</th>
								<th>
									管理
								</th>
							</tr>
						</thead>
						<tbody id="gc_data">
							<form method='post' action='GC_ADD' >
							<tr>
								<td><input name='GC_ID' type='text'/></td>
								<td><input name='GLZ_ZH' type='text'/></td>
								<td><input name='KH_ZH' type='text'/></td>
								<td><input name='GC_DD' type='text'/></td>
								<td><input name='add' type='submit' value='添加'/></td>
							</tr>
							</form>
						</tbody>
					</table>
				</div><!-- id=third -->
 		
 		</div><!-- dataContrl -->
 	</div><!-- id=view -->
 	
</div><!-- class ="container" -->

	<div class="footer">.</div>
</body>
</html>