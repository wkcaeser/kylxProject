package com.dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.util.ConnectDatabase;

public class WXKHBind {
	public	static String bindWX(String ZH, String MM, String WXZH) {
		try {
			Connection connection = (Connection) ConnectDatabase.getConnect();
			//查询用户名和密码是否正确，若正确，取得ID值
			String sql1 = "select ID from KH_INF where ZH=? and MM=?";
			PreparedStatement pStatement1 = connection.prepareStatement(sql1);
			pStatement1.setString(1, ZH);
			pStatement1.setString(2, MM);
			ResultSet rSet1 = pStatement1.executeQuery();
			//若用户名和密码不正确直接返回
			if(!rSet1.next()){
				return "请检查用户名和密码是否正确！";
			}
			int ID = rSet1.getInt("ID");
			rSet1.close();
			pStatement1.close();
			//删除此微信号绑定的ID，并更新为新的ID
			String sql2 = "delete from WeiXin_INF where KH_WX=?";
			PreparedStatement pStatement2 = connection.prepareStatement(sql2);
			pStatement2.setString(1, WXZH);
			pStatement2.executeUpdate();
			pStatement2.close();
			String sql3 = "insert into WeiXin_INF values(?,?)";
			PreparedStatement pStatement3 = connection.prepareStatement(sql3);
			pStatement3.setInt(1, ID);
			pStatement3.setString(2, WXZH);
			pStatement3.executeUpdate();
			pStatement3.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
}
