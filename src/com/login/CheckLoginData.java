package com.login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.util.ConnectDatabase;

public class CheckLoginData {
	public static boolean checkUser(String userType, String userName, String userPassword){
		try {
			Connection connection = (Connection) ConnectDatabase.getConnect();
			PreparedStatement preparedStatement = null;
			if("GLZ".equals(userType)){
				preparedStatement = connection.prepareStatement("select ID from GLZ_INF where ZH=? and MM=?");
			}else if ("KH".equals(userType)){
				preparedStatement = connection.prepareStatement("select ID from KH_INF where ZH=? and MM=?");
			}else if ("SGZ".equals(userType)) {
				preparedStatement = connection.prepareStatement("select ID from SGZ_INF where ZH=? and MM=?");
			}
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPassword);
			ResultSet rSet = preparedStatement.executeQuery();
			if(!rSet.next()){
				rSet.close();
				preparedStatement.close();
				connection.close();
				return false;
			}
			else{
				int userID = rSet.getInt(1);
				ActionContext.getContext().getSession().clear();
				ActionContext.getContext().getSession().put("userID", userID);
				ActionContext.getContext().getSession().put("userType", userType);
			}
			rSet.close();
			preparedStatement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
