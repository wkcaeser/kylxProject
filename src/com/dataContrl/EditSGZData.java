package com.dataContrl;

import java.sql.PreparedStatement;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

public class EditSGZData extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String zh;

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
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
		String sql = "delete from SGZ_INF where ZH=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, zh);
		int num = preparedStatement.executeUpdate();
		if(num==1){
			ActionContext.getContext().getSession().put("Log_add", "删除施工者成功！");
			return SUCCESS;
		}
		else{
			ActionContext.getContext().getSession().put("Log_add", "删除施工者失败！");
			return ERROR;
		}
	}

}
