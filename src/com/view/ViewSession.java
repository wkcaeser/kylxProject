package com.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

public class ViewSession extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int khID;

	public int getKhID() {
		return khID;
	}

	public void setKhID(int khID) {
		this.khID = khID;
	}

	private Boolean initUserData(String type, int ID){
		try {
			Connection connection = (Connection)ConnectDatabase.getConnect();
			String sql = "select * from " + type + "_INF where ID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ID);
			ResultSet rSet = preparedStatement.executeQuery();
			if(rSet.next()){
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("ID_view", ID);
				session.put("userType_view", type);
				session.put("XM_view", rSet.getString("XM"));
				session.put("XB_view", rSet.getString("XB"));
				session.put("YX_view", rSet.getString("YX"));
				session.put("DH_view", rSet.getString("DH"));
				session.put("power", "sa");
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("get");
		if(initUserData("KH", khID))
			return SUCCESS;
		
		return ERROR;
	}	

}