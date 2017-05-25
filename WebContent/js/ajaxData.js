/**
 * 
 */

function getAjax(obj){
	var p = obj.text();
	myChart.showLoading();
	
	var dates=[];    //（实际用来盛放X轴坐标值）
    var nums=[];    //（实际用来盛放Y坐标值）
     
     $.ajax({
     type : "post",
     async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
     url : "getPartData",    
     data : {'part':p},
     dataType : "json",        //返回数据形式为json
     success : function(result) {
    	//请求成功时执行该函数内容，result即为服务器返回的json对象
         if (result) {
        	 	var part = result.part;
        	 	var partData = result.data;

//        	 	dates.push('');
//        	 	nums.push('');
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
   						text:"进度明细："+p 
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
         }
    },
     error : function(errorMsg) {
         //请求失败时执行该函数
     		alert("图表请求数据失败!");
     		myChart.hideLoading();
     }
})

}

