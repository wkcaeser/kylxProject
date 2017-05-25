package com.dataContrl;

import java.sql.PreparedStatement;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

public class EditGCData extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.get("topPower")==null || (int)session.get("topPower") != 1){
			ActionContext.getContext().getSession().put("Log_add", "没有操作权限！");
			return ERROR;
		}
		Connection connection = (Connection) ConnectDatabase.getConnect();
		String sql = "delete from GC_INF where GC_ID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, id);
		int num = preparedStatement.executeUpdate();
		if(num==1){
			ActionContext.getContext().getSession().put("Log_add", "删除工程成功！");
			return SUCCESS;
		}
		else{
			ActionContext.getContext().getSession().put("Log_add", "删除工程失败！");
			return ERROR;
		}
	}


}
