/**
 * 
 */
function EditKHData(obj){
	var tr = obj.closest("td").closest("tr");
	var zh = tr.children('#kzh').text();
	var form = $("<form method='post' action='deleteKH'>" +
				"<input type='text' name='zh' value='" + zh + "'>"+
				"</form>");
	form.submit();
}

function EditGLZData(obj){
	var tr = obj.closest("td").closest("tr");
	var zh = tr.children('#gzh').text();
	var form = $("<form method='post' action='deleteGLZ'>" +
				"<input type='text' name='zh' value='" + zh + "'>"+
				"</form>");
	form.submit();
}

function EditSGZData(obj){
	var tr = obj.closest("td").closest("tr");
	var zh = tr.children('#szh').text();
	var form = $("<form method='post' action='deleteSGZ'>" +
				"<input type='text' name='zh' value='" + zh + "'>"+
				"</form>");
	form.submit();
}

function EditGCData(obj){
	var tr = obj.closest("td").closest("tr");
	var id = tr.children('#gid').text();
	var form = $("<form method='post' action='deleteGC'>" +
				"<input type='text' name='id' value='" + id + "'>"+
				"</form>");
	form.submit();
}

$(document).ready(
		function(){
			$.ajax({
				type:"post",
				async:true,
				url:"getUserData",
				data:{},
				dataType:"json",
				success : function(result) {
			         //请求成功时执行该函数内容，result即为服务器返回的json对象
			         if (result) {
			        	 var khData = result.khData;
			        	 var sgzData = result.sgzData;
			        	 var gcData = result.gcData;
			        	 var glzData = result.glzData;
			        	 
			        	 for(var i=0; i<khData.length; i++){
			        		 var id = khData[i].id;
			        		 var zh = khData[i].zh;
			        		 var mm = khData[i].mm;
			        		 var xm = khData[i].xm;
			        		 var xb = khData[i].xb;
			        		 var yx = khData[i].yx;
			        		 var dh = khData[i].dh;
			        		 
			        		 var parent = $("#kh_data");
			        		 var newData = $("<tr><td>" + id + "</td>" +
			        			"<td id='kzh'>" + zh + "</td>" +
								"<td>" + mm + "</td>" +
								"<td>" + xm + "</td>" +
								"<td>" + xb + "</td>" +
								"<td>" + yx + "</td>" +
								"<td>" + dh + "</td>" +
								"<td><button onclick='EditKHData($(this))'>删除</button></td></tr>");
			        		 parent.append(newData);
			        	 }
			        	 
			        	 for(var i=0; i<glzData.length; i++){
			        		 var id = glzData[i].id;
			        		 var zh = glzData[i].zh;
			        		 var mm = glzData[i].mm;
			        		 var xm = glzData[i].xm;
			        		 var xb = glzData[i].xb;
			        		 var yx = glzData[i].yx;
			        		 var dh = glzData[i].dh;
			        		 
			        		 var parent = $("#glz_data");
			        		 var newData = $("<tr><td>" + id + "</td>" +
			        			"<td id='gzh'>" + zh + "</td>" +
								"<td>" + mm + "</td>" +
								"<td>" + xm + "</td>" +
								"<td>" + xb + "</td>" +
								"<td>" + yx + "</td>" +
								"<td>" + dh + "</td>" +
								"<td><button onclick='EditGLZData($(this))'>删除</button></td></tr>");
			        		 parent.append(newData);
			        	 }

			        	 
			        	 for(var i=0; i<sgzData.length; i++){
			        		 var zh = sgzData[i].zh;
			        		 var mm = sgzData[i].mm;
			        		 var xm = sgzData[i].xm;
			        		 var xb = sgzData[i].xb;
			        		 var yx = sgzData[i].yx;
			        		 var dh = sgzData[i].dh;
			        		 var fzlx = sgzData[i].fzlx;
			        		 
			        		 var parent = $("#sgz_data");
			        		 var newData = $("<tr><td id='szh'>" + zh + "</td>" +
								"<td>" + mm + "</td>" +
								"<td>" + xm + "</td>" +
								"<td>" + xb + "</td>" +
								"<td>" + dh + "</td>" +
								"<td>" + fzlx + "</td>" +
								"<td>" + yx + "</td>" +
								"<td><button onclick='EditSGZData($(this))'>删除</button></td></tr>");
			        		 parent.append(newData);
			        	 }
			        	 
			        	 for(var i=0; i<gcData.length; i++){
			        		 var gc_id = gcData[i].gc_id;
			        		 var glz_xm = gcData[i].glz_id;
			        		 var kh_xm = gcData[i].kh_id;
			        		 var gc_dd = gcData[i].gc_dd;
			        		 
			        		 var parent = $("#gc_data");
			        		 var newData = $("<tr><td id='gid'>" + gc_id + "</td>" +
								"<td>" + glz_xm + "</td>" +
								"<td>" + kh_xm + "</td>" +
								"<td>" + gc_dd + "</td>" +
								"<td><button onclick='EditGCData($(this))'>删除</button></td></tr>");
			        		 parent.append(newData);
			        	 }
			         }
			     
			     },
			     error : function(errorMsg) {
			         //请求失败时执行该函数
			     		alert("请求数据失败!");
			     }
			});
			
			var first = $('div#first');
			var second = $('div#second');
			var third = $('div#third');
			var four = $('div#four');
			var title = $('h3#view_title');
			$('td#KH').click(
					function(event){
						first.css("display", 'block');
						second.css("display", 'none');
						third.css("display", 'none');
						four.css("display", 'none');
						title.text($('td#KH').text());
					}
			);
			$('td#SGZ').click(
					function(event){
						first.css("display", 'none');
						second.css("display", 'block');
						third.css("display", 'none');
						four.css("display", 'none');
						title.text($('td#SGZ').text());
					}
			);
			$('td#GC').click(
					function(event){
						first.css("display", 'none');
						second.css("display", 'none');
						third.css("display", 'block');
						four.css("display", 'none');
						title.text($('td#GC').text());
					}
			);
			$('td#view').click(
					function(event){
						var pTag = $('#con');
						var input = $("<form method='post' action='viewSession' id='addForm'>" +
									"<input type='text' name='khID'/>" +
									"<input type='submit' value='查询'/>" +
									"</form>");
						if($('form#addForm').length>0){
							return;
						}
						pTag.append(input);
					}
			);
			if($('td#GLZ').length>0){
				$('td#GLZ').click(
						function(event){
							first.css("display", 'none');
							second.css("display", 'none');
							third.css("display", 'none');
							four.css("display", 'block');
							title.text($('td#GLZ').text());
						}
				);
				$('td#editData').click(
						function(event){
							window.location.href="edit.action";
						}
				);
			}
		}
);
