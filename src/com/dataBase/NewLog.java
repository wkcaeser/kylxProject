package com.dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.util.ConnectDatabase;

public class NewLog {
	public static String GetLog(String userWXZH){
		String log = "";
		
		Connection connection;
		try {
			connection = (Connection) ConnectDatabase.getConnect();
			//查询用户名和密码是否正确，若正确，取得ID值
			String sql1 = "select GC_ID from GC_INF where KH_ID = (select KH_ID from WeiXin_INF where KH_WX = ?);";
			PreparedStatement pStatement1 = connection.prepareStatement(sql1);
			pStatement1.setString(1, userWXZH);
			ResultSet rSet1 = pStatement1.executeQuery();
			if(rSet1.next()){
				int gcid=rSet1.getInt("GC_ID");
				String sql2 = "select * from LOG_INF where LOG_GC_ID = ? order by LOG_ID desc";
				PreparedStatement pStatement2 = connection.prepareStatement(sql2);
				pStatement2.setInt(1, gcid);
				ResultSet rSet2 = pStatement2.executeQuery();
				if(rSet2.next()){
					String LOG_LX = rSet2.getString("LOG_LX");
					log += "施工类型：" + LOG_LX + "\n";
					
					String LOG_JD = rSet2.getString("LOG_JD");
					log += "施工进度：" + LOG_JD + "\n";
					
					String LOG_CLXH = rSet2.getString("LOG_CLXH"); 
					log += "材料型号：" + LOG_CLXH + "\n";
					
					String LOG_CLCS = rSet2.getString("LOG_CLCS"); 
					log += "材料厂商：" + LOG_CLCS + "\n";
					
					String LOG_CLSYSL = rSet2.getString("LOG_CLSYSL"); 
					log += "材料数量：" + LOG_CLSYSL + "\n";
					
					String LOG_RQ = rSet2.getString("LOG_RQ"); 
					log += "日期：" + LOG_RQ + "\n";
					
					String LOG_BZ = rSet2.getString("LOG_BZ"); 
					log += "备注：" + LOG_BZ + "\n";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log = "获取信息失败！";
		}
		
		return log;
	}
}
