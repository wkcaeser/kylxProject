<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script language="JavaScript" src="js/jquery.min.js"></script>
	<script language="JavaScript" src="js/bootstrap.min.js"></script>
	<script language="JavaScript" src="js/echarts.min.js"></script>
	<link href="css/dataView.css" rel="stylesheet" type="text/css" />
<title>用户装修信息界面</title>

<%
	String XB = (String)session.getAttribute("XB_view");
	String sex = null;
	if(XB.equals("man"))
		sex = "男";
	else
		sex = "女";
%>

</head>
 
<body>

<div class="all"><!-- view -->

	<div class="left"><!-- left -->
		<div class = "type">个人资料</div>
		<div class="xuxian"></div>
		<table class="table table-bordered">
		<tr><th colspan='2'>个人信息</th></tr>
		<tr>
			<td >姓名:</td>
			 <td style="vertical-align:middle; text-align:center;"><%=session.getAttribute("XM_view") %></td>
		</tr>
	
		<tr>    
			<td>性别:</td>
			<td style="vertical-align:middle; text-align:center;"><%=sex %></td>
		</tr>    
		<tr>
			<td>权限:</td>
			<td style="vertical-align:middle; text-align:center;">客户</td>
		</tr>
		</table>
		<br><br><br>
		
		<div id="ajaxButton"><!-- menu -->
		<table class="table table-bordered">
			<tr>
				<td onclick="getAjax($(this))">安装</td>
				<td onclick="getAjax($(this))">地面</td>
			</tr>
			<tr>
				<td onclick="getAjax($(this))">电</td>
				<td onclick="getAjax($(this))">吊顶</td>
			</tr>
			<tr>
				<td onclick="getAjax($(this))">木工</td>
				<td onclick="getAjax($(this))">泥瓦</td>
			</tr>
			<tr>
				<td onclick="getAjax($(this))">墙壁</td>
				<td onclick="getAjax($(this))">水</td>
			</tr>
			<tr>
				<td onclick="getAjax($(this))">隐蔽</td>
			</tr>
		</table>
		</div><!-- menu -->
		
	</div><!-- left -->

	<div class="view"><!-- pic -->
	
 		<div class="show" id="view1">
 			<div class="show">
 				<div id="main" style="width: 100%;height:400px;"></div>
    				<script type="text/javascript">

        			var myChart = echarts.init(document.getElementById('main'));

       				 var option = {
       					 title:{
       						text:"进度明细" 
       					 },
   						 tooltip : {
          					  trigger: 'axis'
   						 },
//     					 legend: {
//       						  data:['水','电','木工','泥瓦','墙壁','安装','地面','吊顶','隐蔽']
//    						 },
   						 toolbox: {
       						show : true,
        					feature : {
            					mark : {show: true},
           						dataView : {show: true, readOnly: false},
            					magicType : {show: true, type: ['bar','queue']},
            					restore : {show: true},
            					//	saveAsImage : {show: true}
        					}
    					},
    					calculable : true,
    					xAxis : [{
            				type : 'category',
           					boundaryGap : false,
           					data : ['','1.1','1.2','1.3','1.4','1.5','1.6','1.7']
        				}],
    					yAxis : [{
            				type : 'value',
            				min:0,
            				max:100,
            				splitNumber:10,
            				axisLabel:{
                				formatter:'{value}%'
            				}
        				}],
    					series : [{
            				name:'水',
            				type:'line',
            				//type:'line',
            				queue: '进度',
            				//data:[],
            				data:[0,10,20,30,40,50,60,70]
        					//    itemStyle:{normal:{label:{show:true}}}
        				}]
    					};

        		myChart.setOption(option);
				myChart.showLoading();
				
				var dates=[];    //（实际用来盛放X轴坐标值）
		        var nums=[];    //（实际用来盛放Y坐标值）
		         
		         $.ajax({
		         type : "post",
		         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         url : "getPartData",    //请求发送到TestServlet处
		         data : {'part':'水'},
		         dataType : "json",        //返回数据形式为json
		         success : function(result) {
		             //请求成功时执行该函数内容，result即为服务器返回的json对象
		             if (result) {
		            	 	var part = result.part;
		            	 	var partData = result.data;

// 		            	 	dates.push('');
// 		            	 	nums.push('');
		            	 	for(var i=partData.length-1; i>=0; i--){
		            	 		dates.push(partData[i].LOG_RQ.split(" ")[0]);
		            	 		nums.push(partData[i].LOG_JD);
		            	 	}

		                    myChart.hideLoading();    //隐藏加载动画
		                    myChart.setOption({        //加载数据图表
		                        xAxis: {
		                            data: dates
		                        },
		                        series: [{
		                            // 根据名字对应到相应的系列
		                            name: [part],
		                            data: nums
		                        }],
		                        title:{
		       						text:"进度明细：水" 
		       					 }, 
		                    });
		                    
		                    var power = result.power;
		                    var table = $("<table id='table-3' style='width: 100%;height:200px;text-align:left''></table>");
		             		var th = $("<thead><tr>"+
		             				"<th> 施工类型</th>"+
		             				"<th> 施工人</th>"+
		             				"<th> 施工进度</th>"+
		             				"<th> 材料型号</th>"+
		             				"<th> 材料厂商</th>"+
		             				"<th> 材料数量(个)</th>"+
		             				"<th> 日期</th>"+
		             				"<th> 详情</th>"+
		             				"<th> 备注</th>"+
		             				"</tr></thead>");
		             		
		             		if(power=="sa"){
		             			th.find("tr").append($("<th>材料价格(元)</th>"));
		             			th.find("tr").append($("<th>施工费用(元)</th>"));
		             		}
		             		
		             		table.append(th);
							console.log(partData)
		             		var tbody = $("<tbody></tbody>");
		             		for(var i=0; i<partData.length; i++){
		             			var tr = $("<tr></tr>");
		             			tr.append("<td>" + partData[i].LOG_LX +"</td>");
		             			tr.append("<td>" + partData[i].LOG_SGZ +"</td>");
		             			tr.append("<td>" + partData[i].LOG_JD +"</td>");
		             			tr.append("<td>" + partData[i].LOG_CLXH +"</td>");
		             			tr.append("<td>" + partData[i].LOG_CLCS +"</td>");
		             			tr.append("<td>" + partData[i].LOG_CLSYSL +"</td>");
		             			tr.append("<td>" + partData[i].LOG_RQ +"</td>");
		             			tr.append("<td><a href='" + partData[i].LOG_PIC +"'>详情</a></td>");
		             			tr.append("<td>" + partData[i].LOG_BZ +"</td>");
		             			
		             			if(power=="sa"){
		             				tr.append("<td>" + partData[i].LOG_CLJG +"</td>");
		             				tr.append("<td>" + partData[i].LOG_FY +"</td>");
		             			}
		             			
		             			tbody.append(tr);
		             		}
		             		table.append(tbody);
		             		
		             		$("#view1-1").empty();
		             		$("#view1-1").append(table);
		             		console.log("-------------------")
		             }
		        },
		         error : function(errorMsg) {
		             //请求失败时执行该函数
		         		alert("图表请求数据失败!");
		         		myChart.hideLoading();
		         }
		    })

        /*
        myChart.on('click',function(param){
            var name=param.name;
            if(name=="周二")
                window.location.href="www.baidu.com";
        })
        */
   	</script>
 	</div>
	
			
			<div class="class" id="view1-1">
				
			</div>
 		
 		</div>
		
	</div><!-- pic -->

</div><!-- view -->
<script language="JavaScript" src="js/ajaxData.js"></script>
</body>
</html>