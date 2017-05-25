package com.dataContrl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

public class Gc_add extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String GC_ID;
	private String GLZ_ZH;
	private String KH_ZH;
	private String GC_DD;
	public String getGC_ID() {
		return GC_ID;
	}
	public void setGC_ID(String gC_ID) {
		GC_ID = gC_ID;
	}
	public String getGLZ_ZH() {
		return GLZ_ZH;
	}
	public void setGLZ_ZH(String gLZ_ZH) {
		GLZ_ZH = gLZ_ZH;
	}
	public String getKH_ZH() {
		return KH_ZH;
	}
	public void setKH_ZH(String kH_ZH) {
		KH_ZH = kH_ZH;
	}
	public String getGC_DD() {
		return GC_DD;
	}
	public void setGC_DD(String gC_DD) {
		GC_DD = gC_DD;
	}
	
	private String get_GLZ_ID() throws SQLException{
		String id = null;
		Connection connection = (Connection)ConnectDatabase.getConnect();
		String sql = "select ID from GLZ_INF where ZH=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, GLZ_ZH);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		id = resultSet.getString(1);
		resultSet.close();
		preparedStatement.close();
		return id;
	}
	
	private String get_KH_ID() throws SQLException{
		String id = null;
		Connection connection = (Connection)ConnectDatabase.getConnect();
		String sql = "select ID from KH_INF where ZH=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, KH_ZH);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		id = resultSet.getString(1);
		resultSet.close();
		preparedStatement.close();
		return id;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
		String GLZ_ID = get_GLZ_ID();
		String KH_ID = get_KH_ID();
		Connection connection = (Connection)ConnectDatabase.getConnect();
		String sql = "insert into GC_INF(GC_ID, GLZ_ID, KH_ID, GC_DD) values(?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, GC_ID);
		preparedStatement.setString(2, GLZ_ID);
		preparedStatement.setString(3, KH_ID);
		preparedStatement.setString(4, GC_DD);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		
		}catch(Exception e){
			e.printStackTrace();
			ActionContext.getContext().getSession().put("Log_add", "添加工程失败！");
			return ERROR;
		}
		ActionContext.getContext().getSession().put("Log_add", "添加工程成功！");
		return SUCCESS;
	}

}
